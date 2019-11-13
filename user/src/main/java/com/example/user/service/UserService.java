package com.example.user.service;

import org.springframework.stereotype.Service;

/**
 * @author zhou.fan
 */
@Service
//@Transactional(rollbackFor = Exception.class)
public class UserService {
    /*@Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findUserByUserName(String userName) {
        return userRepository.findUserByUserName(userName);
    }

    public User login(User user) {
        return userRepository.findUserByUserNameAndUserPass(user.getUserName(), user.getUserPass());
    }*/
}
