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
public class ReturnBooksContorller {
	
	@Autowired
	private RentalBooksService rentalbooksService;

	@Autowired
	private BooksService booksService;


	@RequestMapping(value = "/returnBook", method = RequestMethod.POST) // value＝actionで指定したパラメータ
	// RequestParamでname属性を取得
	public String login(Locale locale, @RequestParam("bookId") int bookId, Model model) {
		int rentId = rentalbooksService.selectrentalInfo(bookId);

		if (rentId != 0) { 
			rentalbooksService.returnBook(bookId);

		} else { // rentalsに書籍ID(bookId)が登録されていたら貸出できないメッセージを表示
			model.addAttribute("errorMessage", "貸出されてないです。");

		}
		model.addAttribute("bookDetailsInfo", booksService.getBookInfo(bookId));
		return "details";

	}

}
