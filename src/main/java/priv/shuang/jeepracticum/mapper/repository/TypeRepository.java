package priv.shuang.jeepracticum.mapper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import priv.shuang.jeepracticum.model.Type;

/**
 * @author 11206
 */
@Repository
public interface TypeRepository extends JpaRepository<Type, Integer> {

    Type getByName(String name);
}
