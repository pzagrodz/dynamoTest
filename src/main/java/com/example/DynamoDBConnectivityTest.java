package com.example;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

@Log4j2
@Component
@ConditionalOnProperty(name = "test.dynamodb", havingValue = "true")
public class DynamoDBConnectivityTest implements CommandLineRunner {

    @Override
    public void run(String... args) {
        log.info("EXECUTING : command line runner DynamoDB connectivity test");

        try {
            DynamoDBMapper dynamoDBMapper = dynamoDBMapper();

            log.info("QUERY DATA (10 TIMES) START");
            for (int i = 0; i < 10; i++) {
                log.info("QUERY NO.{} START", i);
                BusinessPrioritiesDocument readDocument = dynamoDBMapper.load(BusinessPrioritiesDocument.class, "123123" + i);
                log.info("QUERY NO.{} END", i);
            }
            log.info("QUERY DATA (10 TIMES) END");

            log.info("SUCCESS READ: ----------");
//        log.info(readDocument.getCustomerId());
//        log.info(readDocument.getPriorities().get(0).getDescription());}
        } catch (Exception e) {
            log.info("ERROR DynamoDB: ----------");
            log.info(e.getMessage());
        }
    }

    public DynamoDBMapper dynamoDBMapper() {
        log.info("CREATE DYNAMODB MAPPER START");
        AmazonDynamoDB amazonDynamoDB = AmazonDynamoDBClientBuilder.standard()
                .withCredentials(new DefaultAWSCredentialsProviderChain())
//                .withRegion("us-west-2")
                .build();
        log.info("CREATE DYNAMODB MAPPER END");
        return new DynamoDBMapper(amazonDynamoDB);
    }
}
