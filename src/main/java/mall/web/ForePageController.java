package mall.web;

import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 用于负责前端的页面之前的跳转
 * 
 * @author 15037
 *
 */
@Controller
public class ForePageController {

	/**
	 * 访问主页
	 */
	@GetMapping("")
	public String fore() {
		return "redirect:home";
	}

	@GetMapping("home")
	public String home() {
		return "fore/home";
	}

	@GetMapping(value = "/regist")
	public String register() {
		return "fore/register";
	}

	@GetMapping(value="/registSuccess")
	public String registeSuccess() {
		return "fore/registSuccess";
	}
	
	@GetMapping(value="/login")
	public String login() {
		return "fore/login";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		 Subject subject = SecurityUtils.getSubject();
		    if(subject.isAuthenticated())
		        subject.logout();
		return "redirect:home";
	}
	
	@GetMapping("/foreproduct")
	public String product() {
		return "fore/product";
	}
	
	@GetMapping("/search")
    public String searchResult(){
        return "fore/searching";
    }
	
	@GetMapping("/foreCategory")
    public String searchCategory(){
        return "fore/searchingCategory";
    }
	
	/**
	 * 支付页面
	 * @return
	 */
	@GetMapping("/buy")
    public String buy(){
        return "fore/buy";
    }
	
	@GetMapping("/cart")
	public String cart() {
		return "fore/cart";
	}
	
	@GetMapping("/alipay")
	public String alipay() {
		return "fore/alipay";
	}
	
	@GetMapping("bought")
	public String bought() {
		return "fore/bought";
	}
	
	@GetMapping("confirmPay")
	public String confirmPay() {
		return "fore/confirmPay";
	}
	
	@GetMapping("orderConfirmed")
	public String orderConfirmed(){
		return "fore/orderConfirmed";
	}
	
	@GetMapping("review")
	public String review() {
		return "fore/review";
	}
}
