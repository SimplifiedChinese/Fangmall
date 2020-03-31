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

import mall.pojo.Product;
import mall.service.ProductService;
import mall.util.Page4Navigator;

@RestController
public class ProductController {

	@Autowired ProductService productService;
	
	@GetMapping("/categories/{cid}/products")
	public Page4Navigator<Product> list(@PathVariable("cid") int cid, @RequestParam(value = "start", defaultValue = "0") int start, @RequestParam(value = "size", defaultValue = "5") int size) throws Exception {
		start = start<0?0:start;
		Page4Navigator<Product> page =productService.list(cid, start, size, 5);  //5表示导航分页最多有5个，像 [1,2,3,4,5] 这样
		return page;
	}
	
	@GetMapping("/products/{id}")
	public Object get(@PathVariable(name="id")int id) {
		return productService.get(id);
	}

	@DeleteMapping("/products/{id}")
	public Object delete(@PathVariable(name="id")int id ) {
		productService.delete(id);
		return null;
	}
	
	@PutMapping("/products/{id}")
	public Object update(@RequestBody Product product) {
		productService.add(product);
		return product;
	}
	
	@PostMapping("/products")
	public Object add(@RequestBody Product product) {
		productService.add(product);
		return product;
	}
}
