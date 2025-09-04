package br.edu.utfpr.pb.tcc.server.controller;

import br.edu.utfpr.pb.tcc.server.dto.UserDto;
import br.edu.utfpr.pb.tcc.server.model.User;
import br.edu.utfpr.pb.tcc.server.service.ICrudService;
import br.edu.utfpr.pb.tcc.server.service.UserService;
import br.edu.utfpr.pb.tcc.server.shared.GenericResponse;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
public class UserController extends CrudController<User, UserDto, Long> {

    private final UserService userService;
    private final MessageSource messageSource;
    private final ModelMapper modelMapper;

    public UserController(UserService userService, MessageSource messageSource, ModelMapper modelMapper) {
        super(User.class, UserDto.class);
        this.userService = userService;
        this.messageSource = messageSource;
        this.modelMapper = modelMapper;
    }

    @Override
    protected ICrudService<User, Long> getService() {
        return userService;
    }

    @Override
    protected ModelMapper getModelMapper() {
        return modelMapper;
    }

    @PostMapping
    public GenericResponse createUser(@Valid @RequestBody UserDto userDto) {
        userService.save(userDto);
        return GenericResponse.of(messageSource.getMessage("br.edu.pb.utfpr.tcc.server.user.created", null, LocaleContextHolder.getLocale()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GenericResponse> deleteUser(@PathVariable Long id) {
        if (userService.safeDelete(id)) {
            return ResponseEntity.ok(GenericResponse.of("Usuário excluído com sucesso!"));
        } else {
            String deleteAdminViolationMessage = messageSource.getMessage("br.edu.pb.utfpr.tcc.server.user.deleteAdmin.violation", null, LocaleContextHolder.getLocale());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(GenericResponse.of(deleteAdminViolationMessage));
        }
    }
}