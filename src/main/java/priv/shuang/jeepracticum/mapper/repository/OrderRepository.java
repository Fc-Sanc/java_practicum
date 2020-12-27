package priv.shuang.jeepracticum.mapper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import priv.shuang.jeepracticum.model.Order;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Jonathan
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query(value = "select * from `order` where user_id = :userId order by date desc limit :start, :size",
            nativeQuery = true)
    List<Order> getOrdersByUserId(Integer userId, Integer start, Integer size);

    @Query(value = "select count(*) from `order` where user_id = :userId", nativeQuery = true)
    Integer getOrderCountByUserId(Integer userId);

    @Query(value = "select count(*) from `order` where book_ISBN = :isbn", nativeQuery = true)
    Integer isBorrowed(String isbn);

    @Transactional
    @Modifying
    @Query(value = "insert into `order`(user_id, book_ISBN, date) " +
            "VALUES (:userId, :isbn, date_add(now(), interval 1 MONTH))",
            nativeQuery = true)
    Integer borrow(Integer userId, String isbn);

    @Transactional
    @Modifying
    @Query(value = "update `order` " +
            "set date = date_add(now(), interval 1 MONTH) where user_id = :userId and book_ISBN = :isbn",
            nativeQuery = true)
    Integer renewBook(Integer userId, String isbn);

    @Transactional
    @Modifying
    @Query(value = "delete from `order` where user_id = :userId and book_ISBN = :isbn", nativeQuery = true)
    Integer returnBook(Integer userId, String isbn);
}
