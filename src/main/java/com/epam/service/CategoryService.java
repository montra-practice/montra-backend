package com.epam.service;

import com.epam.data.Category;
import com.epam.data.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {

    List<Category> getAllCategories();

}
