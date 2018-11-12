package com.coswafe.odyssey.service;


import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CopyObjectRequest;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;

import static org.apache.logging.log4j.util.Strings.isNotBlank;


@Component
@Slf4j
public class S3Service {

    @Value("${aws.s3.access.key.id}")
    private String accessKey;

    @Value("${aws.s3.access.key.secret}")
    private String secretKey;

    @Value("${aws.s3.base.bucket}")
    private String baseBucket;


    private AmazonS3 s3;


    @PostConstruct
    private void init() {
        s3 = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey)))
                .withRegion(Regions.AP_SOUTHEAST_1)
                .build();

    }

    public byte[] getFile(final String relativePath) throws IOException {
        S3Object object = s3.getObject(new GetObjectRequest(baseBucket, relativePath));
        return com.amazonaws.util.IOUtils.toByteArray(object.getObjectContent());
    }

    /**
     * Stores the file to the S3 bucket. <b>The relativePath should not have a leading slash</b>.<br>
     * Otherwise, it will create a blank folder. The folders and sub-folders will be created dynamically on the S3 bucket.
     * <p>
     * For eg.-<br>
     * a) images/1/profile_pic/my-profile-pic_1.jpg <br>
     * b) images/1/company_logo/company_logo_1.jpg <br>
     * c) images/1/cvs/Ram_1.docx
     *
     * @param relativePath Relative path to the location of the file with the file name.
     * @param inputStream
     */
    public void putFile(final String relativePath, final InputStream inputStream) {
        s3.putObject(new PutObjectRequest(baseBucket, relativePath, inputStream, null));
    }

    public void copyFile(final String sourceFilePath, final String destinationFilePath) {
        s3.copyObject(new CopyObjectRequest(baseBucket, sourceFilePath, baseBucket, destinationFilePath));
    }

    public void removeFile(final String filePath) {
        if (isNotBlank(filePath)) {
            s3.deleteObject(baseBucket, filePath);
        }
    }

    public boolean isFileExist(final String filePath) {
        boolean isFileExist = false;
        if (isNotBlank(filePath)) {
            isFileExist = s3.doesObjectExist(baseBucket, filePath);
        }
        return isFileExist;
    }


}