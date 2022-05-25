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
import jp.co.seattle.library.service.RentalBooksService;

@Controller
public class rentHistoryContrpller {

	@Autowired
	private RentalBooksService rentalbooksService;
	
	@Autowired
	private BooksService booksService;

	@RequestMapping(value = "/renthist", method = RequestMethod.GET) 
	public String login(Model model) {
		model.addAttribute("rentalbookList", rentalbooksService.rentalBookList());
		return "rentHistory";
		}

	@Transactional
	@RequestMapping(value = "/rentdate", method = RequestMethod.GET)
	public String rental(Locale locale, @RequestParam("bookId") Integer bookId, Model model) {
		model.addAttribute("bookDetailsInfo", booksService.getBookInfo(bookId));
		
		return "details";

	}

}
