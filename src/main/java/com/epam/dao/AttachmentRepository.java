package com.epam.dao;

import com.epam.data.Attachment;
import com.epam.data.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttachmentRepository extends CrudRepository<Attachment, Long>, JpaRepository<Attachment, Long> {
}
