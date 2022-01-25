package com.example;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DynApplication implements CommandLineRunner {

    private static Logger LOG = LoggerFactory
            .getLogger(DynApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(DynApplication.class, args);
    }

    @Override
    public void run(String... args) {
        LOG.info("EXECUTING : command line runner");

        try {
//            BusinessPrioritiesDocument.Priority priority = new BusinessPrioritiesDocument.Priority();
//            priority.setPriority(1L);
//            priority.setDescription("descrertere");
//            priority.setTitle("skdjfshdufsdfs");
//            BusinessPrioritiesDocument document = new BusinessPrioritiesDocument();
//            document.setCustomerId("123123");
//            document.setPriorities(Collections.singletonList(priority));
//            dynamoDBMapper().save(document);
//            LOG.info("SUCCESS WRITE: ----------");
//            LOG.info(document.getCustomerId());

            DynamoDBMapper dynamoDBMapper = dynamoDBMapper();

            LOG.info("QUERY DATA (10 TIMES) START");
            for (int i = 0; i < 10; i++) {
                LOG.info("QUERY NO.{} START", i);
                BusinessPrioritiesDocument readDocument = dynamoDBMapper.load(BusinessPrioritiesDocument.class, "123123" + i);
                LOG.info("QUERY NO.{} END", i);
            }
            LOG.info("QUERY DATA (10 TIMES) END");

            LOG.info("SUCCESS READ: ----------");
//            LOG.info(readDocument.getCustomerId());
//            LOG.info(readDocument.getPriorities().get(0).getDescription());

        } catch (Exception e) {
            LOG.info("ERROR: ----------");
            LOG.info(e.getMessage());
        }
    }

    public DynamoDBMapper dynamoDBMapper() {
        LOG.info("CREATE MAPPER START");
        AmazonDynamoDB amazonDynamoDB = AmazonDynamoDBClientBuilder.standard()
                .withCredentials(new DefaultAWSCredentialsProviderChain())
//                .withRegion("us-west-2")
                .build();
        LOG.info("CREATE MAPPER END");
        return new DynamoDBMapper(amazonDynamoDB);
    }

}