package br.edu.utfpr.pb.tcc.server.controller;

import br.edu.utfpr.pb.tcc.server.dto.CategoryDto;
import br.edu.utfpr.pb.tcc.server.model.Category;
import br.edu.utfpr.pb.tcc.server.service.ICategoryService;
import br.edu.utfpr.pb.tcc.server.service.ICrudService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("categories")
public class CategoryController extends CrudController<Category, CategoryDto, Long>{

    private final ICategoryService service;
    private final ModelMapper modelMapper;

    public CategoryController(ICategoryService service, ModelMapper modelMapper) {
        super(Category.class, CategoryDto.class);
        this.service = service;
        this.modelMapper = modelMapper;
    }

    @Override
    protected ICrudService<Category, Long> getService() {
        return service;
    }

    @Override
    protected ModelMapper getModelMapper() {
        return modelMapper;
    }

    @PostMapping
    public ResponseEntity<CategoryDto> create(@RequestBody @Valid CategoryDto entity) {
        Category savedEntity = getService().save(convertToEntity(entity));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedEntity.getId())
                .toUri();
        return ResponseEntity.created(location)
                .body(convertToDto(savedEntity));
    }

    private Category convertToEntity(CategoryDto entityDto) {
        return getModelMapper().map(entityDto, Category.class);
    }
}