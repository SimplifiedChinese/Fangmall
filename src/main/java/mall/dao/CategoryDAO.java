package mall.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import mall.pojo.Category;
 
public interface CategoryDAO extends JpaRepository<Category,Integer>{
 
}