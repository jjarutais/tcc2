package br.edu.utfpr.pb.tcc.server.service;

import br.edu.utfpr.pb.tcc.server.dto.UserDto;
import br.edu.utfpr.pb.tcc.server.model.User;
import br.edu.utfpr.pb.tcc.server.model.UserRole;
import br.edu.utfpr.pb.tcc.server.repository.UserRepository;
import br.edu.utfpr.pb.tcc.server.service.impl.CrudServiceImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService extends CrudServiceImpl<User, Long> {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected JpaRepository<User, Long> getRepository() {
        return userRepository;
    }

    public User save(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setDisplayName(userDto.getDisplayName());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRole(userDto.getRole());
        user.setActive(true);

        return userRepository.save(user);
    }

    public boolean update(Long id, UserDto userDto) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setUsername(userDto.getUsername());
            user.setDisplayName(userDto.getDisplayName());
            if (userDto.getPassword() != null && !userDto.getPassword().isEmpty()) {
                user.setPassword(passwordEncoder.encode(userDto.getPassword()));
            }
            userRepository.save(user);
            return true;
        }
        return false;
    }

    public boolean safeDelete(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (user.getRole() == UserRole.ROLE_ADMIN) {
                return false;
            }
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }


    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
}