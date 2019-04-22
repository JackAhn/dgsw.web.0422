package kr.hs.dgsw.demo.Service;

import kr.hs.dgsw.demo.Domain.User;

import java.util.List;

public interface UserService {
    User chkLogin(String id, String pw);
    int addUser(User user);
    User modifyUser(User user);
    boolean removeUser(String id);
}
