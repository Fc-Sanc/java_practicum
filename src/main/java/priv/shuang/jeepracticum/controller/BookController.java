package priv.shuang.jeepracticum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import priv.shuang.jeepracticum.model.Book;
import priv.shuang.jeepracticum.service.BookService;
import priv.shuang.jeepracticum.utils.JsonGenerator;

import java.util.List;

/**
 * @author Jonathan
 */
@CrossOrigin
@Controller
public class BookController {
    final
    BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    @ResponseBody
    @RequestMapping(value = {"/getBooks"}, method = {RequestMethod.GET})
    public String getBooks(Integer typeId) {
        List<Book> books;
        if (typeId != null) {
            books = bookService.getBooksByTypeId(typeId);
        } else {
            books = bookService.getBooks();
        }

        return JsonGenerator.toJson(books);
    }

    @ResponseBody
    @RequestMapping(value = {"/getPageOfBooks"}, method = {RequestMethod.GET})
    public String getPageOfBooks(Integer page, Integer size, Integer typeId, String keyword) {
        keyword = "%" + keyword + "%";
        return JsonGenerator.toJson(bookService.getPageOfBooks(page, size, typeId, keyword));
    }

    @ResponseBody
    @RequestMapping(value = {"/getPageNumber"}, method = {RequestMethod.GET})
    public String getPageNumber(Integer typeId, String keyword, Integer size) {
        keyword = "%" + keyword + "%";
        return JsonGenerator.toJson(bookService.getPageNumber(typeId, keyword, size));
    }

    @ResponseBody
    @RequestMapping(value = {"/getBooksTop12"}, method = {RequestMethod.GET})
    public String getBooksTop12() {
        return JsonGenerator.toJson(bookService.getBooksTop12());
    }

    @ResponseBody
    @RequestMapping(value = {"/getBookByIsbn"}, method = {RequestMethod.GET})
    public String getBookByIsbn(String isbn) {
        return JsonGenerator.toJson(bookService.getBookByIsbn(isbn));
    }

    @ResponseBody
    @RequestMapping(value = {"/getNameByIsbn"}, method = {RequestMethod.GET})
    public String getNameByIsbn(String isbn) {
        return JsonGenerator.toJson(bookService.getNameByIsbn(isbn));
    }
}
