package com.platform.file;

import com.platform.tenant.TenantContext;
import io.minio.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.UUID;

@Service
public class FileService {
    private final MinioClient minioClient;
    public FileService(MinioClient minioClient){ this.minioClient = minioClient; }

    public String upload(MultipartFile file) throws Exception {
        String tenant = TenantContext.getCurrentTenant();
        if(tenant == null) tenant = "default";
        String bucket = "platform-files";
        String objectName = tenant + "/" + UUID.randomUUID() + "-" + file.getOriginalFilename();

        boolean exists = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucket).build());
        if(!exists){
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucket).build());
        }

        minioClient.putObject(
            PutObjectArgs.builder()
                .bucket(bucket)
                .object(objectName)
                .stream(file.getInputStream(), file.getSize(), -1)
                .contentType(file.getContentType())
                .build()
        );
        return "/" + bucket + "/" + objectName;
    }
}