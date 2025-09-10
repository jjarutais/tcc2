package br.edu.utfpr.pb.tcc.server.controller;

import br.edu.utfpr.pb.tcc.server.dto.UserDto;
import br.edu.utfpr.pb.tcc.server.service.UserService;
import br.edu.utfpr.pb.tcc.server.shared.GenericResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import br.edu.utfpr.pb.tcc.server.model.User;


@RestController
@RequestMapping("users")
public class UserController {

    private final UserService userService;
    private final MessageSource messageSource;

    public UserController(UserService userService, MessageSource messageSource) {
        this.userService = userService;
        this.messageSource = messageSource;
    }

    @PostMapping
    public GenericResponse createUser(@Valid @RequestBody UserDto userDto) {
        userService.save(userDto);
        return GenericResponse.of("Usuário salvo com sucesso!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenericResponse> updateUser(@PathVariable Long id, @Valid @RequestBody UserDto userDto) {
        if (userService.update(id, userDto)) {
            return ResponseEntity.ok(GenericResponse.of("Usuário atualizado com sucesso!"));
        } else {
            String notFoundMessage = messageSource.getMessage("br.edu.pb.utfpr.tcc.server.user.notFound", null, LocaleContextHolder.getLocale());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(GenericResponse.of(notFoundMessage));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GenericResponse> deleteUser(@PathVariable Long id) {
        if (userService.delete(id)) {
            return ResponseEntity.ok(GenericResponse.of("Usuário excluído com sucesso!"));
        } else {
            String notFoundMessage = messageSource.getMessage("br.edu.pb.utfpr.tcc.server.user.notFound", null, LocaleContextHolder.getLocale());
            String deleteAdminViolationMessage = messageSource.getMessage("br.edu.pb.utfpr.tcc.server.user.deleteAdmin.violation", null, LocaleContextHolder.getLocale());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(GenericResponse.of(deleteAdminViolationMessage));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findUserById(@PathVariable Long id) {
        User user = userService.findById(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/deactivate")
    public ResponseEntity<GenericResponse> deactivateUser(@PathVariable Long id) {
        if (userService.updateActiveStatus(id, false)) {
            return ResponseEntity.ok(GenericResponse.of("Usuário desativado com sucesso!"));
        } else {
            String notFoundMessage = messageSource.getMessage("br.edu.pb.utfpr.tcc.server.user.notFound", null, LocaleContextHolder.getLocale());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(GenericResponse.of(notFoundMessage));
        }
    }

}