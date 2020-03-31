package mall.test;
import java.util.List;
 
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import mall.Application;
import mall.dao.*;
import mall.pojo.*;
import mall.util.Page4Navigator;
import mall.util.ProductImageType;
import mall.service.*;
 
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class TestJPA {
	@Autowired ProductImageService productImageService;
	@Autowired ProductImageDAO productImageDAO;
	@Autowired ProductService productService;

     
    @Test
    public void test() {
    	Product product = productService.get(87);
    	List<ProductImage> a = productImageDAO.findByProductAndTypeOrderByIdDesc(product,ProductImageType.BRIEF.toString());
    	System.out.println(a.size());
    	if(ProductImageType.BRIEF.toString().equals(ProductImageType.BRIEF.toString())) {
			int b = productImageService.listBriefProductImages(87).size();
			System.out.println(b);
		}
    }
}