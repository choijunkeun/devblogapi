package jkchoi.devblogapi.repository.query;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;
import jkchoi.devblogapi.entity.Category;
import jkchoi.devblogapi.entity.Post;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.SliceImpl;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static jkchoi.devblogapi.entity.QCategory.category;
import static jkchoi.devblogapi.entity.QCategoryPost.categoryPost;
import static jkchoi.devblogapi.entity.QPost.post;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class PostQueryRepositoryTest {
    @Autowired
    EntityManager em;

    @PersistenceUnit
    EntityManagerFactory emf;

    private BooleanExpression hasTagName(String tag) {
        System.out.println("tag = " + tag);
        if("".equals(tag)) {
            System.out.println("null");
            return null;
        }

        return category.name.eq(tag);
    }

    @Test
    void findPostsByTag() {
        PageRequest pageable = PageRequest.of(0,10);
        String tag = "Java";
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        int pageSize = pageable.getPageSize();
        System.out.println("pageSize = " + pageSize);

        List<Post> findPosts = queryFactory
                .select(post)
                .from(post)
                .join(post.categoryPost, categoryPost).fetchJoin()
                .join(categoryPost.category, category).fetchJoin()
                .where(hasTagName(tag))
                .limit(pageSize + 1)
                .fetch();

        String editTitle = "호호호";


        boolean hasNext = false;
        if(findPosts.size() > pageSize) {
            findPosts.remove(pageSize);
            hasNext = true;
        }

    }
}