package priv.shuang.jeepracticum.service;

import org.springframework.stereotype.Service;
import priv.shuang.jeepracticum.mapper.repository.BookRepository;
import priv.shuang.jeepracticum.mapper.repository.OrderRepository;
import priv.shuang.jeepracticum.model.Order;

import java.util.List;

/**
 * @author Jonathan
 */
@Service
public class OrderService {

    final OrderRepository orderRepository;
    final BookRepository  bookRepository;

    public OrderService(OrderRepository orderRepository, BookRepository bookRepository) {
        this.orderRepository = orderRepository;
        this.bookRepository = bookRepository;
    }

    public List<Order> getOrdersByUserId(Integer userId, Integer page, Integer size) {
        if (userId != null && userId != -1 && page != null && size != null) {
            Integer start = size * (page - 1);
            return orderRepository.getOrdersByUserId(userId, start, size);
        }
        return null;
    }

    public Integer getOrderPageNumber(Integer userId, Integer size) {
        if (userId != null && userId != -1 && size != null) {
            Integer number = orderRepository.getOrderCountByUserId(userId);
            return (int) Math.ceil(((double) number / size));
        }
        return null;
    }

    public Integer addOrder(Integer userId, String isbn) {
        Integer res = orderRepository.borrow(userId, isbn);
        if (res != null && res > 0) {
            bookRepository.increaseSales(isbn);
        }
        return res;
    }

    public Integer renewBook(Integer userId, String isbn) {
        if (userId != null && isbn != null) {
            return orderRepository.renewBook(userId, isbn);
        }
        return null;
    }

    public Integer returnBook(Integer userId, String isbn) {
        if (userId != null && isbn != null) {
            return orderRepository.returnBook(userId, isbn);
        }
        return null;
    }

    public void deleteOrder(Integer id) {
        if (id != null && id != -1) {
            orderRepository.deleteById(id);
        }
    }

    public boolean isBorrowed(String isbn) {
        return orderRepository.isBorrowed(isbn) > 0;
    }
}
