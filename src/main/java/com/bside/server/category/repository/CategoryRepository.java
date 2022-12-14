package com.bside.server.category.repository;

import com.bside.server.category.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Integer > {
    Optional<Category> findByCategoryId(Integer categoryId);
}
