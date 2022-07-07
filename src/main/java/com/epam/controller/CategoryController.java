package com.epam.controller;

import com.epam.data.Category;
import com.epam.service.CategoryService;
import com.epam.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.epam.constant.Constants.API_PREFIX;

/**
 * @description: Category
 * @author: Nickt
 * @date: 2022/7/6
 */
@RestController
@Api(tags = "CategoryController")
@Slf4j
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @ApiOperation(value = "get categories", notes = "get all categories")
    @GetMapping
    public Result<List<Category>> getAllCategories() {
        if (log.isInfoEnabled()) {
            log.info("call get all categories");
        }
        List<Category> allCategories = categoryService.getAllCategories();
        return Result.success(allCategories);
    }

}
