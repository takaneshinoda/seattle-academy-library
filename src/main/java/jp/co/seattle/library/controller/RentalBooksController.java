package jp.co.seattle.library.controller;

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
public class RentalBooksController {

	@Autowired
	private RentalBooksService rentalbooksService;

	@Autowired
	private BooksService booksService;

	@RequestMapping(value = "/rentBook", method = RequestMethod.POST)
	public String login(Locale locale, @RequestParam("bookId") int bookId, Model model) {
		int rentId = rentalbooksService.selectrentalInfo(bookId);
		java.sql.Date lendDate = rentalbooksService.selectlendInfo(bookId);

		if (rentId == 0) {
			rentalbooksService.rentalBook(bookId);

		} else if (lendDate == null) {
			rentalbooksService.lendBook(bookId);

		} else {

			model.addAttribute("errorMessage", "貸出済みです。");

		}
		model.addAttribute("bookDetailsInfo", booksService.getBookInfo(bookId));
		return "details";

	}

}