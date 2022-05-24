package jp.co.seattle.library.dto;

import java.sql.Date;

import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@Data
public class RentalBookInfo {

	private int bookId;

	private String title;

	private Date lendDate;

	private Date returnDate;

	public RentalBookInfo() {

	}

	public RentalBookInfo(int bookId, String title, Date lenddate, Date returndate) {
		this.bookId = bookId;
		this.title = title;
		this.lendDate = lenddate;
		this.returnDate = returndate;
	}

}
