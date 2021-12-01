package com.project.challenge.service.file;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.InputStream;

@Component
@RequiredArgsConstructor
public class AwsS3FileUpload implements FileUpload {

    private final AmazonS3Client amazonS3;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Value("${cloud.aws.region.static}")
    private String region;

    @Override
    public void uploadFile(InputStream inputStream, ObjectMetadata objectMetadata, String filename) {
        System.out.println("region = " + amazonS3.getRegion());
        System.out.println("region = " + region);
        amazonS3.putObject(new PutObjectRequest(bucket, filename, inputStream, objectMetadata).withCannedAcl(CannedAccessControlList.PublicReadWrite));
    }

    @Override
    public void uploadThumbnailFile(InputStream inputStream, ObjectMetadata objectMetadata, String filename) {

    }

    @Override
    public String getFileUrl(String filename) {
        return amazonS3.getUrl(bucket, filename).toString();
    }
}
