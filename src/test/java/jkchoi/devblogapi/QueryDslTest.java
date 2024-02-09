package jkchoi.devblogapi;


import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;
import jkchoi.devblogapi.entity.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@Transactional
public class QueryDslTest {
    @Autowired
    EntityManager em;


    @PersistenceUnit
    EntityManagerFactory emf;

    @Test
    public void searchPost() {
        QPost p = new QPost("post");
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        Post post = queryFactory
                .select(p)
                .from(p)
                .where(p.id.eq(1L))
                .fetchOne();

        boolean loaded = emf.getPersistenceUnitUtil().isLoaded(post.getCategoryPost());
        assertThat(loaded).as("페치조인미적용").isFalse();
    }

    @Test
    public void searchPostsFetchJoin() {
        QPost p = new QPost("post");
        QCategoryPost cp = new QCategoryPost("categroy_post");
        QCategory c = new QCategory("category");

        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        Post findPost = queryFactory
                .selectFrom(p)
                .join(p.categoryPost, cp).fetchJoin()
                .where(p.id.eq(1L))
                .fetchOne();


        boolean loaded = emf.getPersistenceUnitUtil().isLoaded(findPost.getCategoryPost());
        assertThat(loaded).as("페치조인적용").isTrue();
    }


}
