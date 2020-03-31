package mall.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import mall.dao.OrderDAO;
import mall.pojo.Order;
import mall.pojo.OrderItem;
import mall.pojo.User;
import mall.util.OrderType;
import mall.util.Page4Navigator;

@Service
@CacheConfig(cacheNames="orders")
public class OrderService {

	@Autowired
	OrderDAO orderDAO;
	@Autowired
	OrderItemService orderItemService;

	@Cacheable(key="'orders-page-'+#start+ '-' + #size")
	public Page4Navigator<Order> list(int start, int size) {
		Sort sort = new Sort(Sort.Direction.DESC, "id");
		Pageable pageable = new PageRequest(start, size,sort);
		Page<Order> pageFromJPA =orderDAO.findAll(pageable);
		return new Page4Navigator<>(pageFromJPA,size);
	}

	// 创建订单，业务方法
	@Transactional(propagation = Propagation.REQUIRED, rollbackForClassName = "Exception")
	public float createOrder(Order order, List<OrderItem> ois) {
		float total = 0;
		add(order);

		if (false)
			throw new RuntimeException();

		for (OrderItem oi : ois) {
			oi.setOrder(order);
			orderItemService.update(oi);
			total += oi.getProduct().getPromotePrice() * oi.getNumber();
		}
		return total;
	}

	@Cacheable(key="orders-all")
	public List<Order> list() {
		Sort sort = new Sort(Sort.Direction.ASC, "id");
		return orderDAO.findAll(sort);
	}
	

	@CacheEvict(allEntries=true)
	public void add(Order bean) {
		orderDAO.save(bean);
	}

	@CacheEvict(allEntries=true)
	public void delete(int id) {
		orderDAO.delete(id);
	}

	@Cacheable(key="'orders-one-'+ #id")
	public Order get(int id) {
		Order order = orderDAO.findOne(id);
		return order;
	}

	@CacheEvict(allEntries=true)
	public void update(Order order) {
		orderDAO.save(order);
	}

	public List<Order> listByUserWithoutDelete(User user) {
		List<Order> orders = listByUserAndNotDeleted(user);
		// 填充订单项
		orderItemService.fillItems(orders);
		return orders;
	}

	/**
	 * 找到状态不是删除的订单
	 * 
	 * @param user
	 * @return
	 */
	@Cacheable(key="'orders-listByUserAndNotDeleted-'+user.id")
	private List<Order> listByUserAndNotDeleted(User user) {
		return orderDAO.findByUserAndStatusNotOrderByIdDesc(user, OrderType.DELETE.toString());
	}

	/**
	 * 为订单计算总价
	 * @param order
	 */
	public void setTotalPrice(Order order) {
		List<OrderItem> orderItems = order.getOrderItems();
		float total = 0;
		for (OrderItem orderItem : orderItems) {
			total += orderItem.getProduct().getPromotePrice() * orderItem.getNumber();
		}
		order.setTotalPrice(total);
	}
}
