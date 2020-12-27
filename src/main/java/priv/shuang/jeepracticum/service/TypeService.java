package priv.shuang.jeepracticum.service;

import org.springframework.stereotype.Service;
import priv.shuang.jeepracticum.mapper.repository.BookRepository;
import priv.shuang.jeepracticum.mapper.repository.TypeRepository;
import priv.shuang.jeepracticum.model.Type;

import java.util.List;

@Service
public class TypeService {
    final BookRepository bookRepository;
    final TypeRepository typeRepository;

    public TypeService(BookRepository bookRepository, TypeRepository typeRepository) {
        this.bookRepository = bookRepository;
        this.typeRepository = typeRepository;
    }

    public List<Type> getTypes() {
        return typeRepository.findAll();
    }
}
