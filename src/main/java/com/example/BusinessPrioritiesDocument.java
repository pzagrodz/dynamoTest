package com.example;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.Data;

import java.util.List;

@Data
@DynamoDBTable(tableName = "expert-engagement-business-priorities-cluster01")
public class BusinessPrioritiesDocument {

    @DynamoDBHashKey(attributeName = "projectId")
    private String customerId;
    @DynamoDBAttribute(attributeName = "priorities")
    private List<Priority> priorities;

    @Data
    @DynamoDBDocument
    public static class Priority {
        Long priority;
        String title;
        String description;
    }
}
