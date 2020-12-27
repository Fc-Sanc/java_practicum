package priv.shuang.jeepracticum.mapper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import priv.shuang.jeepracticum.model.Book;
import priv.shuang.jeepracticum.model.Type;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Jonathan
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    @Query(value = "select b from Book b where b.type = :type")
    List<Book> getBooksByType(Type type);

    @Query(value = "select b from Book b order by b.sales desc")
    List<Book> getBooksBestSales();

    @Query(value = "select b from Book b where b.isbn = :isbn")
    Book getBookByIsbn(String isbn);

    @Query(value = "select b.name from Book b where b.isbn = :isbn")
    String getNameByIsbn(String isbn);

    @Query(value = "select book.* from book, type " +
            "where type.id = type_id " +
            "and (ISBN like :keyword " +
            "or book.name like :keyword " +
            "or author like :keyword " +
            "or type.name like :keyword) " +
            "limit :start, :size",
            nativeQuery = true)
    List<Book> getPageOfBooksByKeyword(int start, Integer size, String keyword);

    @Query(value = "select * from book where type_id = :typeId limit :start, :size",
            nativeQuery = true)
    List<Book> getPageOfBooksByTypeId(Integer start, Integer size, Integer typeId);

    @Query(value = "select count(*) from book where type_id = :typeId", nativeQuery = true)
    Integer getBookNumberByTypeId(Integer typeId);

    @Query(value = "select count(*) from book, type " +
            "where type.id = type_id " +
            "and (ISBN like :keyword " +
            "or book.name like :keyword " +
            "or author like :keyword " +
            "or type.name like :keyword) ",
            nativeQuery = true)
    Integer getBookNumberByKeyword(String keyword);

    @Transactional
    @Modifying
    @Query(value = "update book set sales = sales + 1 where ISBN = :isbn", nativeQuery = true)
    Integer increaseSales(String isbn);
}
