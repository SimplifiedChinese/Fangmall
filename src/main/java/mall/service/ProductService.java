package mall.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import mall.dao.ProductDAO;
import mall.pojo.Category;
import mall.pojo.Product;
import mall.util.Page4Navigator;


@Service
public class ProductService {

	@Autowired ProductDAO productDAO;
	@Autowired CategoryService categoryService;
	@Autowired ProductImageService productImageService;
	
	public Page4Navigator<Product> list(int cid, int start, int size, int navigatePages) {
		Category category = categoryService.get(cid);
    	
		Sort sort = new Sort(Sort.Direction.DESC, "id");
		Pageable pageable = new PageRequest(start, size, sort);
		
		Page<Product> pageFromJPA = productDAO.findByCategory(category, pageable);

		return new Page4Navigator<>(pageFromJPA,navigatePages);
	}
	
	public void add(Product product) {
		productDAO.save(product);
	}

	public void delete(int id) {
		productDAO.delete(id);
	}
	
	public void update(Product product) {
		productDAO.save(product);
	}

	public Product get(int id) {
		return productDAO.findOne(id);
	}
	
	public void fill(List<Category> categorys) {
	for(Category category:categorys)
		fill(category);
	}
	
	public void fill(Category category) {
		List<Product> products = listByCategory(category);
		productImageService.setFirstProdutImages(products);
		category.setProducts(products);
	}

	private List<Product> listByCategory(Category category) {
		return productDAO.findByCategoryOrderById(category);
	}
	
	public void fillByRow(List<Category> categorys){
		int productNumberEachRow = 8 ;
		for ( Category category:categorys ) {
			List<Product> products = category.getProducts();
			List<List<Product>> productsByRow = new ArrayList<>();
			for ( int i = 0 ; i<products.size(); i+=productNumberEachRow) {
				int size = i + productNumberEachRow;
				size = size > products.size()?products.size():size;
				List<Product> productsofEachRow = products.subList(i,size);
				productsByRow.add(productsofEachRow);
			}
			category.setProductsByRow(productsByRow);
		}
	}
}