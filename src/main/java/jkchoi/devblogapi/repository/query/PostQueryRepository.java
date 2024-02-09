package jkchoi.devblogapi.repository.query;


import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jkchoi.devblogapi.dto.PostDto;
import jkchoi.devblogapi.entity.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

import static jkchoi.devblogapi.entity.QCategory.category;
import static jkchoi.devblogapi.entity.QPost.post;
import static jkchoi.devblogapi.entity.QCategoryPost.categoryPost;

@Repository
@RequiredArgsConstructor
public class PostQueryRepository {
    private final EntityManager em;

    private JPAQueryFactory queryFactory;

    public Slice<PostDto.ReadPostsResponse> findPostsByTag(String tag, Pageable pageable) {
        queryFactory = new JPAQueryFactory(em);

        int pageSize = pageable.getPageSize();

        List<Post> findPosts = queryFactory
                .select(post)
                .from(post)
                .join(post.categoryPost, categoryPost).fetchJoin()
                .join(categoryPost.category, category).fetchJoin()
                .where(hasTagName(tag))
                .limit(pageSize + 1)
                .orderBy(post.lastModifiedDate.desc())
                .fetch();

        boolean hasNext = false;
        if(findPosts.size() > pageSize) {
            findPosts.remove(pageSize);
            hasNext = true;
        }

        List<PostDto.ReadPostsResponse> result = findPosts.stream()
                .map(findPost -> new PostDto.ReadPostsResponse(findPost)).collect(Collectors.toList());

        return new SliceImpl<>(result, pageable, hasNext);
    }

    // 태그이름 존재하는지 체크,
    private BooleanExpression hasTagName(String tag) {
        if("".equals(tag)) {
           return null;
        }

        return category.tagName.eq(tag);
    }

}
