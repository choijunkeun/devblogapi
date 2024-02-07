package jkchoi.devblogapi.dto;

import jkchoi.devblogapi.entity.Category;
import jkchoi.devblogapi.entity.Post;
import lombok.Data;

public class CategoryDto {

    @Data
    public static class ReadResponse {
        private Long id;
        private String name;
        private int postCount;

        public ReadResponse(Category category) {
            this.id = category.getId();
            this.name = category.getName();
            this.postCount = category.getPostCount();
        }
    }
}
