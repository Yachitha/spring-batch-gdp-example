package com.assesment.rootcodelabs.gdpcsvprocessor;

import com.assesment.rootcodelabs.entity.GdpCountry;
import com.assesment.rootcodelabs.entity.GdpGrowthRate;
import com.assesment.rootcodelabs.repository.GdpCountryRepository;
import com.assesment.rootcodelabs.repository.GdpRateRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;

@EnableBatchProcessing
@Configuration
@Log4j2
public class CsvFileToDatabaseGdpCountryConfig {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    public DataSource dataSource;

    @Autowired
    public GdpCountryRepository gdpCountryRepository;

    @Autowired
    public GdpRateRepository gdpGrowthRepository;

    @Bean
    public FlatFileItemReader<GdpCountry> csvGdpCountryReader() {
        log.info("CALLED CSV READING");
        FlatFileItemReader<GdpCountry> reader = new FlatFileItemReader<GdpCountry>();
        reader.setResource(new ClassPathResource("country_code_conversion.csv"));
        reader.setLinesToSkip(1);
        reader.setLineMapper(new DefaultLineMapper<GdpCountry>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames("country", "alpha2Code", "alpha3Code", "numericVal");
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<GdpCountry>() {{
                setTargetType(GdpCountry.class);
            }});
        }});
        log.info("CSV READING DONE");
        return reader;
    }

    @Bean
    public FlatFileItemReader<GdpGrowthRate> gdpRateCsvReader() {
        log.info("CALLED CSV READING GDPGROWTHRATE");
        FlatFileItemReader<GdpGrowthRate> reader = new FlatFileItemReader<GdpGrowthRate>();
        reader.setResource(new ClassPathResource("all_country_gdp_dataset.csv"));
        reader.setLinesToSkip(1);
        reader.setLineMapper(new DefaultLineMapper<GdpGrowthRate>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames("countryName", "countryCode", "year", "value");
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<GdpGrowthRate>() {{
                setTargetType(GdpGrowthRate.class);
            }});
        }});
        log.info("CSV READING DONE GDPGROWTHRATE");
        return reader;
    }

    @Bean
    ItemProcessor<GdpCountry, GdpCountry> csvGdpCountryProcessor() {
        return new GdpCountryProcessor();
    }

    @Bean
    ItemProcessor<GdpGrowthRate, GdpGrowthRate> gdpRateCsvProcessor() {
        return new GdpRateProcessor();
    }

    @Bean
    public RepositoryItemWriter<GdpCountry> gdpCountryWriter() {
        RepositoryItemWriter<GdpCountry> gdpCountryWriter = new RepositoryItemWriter<GdpCountry>();
        gdpCountryWriter.setRepository(gdpCountryRepository);
        gdpCountryWriter.setMethodName("save");
        return gdpCountryWriter;
    }

    @Bean
    public RepositoryItemWriter<GdpGrowthRate> gdpRateWriter() {
        RepositoryItemWriter<GdpGrowthRate> gdpCountryWriter = new RepositoryItemWriter<GdpGrowthRate>();
        gdpCountryWriter.setRepository(gdpGrowthRepository);
        gdpCountryWriter.setMethodName("save");
        return gdpCountryWriter;
    }

    @Bean
    public Step gdpCountryCsvFileToDatabaseStep() {
        return stepBuilderFactory.get("csvFileToDatabaseStep")
                .<GdpCountry, GdpCountry>chunk(100)
                .reader(csvGdpCountryReader())
                .processor(csvGdpCountryProcessor())
                .writer(gdpCountryWriter())
                .build();
    }

    @Bean
    public Step gdpRateCsvFileToDatabaseStep() {
        return stepBuilderFactory.get("csvFileToDatabaseStep")
                .<GdpGrowthRate, GdpGrowthRate>chunk(100)
                .reader(gdpRateCsvReader())
                .processor(gdpRateCsvProcessor())
                .writer(gdpRateWriter())
                .build();
    }

    @Bean
    Job gdpCountryCsvFileToDatabaseJob(GdpCountryJobCompletionNotificationListener listener) {
        return jobBuilderFactory.get("csvFileToDatabaseJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(gdpCountryCsvFileToDatabaseStep())
                .end()
                .build();
    }

    @Bean
    Job gdpRateCsvFileToDatabaseJob(GdpRateJobCompletionNotificationListener listener) {
        return jobBuilderFactory.get("csvFileToDatabaseJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(gdpRateCsvFileToDatabaseStep())
                .end()
                .build();
    }
}
