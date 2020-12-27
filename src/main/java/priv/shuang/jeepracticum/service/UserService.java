package priv.shuang.jeepracticum.service;

import org.springframework.stereotype.Service;
import priv.shuang.jeepracticum.mapper.repository.UserRepository;
import priv.shuang.jeepracticum.model.User;
import priv.shuang.jeepracticum.utils.PasswordEncrypt;

@Service
public class UserService {

    final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Integer login(String username, String password) {
        String passwordHashed = PasswordEncrypt.encrypt(password);
        User   user           = userRepository.login(username, passwordHashed);
        if (user != null) {
            return user.getId();
        }
        return -1;
    }

    public User getUserById(Integer id) {
        if (id != null) {
            return userRepository.getUser(id);
        }
        return null;
    }

    public String getUsername(Integer id) {
        if (id != null) {
            return userRepository.getUsername(id);
        }
        return null;
    }

    public Integer updateUser(Integer id, Integer age, String contactInfo, String gender) {
        if (id != null && age != null && contactInfo != null && gender != null) {
            return userRepository.updateUser(id, age, contactInfo, gender);
        }
        return null;
    }

    public Integer updatePassword(Integer id, String password) {
        if (id != null && password != null) {
            String passwordHashed = PasswordEncrypt.encrypt(password);
            return userRepository.updatePassword(id, passwordHashed);
        }
        return null;
    }

    public Integer register(String username, String password, String age, String contactInfo, String gender) {
        if (username == null || password == null || age == null || contactInfo == null || gender == null) {
            return null;
        }
        String passwordHashed = PasswordEncrypt.encrypt(password);
        return userRepository.register(username, passwordHashed, age, contactInfo, gender);
    }

    public boolean exists(String username) {
        return userRepository.existsByUsername(username);
    }
}
