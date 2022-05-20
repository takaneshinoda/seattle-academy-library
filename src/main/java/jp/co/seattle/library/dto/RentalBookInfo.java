package jp.co.seattle.library.dto;

import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@Data
public class RentalBookInfo {

	private int bookId;

	private String title;

	private java.sql.Date lendDate;

	private java.sql.Date returnDate;

	public RentalBookInfo() {

	}

	public RentalBookInfo(int bookId, String title, java.sql.Date lenddate, java.sql.Date returndate) {
		this.bookId = bookId;
		this.title = title;
		this.lendDate = lenddate;
		this.returnDate = returndate;
	}

}
