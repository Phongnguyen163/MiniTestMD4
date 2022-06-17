package repository;

import model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    Iterable<Post> findAllByTitle(String Title);

    @Query(value = "from Post order by likes desc")
    Iterable<Post> sortByLikes();

    @Query(value = "select * from minitest.Post order by createAt desc limit 4", nativeQuery = true)
    Iterable<Post> findByCreateAt();
}
