package kr.hs.dgsw.demo.Controller;

import kr.hs.dgsw.demo.Domain.User;
import kr.hs.dgsw.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/login/{id}/{pw}")
    public User login(@PathVariable String id, @PathVariable String pw){
        return this.userService.chkLogin(id, pw);
    }

    @PostMapping("/regis")
    public int addUser(@RequestBody User user){
        return this.userService.addUser(user);
    }

    @PutMapping("/modify")
    public User modify(@RequestBody User user){
        return this.userService.modifyUser(user);
    }


    @DeleteMapping("/delete/{id}")
    public boolean removeUser(@PathVariable String id){
        return this.userService.removeUser(id);
    }



}
