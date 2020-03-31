package mall.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import mall.pojo.*;

public interface PropertyValueDAO extends JpaRepository<PropertyValue, Integer> {
	List<PropertyValue> findByProductOrderByIdDesc(Product product);
	PropertyValue getByPropertyAndProduct(Property property,Product product);
}
