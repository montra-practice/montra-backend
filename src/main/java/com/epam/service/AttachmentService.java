package com.epam.service;

import com.epam.data.Attachment;
import com.epam.data.Category;
import com.epam.utils.Result;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public interface AttachmentService {
    Result<Long> uploadAttachment(MultipartFile file) throws IOException;

    Optional<Attachment> getAttachment(Long id);
}
