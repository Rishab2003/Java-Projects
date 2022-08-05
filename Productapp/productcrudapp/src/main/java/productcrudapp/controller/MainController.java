package productcrudapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.servlet.view.RedirectView;

import productcrudapp.dao.ProductDao;
import productcrudapp.model.Product;

@Controller
public class MainController {
	
	@Autowired
	public ProductDao productDao;
	
	@RequestMapping("/")
	public String home() {
		return "index";
	}

	@RequestMapping("/add-product")
	public String addProduct(Model m) {
		m.addAttribute("title","Add product");
		return "add_product_form";
	}
	
	//handle add product form
	@RequestMapping(value = "/handle-product",method=RequestMethod.POST)
	public RedirectView handleProduct(@ModelAttribute Product product,HttpServletRequest request) {
		System.out.println(product);
		productDao.createProduct(product);
		RedirectView redirectView=new RedirectView();
		redirectView.setUrl(request.getContextPath()+"/"); 
		return redirectView;
		
	}
}
