package jp.co.seattle.library.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import jp.co.seattle.library.service.BooksService;

@Controller
public class SearchBooksController {
	
	@Autowired
    private BooksService booksService;
	
	 @Transactional
	    @RequestMapping(value = "/searchbottom", method = RequestMethod.POST)
	    public String serchBook(Locale locale,@RequestParam("search")String title,
	            Model model) {
		 
		 
		 
		 model.addAttribute("bookList" , booksService.getBookList(title));
		 
		 return "home";
	 
	 }
	

}
