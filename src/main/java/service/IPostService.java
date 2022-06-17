package service;

import model.Post;

public interface IPostService extends IGeneralService<Post>{
    Iterable<Post> findAllByTitle(String Title);
    Iterable<Post> sortByLikes();
    Iterable<Post> findByCreateAt();
}
