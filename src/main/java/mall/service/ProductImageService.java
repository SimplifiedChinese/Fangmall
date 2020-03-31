package mall.service;

import java.util.List;

import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Service;

import mall.dao.ProductImageDAO;
//import mall.pojo.OrderItem;
import mall.pojo.Product;
import mall.pojo.ProductImage;
import mall.util.ProductImageType;


@Service
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
public class ProductImageService {

	@Autowired ProductImageDAO productImageDAO;
	@Autowired ProductService productService;
	
	public List<ProductImage> listBriefProductImages(int pid) {
		Product product = productService.get(pid);
		return productImageDAO.findByProductAndTypeOrderByIdDesc(product,ProductImageType.BRIEF.toString());
	}

	public List<ProductImage> listDetailProductImages(int pid) {
		Product product = productService.get(pid);
		return productImageDAO.findByProductAndTypeOrderByIdDesc(product, ProductImageType.DETAIL.toString());
	}

	public void add(ProductImage productImage) {
		productImageDAO.save(productImage);
	}

	public void delete(int id) {
		productImageDAO.delete(id);
	}
	
	public void update(ProductImage productImage) {
		productImageDAO.save(productImage);
	}

	public ProductImage get(int id) {
		return productImageDAO.findOne(id);
	}

	public void setFirstProductImage(Product product) {
		ProductImageService productImageService = (ProductImageService)AopContext.currentProxy();
		
		List<ProductImage> briefImages = productImageService.listBriefProductImages(product.getId());
		if(!briefImages.isEmpty())
			product.setFirstProductImage(briefImages.get(0));
		else
			product.setFirstProductImage(new ProductImage()); //这样做是考虑到产品还没有来得及设置图片，但是在订单后台管理里查看订单项的对应产品图片。

	}

	public void setFirstProdutImages(List<Product> products) {
		for (Product product:products) {
			setFirstProductImage(product);
		}
	}	
}
