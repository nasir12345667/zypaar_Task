package com.zypaar.repository;

import com.zypaar.entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {


    Optional<ProductImage> findByName(String name);
}
