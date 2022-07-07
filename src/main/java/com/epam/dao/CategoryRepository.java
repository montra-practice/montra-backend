package com.epam.dao;

import com.epam.data.Category;
import com.epam.data.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long>, JpaRepository<Category, Long> {
}
