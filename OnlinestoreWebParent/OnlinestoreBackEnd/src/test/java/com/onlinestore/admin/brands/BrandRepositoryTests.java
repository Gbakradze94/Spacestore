package com.onlinestore.admin.brands;

import static org.assertj.core.api.Assertions.assertThat;

import static org.assertj.core.api.Assertions.assertThat;

import com.onlinestore.admin.repository.BrandRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.onlinestore.common.entity.Brand;
import com.onlinestore.common.entity.Category;



@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class BrandRepositoryTests {
    @Autowired
    private BrandRepository brandRepository;

//    @Test
//    public void testCreateBrand(){
//        Brand brand = new Brand(1,"Acer","brand-logo.png");
//        Brand createdBrand = brandRepository.save(brand);
//
//        assertThat(createdBrand.getId()).isGreaterThan(0);
//    }

    @Test
    public void testCreateBrandsWithTwoCategories(){
        Category cellphones = new Category(4);
        Category tablets = new Category(7);

        Brand apple = new Brand("Apple");
        apple.getCategories().add(cellphones);
        apple.getCategories().add(tablets);

        Brand createdBrand = brandRepository.save(apple);

        assertThat(createdBrand).isNotNull();
        assertThat(createdBrand.getId()).isGreaterThan(0);
    }

    @Test
    public void testFindAll(){
        Iterable<Brand> brands = brandRepository.findAll();
        brands.forEach(System.out::println);

        assertThat(brands).isNotEmpty();
    }
    @Test
    public void testGetById() {
        Brand brand = brandRepository.findById(5).get();

        assertThat(brand.getName()).isEqualTo("Acer");
    }
}
