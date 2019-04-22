package kr.hs.dgsw.demo.Repository;

import kr.hs.dgsw.demo.Domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
