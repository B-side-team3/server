package com.bside.server.module.category.repository;

import com.bside.server.module.category.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Integer > {
    Optional<Category> findById(Integer categoryId);
}
