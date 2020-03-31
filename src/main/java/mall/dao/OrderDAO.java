package mall.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import mall.pojo.Order;
import mall.pojo.User;

public interface OrderDAO extends JpaRepository<Order, Integer> {

	 public List<Order> findByUserAndStatusNotOrderByIdDesc(User user, String status);
}
