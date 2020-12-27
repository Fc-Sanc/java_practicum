package priv.shuang.jeepracticum.service;

import org.springframework.stereotype.Service;
import priv.shuang.jeepracticum.mapper.repository.BookRepository;
import priv.shuang.jeepracticum.mapper.repository.TypeRepository;
import priv.shuang.jeepracticum.model.Book;
import priv.shuang.jeepracticum.model.Type;

import java.util.List;

/**
 * @author Jonathan
 */
@Service
public class BookService {
    final BookRepository bookRepository;
    final TypeRepository typeRepository;

    public BookService(BookRepository bookRepository, TypeRepository typeRepository) {
        this.bookRepository = bookRepository;
        this.typeRepository = typeRepository;
    }

    public List<Book> getBooksByTypeId(Integer typeId) {
        Type type = typeRepository.getOne(typeId);
        return bookRepository.getBooksByType(type);
    }

    public List<Book> getBooksByTypeName(String typeName) {
        Type type = typeRepository.getByName(typeName);
        return bookRepository.getBooksByType(type);
    }

    public List<Book> getBooksTop12() {
        return bookRepository.getBooksBestSales().subList(0, 12);
    }

    public List<Book> getPageOfBooks(Integer page, Integer size, Integer typeId, String keyword) {
        if (page != null & size != null) {
            int start = size * (page - 1);
            if (typeId != null) {
                return bookRepository.getPageOfBooksByTypeId(start, size, typeId);
            }
            if (keyword == null) {
                keyword = "%%";
            }
            return bookRepository.getPageOfBooksByKeyword(start, size, keyword);
        }
        return null;
    }

    public Integer getPageNumber(Integer typeId, String keyword, Integer size) {
        Integer number;
        if (typeId != null) {
            number = bookRepository.getBookNumberByTypeId(typeId);
        } else {
            if (keyword == null) {
                keyword = "%%";
            }
            number = bookRepository.getBookNumberByKeyword(keyword);
        }
        return (int) Math.ceil(((double) number / size));
    }

    public Book getBookByIsbn(String isbn) {
        return bookRepository.getBookByIsbn(isbn);
    }

    public String getNameByIsbn(String isbn) {
        return bookRepository.getNameByIsbn(isbn);
    }

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    public Book save(Book book) {
        return bookRepository.save(book);
    }
}
