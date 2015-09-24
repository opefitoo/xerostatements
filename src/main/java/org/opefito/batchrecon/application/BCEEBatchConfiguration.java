package org.opefito.batchrecon.application;

import javax.sql.DataSource;

import org.opefito.batchrecon.beans.BceeStatement;
import org.opefito.batchrecon.beans.XeroStatement;
import org.opefito.batchrecon.processors.ExpenseItemProcessor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
@EnableBatchProcessing
public class BCEEBatchConfiguration {

	// tag::readerwriterprocessor[]
	@Bean
	public ItemReader<BceeStatement> reader() {
		FlatFileItemReader<BceeStatement> reader = new FlatFileItemReader<BceeStatement>();
		// reader.setRecordSeparatorPolicy(RecordSeparatorPolicy.);
		reader.setResource(new ClassPathResource("bcee/HISTORY_LU40_0019_2255_7386_6000_20150923_0809.csv"));
		reader.setEncoding("utf-16");
		reader.setLineMapper(new DefaultLineMapper<BceeStatement>() {
			{
				setLineTokenizer(new DelimitedLineTokenizer() {
					{
						// setNames(new String[] { "Date transaction",
						// "Description", "Date valeur", "Montant en EUR",
						// "Extrait" ,"Solde", "Opération", "Communication1",
						// "Communication2", "Communication3" ,
						// "Communication4"});
						setNames(new String[] { "transactionDate", "Description", "valueDate", "amount", "statement",
								"balance", "Operation", "Communication1", "Communication2", "Communication3",
								"Communication4" });
						setDelimiter(";");
					}
				});
				setFieldSetMapper(new BeanWrapperFieldSetMapper<BceeStatement>() {
					{
						setTargetType(BceeStatement.class);
					}
				});
			}
		});
		return reader;
	}

	@Bean
	public ItemProcessor<BceeStatement, XeroStatement> processor() {
		return new ExpenseItemProcessor();
	}

	@Bean
	public ItemWriter<XeroStatement> writer() {
		
		FlatFileItemWriter<XeroStatement> writer = new FlatFileItemWriter<>();
		writer.setResource( new FileSystemResource("bcee-to-xero-statements.csv"));
    	DelimitedLineAggregator<XeroStatement> delLineAgg = new DelimitedLineAggregator<XeroStatement>();
    	delLineAgg.setDelimiter(",");
		
		//*Date,*Amount,Payee,Description,Reference,Check Number
		BeanWrapperFieldExtractor<XeroStatement> fieldExtractor = new BeanWrapperFieldExtractor<XeroStatement>();
    	fieldExtractor.setNames(new String[] {"date", "amount", "payee", "description", "reference", "checkNumber"});
    	delLineAgg.setFieldExtractor(fieldExtractor);
    	
    	writer.setLineAggregator(delLineAgg);
		return writer;
	}
	
	// tag::jobstep[]
	@Bean
	public Job importUserJob(JobBuilderFactory jobs, Step s1, JobExecutionListener listener) {
		return jobs.get("importUserJob").incrementer(new RunIdIncrementer()).listener(listener).flow(s1).end().build();
	}

	@Bean
	public Step step1(StepBuilderFactory stepBuilderFactory, ItemReader<BceeStatement> reader,
			ItemWriter<XeroStatement> writer, ItemProcessor<BceeStatement, XeroStatement> processor) {
		return stepBuilderFactory.get("step1").<BceeStatement, XeroStatement> chunk(10).reader(reader).processor(processor)
				.writer(writer).build();
	}
	// end::jobstep[]

	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

}
