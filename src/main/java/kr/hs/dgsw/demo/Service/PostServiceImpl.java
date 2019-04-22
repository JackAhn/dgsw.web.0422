package kr.hs.dgsw.demo.Service;

import kr.hs.dgsw.demo.Domain.Post;
import kr.hs.dgsw.demo.Domain.User;
import kr.hs.dgsw.demo.Protocol.PostUsernameProtocol;
import kr.hs.dgsw.demo.Repository.PostRepository;
import kr.hs.dgsw.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public List<PostUsernameProtocol> listAllPosts() {
        List<Post> postList = this.postRepository.findAll();
        List<PostUsernameProtocol> cupList = new ArrayList<>();
        postList.forEach(post -> {
            Optional<User> found = this.userRepository.findById(post.getUserId());
            String username = (found.isPresent()) ? found.get().getUsername() : null;
            cupList.add(new PostUsernameProtocol(post, username));
        });
        return cupList;
    }

    @Override
    public PostUsernameProtocol addPost(Post post) {
        Optional<User> user = this.userRepository.findById(post.getUserId());
        if(user.isPresent()){
            this.postRepository.save(post);
            return new PostUsernameProtocol(post, user.get().getUsername());
        }
        else{
            return null;
        }
    }

    @Override
    public Post findPost(Long id) {
        return this.postRepository.findById(id).get();
    }

    @Override
    public Post modifyPost(Post post) {
        Post modify = this.postRepository.findById(post.getId())
                .map(found -> {
                    found.setContent(Optional.ofNullable(post.getContent()).orElse(found.getContent()));
                    found.setStoragePath(Optional.ofNullable(post.getStoragePath()).orElse(found.getStoragePath()));
                    found.setOriginalName(Optional.ofNullable(post.getOriginalName()).orElse(found.getOriginalName()));
                    return this.postRepository.save(found);
                })
                .orElse(null);
        return new PostUsernameProtocol(modify, this.userRepository.findById(post.getUserId()).get().getUsername());
    }

    @Override
    public boolean deletePost(Long id) {
        try{
            this.postRepository.deleteById(id);
            return true;
        }catch(Exception e){
            return false;
        }
    }
}
