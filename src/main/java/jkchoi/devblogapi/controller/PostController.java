package jkchoi.devblogapi.controller;


import jakarta.validation.Valid;
import jkchoi.devblogapi.dto.PostDto;
import jkchoi.devblogapi.entity.Category;
import jkchoi.devblogapi.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping("/post")
    public ResponseEntity<PostDto.CreateResponse> addPost(@RequestBody @Valid PostDto.CreateRequest request) {
        PostDto.CreateResponse response = postService.savePost(request); // Post & Category 저장

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/post/{id}")
    public ResponseEntity<PostDto.ReadResponse> getPost(@PathVariable("id") Long id) {
        PostDto.ReadResponse response = postService.readPost(id);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/posts")
    public ResponseEntity<Slice<PostDto.ReadPostsResponse>> getPosts(@RequestParam(name = "tag") String tag, Pageable pageable) {
        Slice<PostDto.ReadPostsResponse> response = postService.readPosts(tag, pageable);

        for (PostDto.ReadPostsResponse readPostsResponse : response) {
            System.out.println("readPostsResponse = " + readPostsResponse.getTitle());
        }

        return ResponseEntity.ok(response);
    }

}
