package jkchoi.devblogapi.repository;

import jkchoi.devblogapi.entity.CategoryPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryPostRepository extends JpaRepository<CategoryPost, Long> {
}
