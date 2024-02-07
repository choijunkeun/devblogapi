package jkchoi.devblogapi.repository;

import jkchoi.devblogapi.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(String name);
}
