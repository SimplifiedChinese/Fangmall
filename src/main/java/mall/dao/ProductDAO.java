package mall.dao;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import mall.pojo.Category;
import mall.pojo.Product;

public interface ProductDAO extends JpaRepository<Product,Integer> {
	Page<Product> findByCategory(Category category,Pageable pageable);
	List<Product> findByCategoryOrderById(Category category);
}