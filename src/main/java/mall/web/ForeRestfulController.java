package mall.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import mall.pojo.*;
import mall.service.*;
import mall.util.*;

@RestController
public class ForeRestfulController{
	@Autowired CategoryService categoryService;
	@Autowired ProductService productService;
	
	@GetMapping("/forehome")
	public Object home() {
		List<Category> cs = categoryService.list();
		productService.fill(cs);
		productService.fillByRow(cs);
		categoryService.removeCategoryFromProduct(cs);
		return cs;
	}
}