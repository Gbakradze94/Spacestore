package com.onlinestore.admin.repository;

import com.onlinestore.common.entity.Brand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BrandRepository extends SearchRepository<Brand, Integer> {

    // Review findAll (Built in methods)

    public Long countById(Integer id);

    //public Optional<Brand> findByName(String name);
    Brand findByName(String name);

    @Query("SELECT b FROM Brand b WHERE b.name LIKE %?1%")
    Page<Brand> findAll(String keyword, Pageable pageable);

    @Query("SELECT NEW Brand(b.id, b.name) FROM Brand b ORDER BY b.name ASC")
    List<Brand> findAll();
}