package service.impl;

import model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import repository.PostRepository;
import service.IPostService;


import java.util.List;
import java.util.Optional;


public class PostService implements IPostService {
    @Autowired
    PostRepository postRepository;

    @Override
    public Iterable<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public Optional<Post> findById(Integer id) {
        return postRepository.findById(id);
    }

    @Override
    public void save(Post post) {
        postRepository.save(post);
    }

    @Override
    public void remove(Integer id) {
        postRepository.deleteById(id);
    }

    public Iterable<Post> findAllByTitle(String title) {
        return postRepository.findAllByTitle(title);
    }

    @Override
    public Iterable<Post> sortByLikes() {
        return postRepository.sortByLikes();
    }

    @Override
    public Iterable<Post> findByCreateAt() {
        return postRepository.findByCreateAt();
    }
}
