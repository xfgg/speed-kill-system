package com.xf.demo.repository;

import com.xf.demo.model.AyProduct;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author xfgg
 * 负责商品的CRUD
 */
public interface AyProductRepository extends JpaRepository<AyProduct,Integer> {
}
