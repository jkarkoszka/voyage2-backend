package pl.edu.pja.gdansk.voyage2.storage.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.transfer.TransferManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import pl.edu.pja.gdansk.voyage2.storage.service.exception.UploadException;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Component
public class StorageService {

    @Autowired
    private AmazonS3 amazonS3;
    private TransferManager transferManager;

    @PostConstruct
    private void postConstuct() {
        transferManager = new TransferManager(this.amazonS3);
    }

    public void uploadFile(String fileName, MultipartFile multipartFile) {
        try {
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(multipartFile.getContentType());
            objectMetadata.setContentLength(multipartFile.getSize());
            objectMetadata.setHeader("filename", multipartFile.getOriginalFilename());
            transferManager.upload("voyage2", fileName, multipartFile.getInputStream(), objectMetadata);
        } catch (IOException e) {
            throw new UploadException();
        }
    }
}
