package com.onlinestore.category;


import com.onlinestore.common.entity.Category;
import com.onlinestore.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class CategoryRepositoryTests {
    @Autowired
    private CategoryRepository repository;

    @Test
    public void listEnabledCategories(){
        List<Category> categories = repository.findAllEnabled();
        categories.forEach(category -> {
            System.out.println(category.getName() + " (" + category.isEnabled() + " )");
        });
    }

    @Test
    public void testFindCategoryByAlias(){
        String alias = "computers";
        Category category = repository.findByAliasEnabled(alias);

        assertThat(category).isNotNull();
    }

}
