package kata.mowitnow.batch;


import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.transform.PassThroughLineAggregator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class JobConfig {

    @Bean
    public Job job(JobRepository jobRepository, JobCompletionNotificationListener listener, Step step) {
        return new JobBuilder("Job",jobRepository)
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step)
                .end()
                .build();
    }

    @Bean
    public Step step(JobRepository jobRepository, PlatformTransactionManager transactionManager, ItemWriter<String> writer) {
        return new StepBuilder("step", jobRepository)
                .<String, String> chunk(1, transactionManager)
                .reader(reader())
                .writer(writer)
                .build();
    }
   
    @Bean
    public ItemReader<String> reader() {
        return new MowerItemReader();
    }

    @Bean
    public ItemWriter<String> writer() {
        return new FlatFileItemWriterBuilder<String>()
                .name("mowerItemWriter")
                .resource(new FileSystemResource(
                        "target/Mower.txt"))
                .lineAggregator(new PassThroughLineAggregator<>())
                .build();
    }
}
