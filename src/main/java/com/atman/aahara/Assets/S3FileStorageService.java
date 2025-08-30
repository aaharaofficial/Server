package com.atman.aahara.Assets;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;
import software.amazon.awssdk.core.async.AsyncRequestBody;
import software.amazon.awssdk.services.s3.S3AsyncClient;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class S3FileStorageService implements FileStorageService {

    private final S3AsyncClient s3AsyncClient;

    @Value("${aws.s3.bucket-name}")
    private String bucketName;

    @Value("${aws.cloudfront.domain:}")
    private String cloudFrontDomain;

    @Override
    public String uploadFile(MultipartFile file, String folder) throws IOException {
        String key = folder + "/" + UUID.randomUUID() + "-" + file.getOriginalFilename();

        if (file.getSize() < 5 * 1024 * 1024) { // <5MB, small file, use in-memory
            uploadSmallFile(file, key);
        } else { // large file
            uploadLargeFile(file, key);
        }

        return key;
    }

    private void uploadSmallFile(MultipartFile file, String key) throws IOException {
        byte[] bytes = file.getBytes();
        Flux<ByteBuffer> publisher = Flux.just(ByteBuffer.wrap(bytes));

        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .acl("public-read")
                .contentType(file.getContentType())
                .build();

        s3AsyncClient.putObject(request, AsyncRequestBody.fromPublisher(publisher))
                .whenComplete((resp, ex) -> logCompletion(key, ex));
    }

    private void uploadLargeFile(MultipartFile file, String key) throws IOException {
        Path tempFile = Files.createTempFile("upload-", "-" + file.getOriginalFilename());
        file.transferTo(tempFile.toFile());

        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .acl("public-read")
                .contentType(file.getContentType())
                .build();

        s3AsyncClient.putObject(request, AsyncRequestBody.fromFile(tempFile))
                .whenComplete((resp, ex) -> {
                    try { Files.deleteIfExists(tempFile); } catch (IOException e) { e.printStackTrace(); }
                    logCompletion(key, ex);
                });
    }

    private void logCompletion(String key, Throwable ex) {
        if (ex != null) {
            System.err.println("S3 operation failed for key: " + key);
            ex.printStackTrace();
        } else {
            System.out.println("S3 operation completed for key: " + key);
        }
    }

    @Override
    public void deleteFile(String key) {
        DeleteObjectRequest request = DeleteObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();

        s3AsyncClient.deleteObject(request)
                .whenComplete((resp, ex) -> logCompletion(key, ex));
    }

    @Override
    public String getFileUrl(String key) {
        if (cloudFrontDomain != null && !cloudFrontDomain.isEmpty()) {
            return "https://" + cloudFrontDomain + "/" + key;
        }
        return "https://s3.amazonaws.com/" + bucketName + "/" + key;
    }
}
