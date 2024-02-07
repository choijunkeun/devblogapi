package jkchoi.devblogapi.service;


import jkchoi.devblogapi.dto.PostDto;
import jkchoi.devblogapi.entity.Category;
import jkchoi.devblogapi.entity.CategoryPost;
import jkchoi.devblogapi.entity.Post;
import jkchoi.devblogapi.repository.CategoryPostRepository;
import jkchoi.devblogapi.repository.CategoryRepository;
import jkchoi.devblogapi.repository.PostRepository;
import jkchoi.devblogapi.repository.query.PostQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;
    private final CategoryPostRepository categoryPostRepository;
    private final PostQueryRepository postQueryRepository;


    // FIXME : 기능은 되지만 중복코드 리팩토링,최적화 필요.
    @Transactional
    public PostDto.CreateResponse savePost(PostDto.CreateRequest request) {
        Post post = request.toPostEntity();
        Post savePost = postRepository.save(post);    // post 저장

        for (Category category : request.getCategory()) {
            Category findCategory = categoryRepository.findByName(category.getName());

            if(findCategory == null) {  // 새로운 카테고리일 경우
                category = Category.createCategory(category);
                categoryRepository.save(category);
                CategoryPost categoryPost = new CategoryPost(category, post);
                post.addCategoryPost(categoryPost);
                categoryPostRepository.save(categoryPost);
            } else {    // 이미 카테고리가 있으면,
                CategoryPost categoryPost = new CategoryPost(findCategory, post);
                findCategory.updatePostCount(1);
                post.addCategoryPost(categoryPost);
                categoryPostRepository.save(categoryPost);
            }
        }
        return new PostDto.CreateResponse(savePost);
    }

    public PostDto.ReadResponse readPost(Long id) {
        Post findPost = postRepository.findById(id).orElseThrow(() -> {
            throw new IllegalStateException("게시글이 존재하지 않습니다.");
        });

        return new PostDto.ReadResponse(findPost);
    }

    public Slice<Category> readPosts(String tag, Pageable pageable) {
        Slice<Category> findPostsByTag = postQueryRepository.findPostsByTag(tag, pageable);

        return findPostsByTag;
    }



}
