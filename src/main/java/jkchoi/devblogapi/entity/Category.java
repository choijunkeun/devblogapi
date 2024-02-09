package jkchoi.devblogapi.entity;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Category {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CATEGORY_ID")
    private Long id;

    @Column(nullable = false)
    private String name;

    private String tagName;

    @OneToMany(mappedBy = "category")
    private List<CategoryPost> categoryPost = new ArrayList<>();

    private int postCount;  // 카테고리에 속한 게시글 개수

    @Builder
    public Category(String name, String tagName, int postCount) {
        this.name = name;
        this.tagName = tagName;
        this.postCount = postCount;
    }

    // 카테고리 생성 메서드
    public static Category createCategory(Category category) {
        Category categoryInstance =
                Category.builder()
                        .name(category.getName())
                        .tagName(category.tagName)
                        .postCount(1)
                        .build();
        return categoryInstance;
    }

    // 게시글 업데이트
    public void updatePostCount(int count) {
        this.postCount += count;
    }

    // 비즈니스로직
}
