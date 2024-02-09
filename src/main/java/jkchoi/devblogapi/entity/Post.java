package jkchoi.devblogapi.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Post extends BaseTimeEntity{
    @Id @GeneratedValue
    @Column(name = "POST_ID")
    private Long id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @OneToMany(mappedBy = "post")
    private List<CategoryPost> categoryPost = new ArrayList<>();

    @Builder
    public Post(String title, String content) {
        this.title = title;
        this.content = content;
    }

//    public static Post createPostInstance(String title, String content) {
//        Post post = Post.builder().title(title).content(content).build();
//        return post;
//    }


    // 비즈니스 로직
    public void addCategoryPost(CategoryPost categoryPost) {
        this.categoryPost.add(categoryPost);
    }



}
