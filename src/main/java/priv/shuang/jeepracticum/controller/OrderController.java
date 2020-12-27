package priv.shuang.jeepracticum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import priv.shuang.jeepracticum.service.OrderService;
import priv.shuang.jeepracticum.utils.JsonGenerator;

import javax.servlet.http.HttpSession;

/**
 * @author Jonathan
 */
@Controller
public class OrderController {

    final
    OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @ResponseBody
    @RequestMapping(value = {"/getOrders"})
    public String getOrders(HttpSession session, Integer page, Integer size) {
        Integer userId = (Integer) session.getAttribute("user_id");
        return JsonGenerator.toJson(orderService.getOrdersByUserId(userId, page, size));
    }

    @ResponseBody
    @RequestMapping(value = {"/getOrderPageNumber"}, method = {RequestMethod.GET})
    public String getOrderPageNumber(HttpSession session, Integer size) {
        Integer userId = (Integer) session.getAttribute("user_id");
        return JsonGenerator.toJson(orderService.getOrderPageNumber(userId, size));
    }

    @ResponseBody
    @RequestMapping(value = {"/isBorrowed"}, method = {RequestMethod.GET})
    public boolean isBorrowed(String isbn) {
        return orderService.isBorrowed(isbn);
    }

    @ResponseBody
    @RequestMapping(value = {"/borrow"}, method = {RequestMethod.GET})
    public boolean borrow(HttpSession session, String isbn) {
        Integer userId = (Integer) session.getAttribute("user_id");
        if (userId != null) {
            return orderService.addOrder(userId, isbn) != null;
        }
        return false;
    }

    @ResponseBody
    @RequestMapping(value = {"/renewBook"}, method = {RequestMethod.GET})
    public boolean renewBook(HttpSession session, String isbn) {
        Integer userId = (Integer) session.getAttribute("user_id");
        if (userId != null) {
            return orderService.renewBook(userId, isbn) != null;
        }
        return false;
    }

    @ResponseBody
    @RequestMapping(value = {"/returnBook"}, method = {RequestMethod.GET})
    public boolean returnBook(HttpSession session, String isbn) {
        Integer userId = (Integer) session.getAttribute("user_id");
        if (userId != null) {
            return orderService.returnBook(userId, isbn) != null;
        }
        return false;
    }
}
