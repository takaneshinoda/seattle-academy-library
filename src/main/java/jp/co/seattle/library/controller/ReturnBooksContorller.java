package jp.co.seattle.library.controller;

import java.util.Date;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import jp.co.seattle.library.service.BooksService;
import jp.co.seattle.library.service.RentalBooksService;

@Controller
public class ReturnBooksContorller {

	@Autowired
	private RentalBooksService rentalbooksService;

	@Autowired
	private BooksService booksService;

	@RequestMapping(value = "/returnBook", method = RequestMethod.POST)

	public String login(Locale locale, @RequestParam("bookId") int bookId, Model model) {
		Date lendDate = rentalbooksService.selectlendInfo(bookId);

		if (lendDate != null) {
			rentalbooksService.returnBook(bookId);

		} else {
			model.addAttribute("errorMessage", "貸出されてないです。");

		}
		model.addAttribute("bookDetailsInfo", booksService.getBookInfo(bookId));
		return "details";

	}

}
