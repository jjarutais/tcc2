package br.edu.utfpr.pb.tcc.server.service;

import br.edu.utfpr.pb.tcc.server.model.Category;
import br.edu.utfpr.pb.tcc.server.enumeration.CategoryType;
import java.util.List;

public interface ICategoryService extends ICrudService <Category, Long>{
    List<Category> findByType(CategoryType type);
}