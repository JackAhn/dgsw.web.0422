package kr.hs.dgsw.demo.Controller;

import kr.hs.dgsw.demo.Domain.Post;
import kr.hs.dgsw.demo.Domain.User;
import kr.hs.dgsw.demo.Protocol.AttachmentProtocol;
import kr.hs.dgsw.demo.Repository.PostRepository;
import kr.hs.dgsw.demo.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLConnection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@RestController
public class AttachmentController {

    @Autowired
    private PostRepository postRepository;

    @PostMapping("/attachment")
    public AttachmentProtocol upload(@RequestPart MultipartFile srcFile){
        String destFilename = "D:\\Spring_2\\hodol0418\\upload\\"
                + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy_MM_dd\\"))
                + UUID.randomUUID().toString() + "_"
                + srcFile.getOriginalFilename();
        try{
            File file = new File(destFilename);
            file.getParentFile().mkdirs();
            srcFile.transferTo(file);
            return new AttachmentProtocol(destFilename, srcFile.getOriginalFilename());
        }catch(Exception e){
            System.out.println("에러" + e);
            return null;
        }
    }

    @GetMapping("/attachment/{id}")
    public void download(HttpServletRequest request , HttpServletResponse response, @PathVariable Long id){
        String filepath = null;
        String filename = null;
        Post p = null;
        File file = null;
        p = this.postRepository.findById(id).get();
        if(p.getStoragePath() == null)
            return;
        filepath = p.getStoragePath();
        filename = p.getOriginalName();
        file = new File(filepath);
        String mimeType = URLConnection.guessContentTypeFromName(file.getName());
        if (mimeType == null)
            mimeType = "application/octet-stream";

        response.setContentType(mimeType);
        response.setHeader("Content-Disposition", "inline; filename=\"" + filename + "\"");
        response.setContentLength((int) file.length());
        try {
            InputStream is = new BufferedInputStream(new FileInputStream(file));
            FileCopyUtils.copy(is, response.getOutputStream());
        } catch (Exception e) {
            //System.out.println(e.getMessage());
        }
    }

}
