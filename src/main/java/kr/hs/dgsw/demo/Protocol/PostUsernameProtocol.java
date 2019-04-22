package kr.hs.dgsw.demo.Protocol;

import kr.hs.dgsw.demo.Domain.Post;
import lombok.Data;

@Data
public class PostUsernameProtocol extends Post {

    private String username;

    public PostUsernameProtocol(Post post, String username){
        super(post);
        this.username=username;
    }

}
