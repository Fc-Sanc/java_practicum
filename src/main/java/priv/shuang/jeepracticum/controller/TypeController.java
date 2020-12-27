package priv.shuang.jeepracticum.controller;

import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import priv.shuang.jeepracticum.service.BookService;
import priv.shuang.jeepracticum.service.TypeService;
import priv.shuang.jeepracticum.utils.JsonGenerator;

/**
 * @author Jonathan
 */
@CrossOrigin
@Controller
public class TypeController {

    final BookService bookService;
    final TypeService typeService;

    public TypeController(BookService bookService, TypeService typeService) {
        this.bookService = bookService;
        this.typeService = typeService;
    }

    @ResponseBody
    @RequestMapping(value = {"getTypes"}, method = {RequestMethod.GET})
    public String getTypes() {
        return JsonGenerator.toJson(typeService.getTypes());
    }
}
