package br.edu.utfpr.pb.tcc.server.config;

import br.edu.utfpr.pb.tcc.server.model.User;
import br.edu.utfpr.pb.tcc.server.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminUserInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminUserInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.existsByRole("ADMIN")) {
            return;
        }

        User admin = User.builder()
                .username("admin")
                .displayName("Administrator")
                .password(passwordEncoder.encode("Admin@123"))
                .email("admin@example.com")
                .role("ADMIN")
                .active(true)
                .build();
        userRepository.save(admin);
    }
}