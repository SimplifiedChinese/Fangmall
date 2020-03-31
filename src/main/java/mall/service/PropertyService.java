package mall.service;
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import mall.util.Page4Navigator;
import mall.dao.PropertyDAO;
import mall.pojo.Property;
import mall.pojo.Category;
 
@Service
public class PropertyService {
    @Autowired PropertyDAO propertyDAO;
    @Autowired CategoryService categoryservice;
    
    public Page4Navigator<Property> list(int cid, int start, int size, int navigatePages) {
    	Category category = categoryservice.get(cid);
    	
		Sort sort = new Sort(Sort.Direction.DESC, "id");
		Pageable pageable = new PageRequest(start, size, sort);
		
		Page<Property> pageFromJPA = propertyDAO.findByCategory(category, pageable);

		return new Page4Navigator<>(pageFromJPA,navigatePages);
	}

	public void add(Property bean) {
		propertyDAO.save(bean);
	}

	public void delete(int id) {
		propertyDAO.delete(id);
	}

	public Property get(int id) {
		Property c= propertyDAO.findOne(id);
		return c;
	}
	public void update(Property bean) {
		propertyDAO.save(bean);
	}
	
	//新加的，用于值的修改
	public List<Property> listByCategory(Category category) {
		return propertyDAO.findByCategory(category);
	}
}