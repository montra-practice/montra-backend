package com.epam.service.impl;

import com.epam.AbstractTest;
import com.epam.data.Category;
import com.epam.service.CategoryService;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;


class CategoryServiceImplTest extends AbstractTest {

    @Autowired
    private CategoryService categoryService;

    @Test
    @DatabaseSetup(value = "/data/categories.xml")
    void testGetAllCategories() {

        List<Category> list = categoryService.getAllCategories();

        assertThat(list.size(), equalTo(2));
    }
}
