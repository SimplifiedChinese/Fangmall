package mall.web;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import mall.util.Page4Navigator;

import mall.pojo.Property;
import mall.service.PropertyService;

import javax.servlet.http.HttpServletRequest;
  
@RestController
public class PropertyController {
    @Autowired PropertyService propertyService;
     
    @GetMapping("/categories/{cid}/properties")
	public Page4Navigator<Property> list(@PathVariable("cid") int cid, @RequestParam(value = "start", defaultValue = "0") int start, @RequestParam(value = "size", defaultValue = "5") int size) throws Exception {
		start = start<0?0:start;
		Page4Navigator<Property> page =propertyService.list(cid, start, size, 5);  //5表示导航分页最多有5个，像 [1,2,3,4,5] 这样
		return page;		
	}
    
    @GetMapping("/properties/{id}")
	public Property get(@PathVariable("id") int id) throws Exception {
		Property bean=propertyService.get(id);
		return bean;
	}
    
	@PostMapping("/properties")
	public Object add(@RequestBody Property bean) throws Exception {
		propertyService.add(bean);
		return bean;
	}	

	@DeleteMapping("/properties/{id}")
	public String delete(@PathVariable("id") int id, HttpServletRequest request)  throws Exception {
		propertyService.delete(id);
		return null;
	}

	
	@PutMapping("/properties")
	public Object update(@RequestBody Property bean) throws Exception {
		propertyService.update(bean);
		return bean;
	}
}