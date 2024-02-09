package jkchoi.devblogapi.dto;

import jkchoi.devblogapi.entity.Category;
import jkchoi.devblogapi.entity.Post;
import lombok.Data;

public class CategoryDto {

    @Data
    public static class ReadResponse {
        private Long id;
        private String name;
        private String tagName;
        private int postCount;

        public ReadResponse(Category category) {
            this.id = category.getId();
            this.name = category.getName();
            this.tagName = category.getTagName();
            this.postCount = category.getPostCount();
        }
    }
}
