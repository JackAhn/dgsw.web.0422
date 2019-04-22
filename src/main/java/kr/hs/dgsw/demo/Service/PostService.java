package kr.hs.dgsw.demo.Service;

import kr.hs.dgsw.demo.Domain.Post;
import kr.hs.dgsw.demo.Protocol.PostUsernameProtocol;

import java.util.List;

public interface PostService {
    List<PostUsernameProtocol> listAllPosts();
    PostUsernameProtocol addPost(Post post);
    Post findPost(Long id);
    Post modifyPost(Post post);
    boolean deletePost(Long id);
}
