package jp.co.seattle.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
@Service
public class RentalBooksService {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 書籍IDに紐づく借りる書籍を登録する
	 *
	 * @param bookId 書籍ID
	 */

	public void rentalBook(int bookId) {

		String sql = "INSERT INTO rentalbooks ( rent_id ) VALUES (" + bookId + ");";

		jdbcTemplate.update(sql);

	}
	
	/**
	 * 書籍IDに紐づく借りている書籍情報を取得する
	 *
	 * @param bookId 書籍ID
	 * @return rentId 借りる書籍ID
	 */

	public int selectrentalInfo(int bookId) {

		// JSPに渡すデータを設定する
		String sql = "SELECT rent_id FROM rentalbooks where rent_id =" + bookId;
		
		try {
			int rentId = jdbcTemplate.queryForObject(sql, Integer.class);
			return  rentId;
		} catch (Exception e) {
			return 0;
		}

	}
}



