package org.opefito.batchrecon.application;

import javax.sql.DataSource;

import org.opefito.batchrecon.beans.CCPStatement;
import org.opefito.batchrecon.beans.XeroStatement;
import org.opefito.batchrecon.processors.CCPStatementItemProcessor;
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
import org.springframework.batch.item.file.transform.PassThroughLineAggregator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.core.JdbcTemplate;

//@Configuration
//@EnableBatchProcessing
public class CCPBatchConfiguration {

	// tag::readerwriterprocessor[]
	@Bean
	public ItemReader<CCPStatement> reader() {
		FlatFileItemReader<CCPStatement> reader = new FlatFileItemReader<CCPStatement>();
		// reader.setRecordSeparatorPolicy(RecordSeparatorPolicy.);
		reader.setResource(new ClassPathResource("Download_738713_1442925832884.csv"));
		reader.setLineMapper(new DefaultLineMapper<CCPStatement>() {
			{
				setLineTokenizer(new DelimitedLineTokenizer() {
					{
						//Date comptable;Description;Montant de l'opération;Devise;Date valeur;Compte de la contrepartie;Nom de la contrepartie :;Communication 1 :;Communication 2 :;Référence d'opération
						setNames(new String[] { "dateComptable", "Description", "amount", "Devise", "valueDate",
								"CounterPartAccount", "CounterPartName", "Communication1", "Communication2", "operationReference" });
						setDelimiter(";");
					}
				});
				setFieldSetMapper(new BeanWrapperFieldSetMapper<CCPStatement>() {
					{
						setTargetType(CCPStatement.class);
					}
				});
			}
		});
		return reader;
	}

	@Bean
	public ItemProcessor<CCPStatement, XeroStatement> processor() {
		return new CCPStatementItemProcessor();
	}

	@Bean
	public ItemWriter<XeroStatement> writer() {
		
		FlatFileItemWriter<XeroStatement> writer = new FlatFileItemWriter<>();
		writer.setResource( new FileSystemResource("ccp-to-xero-statements.csv"));
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
	public Job importCCPStatmentJob(JobBuilderFactory jobs, Step s1, JobExecutionListener listener) {
		return jobs.get("importCCPStatmentJob").incrementer(new RunIdIncrementer()).listener(listener).flow(s1).end().build();
	}

	@Bean
	public Step ccpStep1(StepBuilderFactory stepBuilderFactory, ItemReader<CCPStatement> reader,
			ItemWriter<XeroStatement> writer, ItemProcessor<CCPStatement, XeroStatement> processor) {
		return stepBuilderFactory.get("ccpStep1").<CCPStatement, XeroStatement> chunk(10).reader(reader).processor(processor)
				.writer(writer).build();
	}
	// end::jobstep[]

	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

}
