package kr.hs.dgsw.demo.Domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private String content;

    @Column
    private String storagePath;

    @Column
    private String originalName;

    @CreationTimestamp
    @Column(updatable = false, nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime created;

    @UpdateTimestamp
    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime modified;

    public Post(){

    }

    public Post(Long userId, String content) {
        this.userId = userId;
        this.content = content;
    }

    public Post(Post post){
        this.id = post.id;
        this.userId = post.userId;
        this.content = post.content;
        this.storagePath = post.storagePath;
        this.originalName = post.originalName;
        this.created = post.created;
        this.modified = post.modified;
    }

}
