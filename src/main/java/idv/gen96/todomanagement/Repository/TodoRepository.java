package idv.gen96.todomanagement.Repository;

import idv.gen96.todomanagement.Entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
}
