package com.onlinestore.admin;

import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.ListObjectsRequest;
import software.amazon.awssdk.services.s3.model.ListObjectsResponse;
import software.amazon.awssdk.services.s3.model.S3Object;

import java.util.List;
import java.util.ListIterator;

public class AmazonS3Util {
    private static final String BUCKET_NAME;

    static {
        BUCKET_NAME = System.getenv("AWS_BUCKET_NAME");
    }

    public static void listFolder(String folderName){
        S3Client client = S3Client.builder().build();
        ListObjectsRequest listRequest = ListObjectsRequest.builder()
                .bucket(BUCKET_NAME).prefix(folderName).build();

        ListObjectsResponse response = client.listObjects(listRequest);

        List<S3Object> contents = response.contents();
        ListIterator<S3Object> listIterator = contents.listIterator();

        while (listIterator.hasNext()){
            S3Object object = listIterator.next();
            System.out.println("Key: " + object.key());
        }
    }
}
