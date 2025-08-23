package br.edu.utfpr.pb.tcc.server.service;

import br.edu.utfpr.pb.tcc.server.dto.UserDto;
import br.edu.utfpr.pb.tcc.server.model.User;
import br.edu.utfpr.pb.tcc.server.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User save(UserDto userDto) {
        User user = User.builder()
                .username(userDto.getUsername())
                .displayName(userDto.getDisplayName())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .build();
        
        return userRepository.save(user);
    }

    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}