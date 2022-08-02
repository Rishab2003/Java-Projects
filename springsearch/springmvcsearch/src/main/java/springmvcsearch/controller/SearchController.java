package springmvcsearch.controller;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class SearchController {

	
	@RequestMapping("/search")
	public String search() {
		return "search";
	}
	
	
	@RequestMapping("/firesearch")
	public RedirectView firesearch(@RequestParam("querybox") String q) {
		
		
		
		
		RedirectView redirectView=new RedirectView();
		
		if(q.isBlank()) {
			redirectView.setUrl("search");
			return redirectView;
		}
		
		String url="https://www.google.com/search?q="+q;
		redirectView.setUrl(url);
		
		return redirectView;
				
	}
}
