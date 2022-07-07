package com.epam.service.impl;

import com.epam.dao.AttachmentRepository;
import com.epam.dao.CategoryRepository;
import com.epam.data.Attachment;
import com.epam.data.Category;
import com.epam.service.AttachmentService;
import com.epam.service.CategoryService;
import com.epam.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class AttachmentServiceImpl implements AttachmentService {

    @Autowired
    private AttachmentRepository attachmentRepository;

    @Override
    public Result<Long> uploadAttachment(MultipartFile file) throws IOException {

        Attachment attachment = new Attachment();
        attachment.setName(file.getOriginalFilename());
        attachment.setType(file.getContentType());
        attachment.setAttachByte(file.getBytes());

        attachmentRepository.save(attachment);

        return Result.success(attachment.getId());
    }

    @Override
    public Optional<Attachment> getAttachment(Long id) {
        return attachmentRepository.findById(id);
    }
}
