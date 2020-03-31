package mall.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import mall.dao.UserDAO;
import mall.pojo.User;
import mall.util.Page4Navigator;

@Service
@CacheConfig(cacheNames="users")
public class UserService {
	
	@Autowired UserDAO userDAO;

	public Page4Navigator<User> list(int start, int size) {
		Sort sort = new Sort(Sort.Direction.DESC, "id");
		Pageable pageable = new PageRequest(start, size,sort);
		Page<User> pageFromJPA =userDAO.findAll(pageable);
		return new Page4Navigator<>(pageFromJPA,size);
	}

	public void add(User user) {
		userDAO.save(user);
	}

	public void delete(int id) {
		userDAO.delete(id);
	}

	public void update(User user) {
		userDAO.save(user);
	}

	public User get(int id) {
		return userDAO.findOne(id);
	}
	
}
