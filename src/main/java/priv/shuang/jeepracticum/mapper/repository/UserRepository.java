package priv.shuang.jeepracticum.mapper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import priv.shuang.jeepracticum.model.User;

import javax.transaction.Transactional;

/**
 * @author Jonathan
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = "from User u where u.username = :username and u.password = :password")
    User login(String username, String password);

    @Query(value = "select u from User u where u.id = :id")
    User getUser(Integer id);

    @Query(value = "select u.username from User u where u.id = :id")
    String getUsername(Integer id);

    @Transactional
    @Modifying
    @Query(value = "update user set age = :age, contact_info = :contactInfo, gender = :gender where id = :id",
            nativeQuery = true)
    Integer updateUser(Integer id, Integer age, String contactInfo, String gender);

    @Transactional
    @Modifying
    @Query(value = "update user set password = :pwd where id = :id", nativeQuery = true)
    Integer updatePassword(Integer id, String pwd);

    @Transactional
    @Modifying
    @Query(value = "insert into user(username, password, age, contact_info, gender) " +
            "VALUES (:username, :password, :age, :contactInfo, :gender)",
            nativeQuery = true)
    Integer register(String username, String password, String age, String contactInfo, String gender);

    boolean existsByUsername(String username);
}
