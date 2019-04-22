package kr.hs.dgsw.demo.Controller;

import kr.hs.dgsw.demo.Domain.Post;
import kr.hs.dgsw.demo.Protocol.PostUsernameProtocol;
import kr.hs.dgsw.demo.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/postlist")
    public List<PostUsernameProtocol> list(){
        return this.postService.listAllPosts();
    }

    @GetMapping("/postfind/{id}")
    public Post findComments(@PathVariable Long id) {
        return this.postService.findPost(id);
    }

    @PostMapping("/postadd")
    public PostUsernameProtocol addPost(@RequestBody Post post){
        return this.postService.addPost(post);
    }

    @PutMapping("/postmodify")
    public Post modify(@RequestBody Post post){
        return this.postService.modifyPost(post);
    }

    @DeleteMapping("/postdelete/{id}")
    public boolean removePost(@PathVariable Long id){
        return this.postService.deletePost(id);
    }

}
