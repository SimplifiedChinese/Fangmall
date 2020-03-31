package mall.dao;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import mall.pojo.Product;
import mall.pojo.ProductImage;

public interface ProductImageDAO extends JpaRepository<ProductImage, Integer> {
	List<ProductImage> findByProductAndTypeOrderByIdDesc(Product product, String type);
}