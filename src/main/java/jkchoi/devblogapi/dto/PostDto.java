package jkchoi.devblogapi.dto;

import jakarta.validation.constraints.NotEmpty;
import jkchoi.devblogapi.entity.Category;
import jkchoi.devblogapi.entity.CategoryPost;
import jkchoi.devblogapi.entity.Post;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PostDto {
    @Data
    public static class CreateRequest {
        private String title;
        private String content;
        private List<Category> category = new ArrayList<>();

        // Post Dto -> Post Entity
        public Post toPostEntity() {
            return Post.builder()
                    .title(title)
                    .content(content).build();
        }
    }

    @Data
    public static class CreateResponse {
        private Long id;

        public CreateResponse(Post post) {
            this.id = post.getId();
        }
    }

    @Data
    public static class ReadResponse {
        private Long id;
        private String title;
        private String content;
        private List<Category> category;

        public ReadResponse(Post post) {
            this.id = post.getId();
            this.title = post.getTitle();
            this.content = post.getContent();
        }
    }

    @Data
    public static class PostListResponse {
        private Long id;
        private String title;
        private String content;

    }




}
