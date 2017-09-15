package net.blueapple.domain.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Lithium on 9/15/2017.
 */
@Service
public class UserService {
    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository,PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User add(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
    public void delete(User user) {
        userRepository.delete(user);
    }

    // Getter & Setter
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    public List<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable).getContent();
    }

}
