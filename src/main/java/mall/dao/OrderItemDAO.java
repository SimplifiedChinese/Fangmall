package mall.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import mall.pojo.*;

public interface OrderItemDAO  extends JpaRepository<OrderItem, Integer>{

	List<OrderItem> findByOrderOrderByIdDesc(Order order);

	List<OrderItem> findByUserOrderByIdDesc(User user);

	List<OrderItem> findByUserAndOrderIsNull(User user);
}
