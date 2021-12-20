package com.onlinestore.admin;

import org.junit.jupiter.api.Test;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.ListObjectsRequest;
import software.amazon.awssdk.services.s3.model.ListObjectsResponse;
import software.amazon.awssdk.services.s3.model.S3Object;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.ListIterator;
import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.in;

class AmazonS3UtilTests {

    Logger logger = Logger.getLogger("info");
    private static final String BUCKET_NAME = "AWS_BUCKET_NAME";

    @Test
    void testListFolder() {

        String folderName = "product-images/1";
        List<String> listKeys = AmazonS3Util.listFolder(folderName);
        listKeys.forEach(System.out::println);
        AmazonS3Util.listFolder(folderName);

    }

    @Test
    void testUploadFile() throws FileNotFoundException {

        String folderName = "test-upload";
        String fileName = "Spacestore.JPG";
        String filePath =  "C:\\Users\\U\\Desktop\\Spacestore Project\\" + fileName;

        InputStream inputStream = new FileInputStream(filePath);
        AmazonS3Util.uploadFile(folderName,fileName,inputStream);
    }

    @Test
    void testDeleteFile(){
        String fileName = "test-upload/Spacestore.JPG";
        AmazonS3Util.deleteFile(fileName);
    }

    @Test
    void testDeleteFolder(){
        String folderName = "test-upload";
        AmazonS3Util.removeFolder(folderName);
    }
}
