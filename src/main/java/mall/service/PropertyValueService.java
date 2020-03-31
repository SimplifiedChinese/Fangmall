package mall.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import mall.dao.PropertyValueDAO;
import mall.pojo.Product;
import mall.pojo.Property;
import mall.pojo.PropertyValue;

@Service
@CacheConfig(cacheNames="propertyValues")
public class PropertyValueService {

	@Autowired PropertyValueDAO propertyValueDAO;
	@Autowired ProductService productService;
	@Autowired PropertyService propertyService;


	public void add(PropertyValue bean) {
        propertyValueDAO.save(bean);
    }
	
	public void delete(int id) {
        propertyValueDAO.delete(id);
    }


	public void update(PropertyValue bean) {
        propertyValueDAO.save(bean);
    }

	public PropertyValue get(int id) {
       return propertyValueDAO.findOne(id);
    }
	

	public List<PropertyValue> list(int pid){
		Product product = productService.get(pid);
		return propertyValueDAO.findByProductOrderByIdDesc(product);
	}

	public void init(int pid) {
		Product product = productService.get(pid);
		List<Property> propertys= propertyService.listByCategory(product.getCategory());
		for (Property property: propertys) {
			PropertyValue propertyValue = getByPropertyAndProduct(product, property);
			if(null==propertyValue){
				propertyValue = new PropertyValue();
				propertyValue.setProduct(product);
				propertyValue.setProperty(property);
				propertyValueDAO.save(propertyValue);//如果是第一次执行，相当于add，否则相当于update
			}
		}
	}

	private PropertyValue getByPropertyAndProduct(Product product, Property property) {
		return propertyValueDAO.getByPropertyAndProduct(property, product);
	}

}
