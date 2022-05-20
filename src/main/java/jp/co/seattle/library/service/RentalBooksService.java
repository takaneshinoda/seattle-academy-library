package jp.co.seattle.library.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import jp.co.seattle.library.dto.RentalBookInfo;
import jp.co.seattle.library.rowMapper.RentalBookInfoRowMapper;

@Service
public class RentalBooksService {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 貸出IDに紐づく借りる書籍を登録する
	 *
	 * @param bookId 書籍ID
	 */
	public void rentalBook(int bookId) {

		String sql = "INSERT INTO rentalbooks ( rent_id,lend_date ) VALUES (" + bookId + ", now());";

		jdbcTemplate.update(sql);

	}
	
	
	/**
	 * 貸出IDに紐づく借りる書籍の返却日を更新
	 *
	 * @param bookId 書籍ID
	 */
	public void returnBook(int bookId) {

		String sql = "update rentalbooks set return_date = now(),lend_date = null where rent_id = " + bookId +  ";";
		jdbcTemplate.update(sql);

	}
	
	/**
	 * 貸出IDに紐づく借りる書籍の貸出日を更新
	 *
	 * @param bookId 貸出ID
	 */
	public void lendBook(int bookId) {
		
		String sql = "update rentalbooks set return_date = null , lend_date = now() where rent_id =" + bookId + ";";
		jdbcTemplate.update(sql);
	}
	
	/**
	 * 貸出IDに紐づく借りる書籍の貸出されているかの状態
	 *
	 * @param bookId 貸出ID
	 * 
	 * @return lendDate 貸出日 
	 */
	public java.sql.Date selectlendInfo(int bookId) {

		// JSPに渡すデータを設定する
		String sql = "SELECT lend_date FROM rentalbooks where rent_id =" + bookId + ";";
		
		try {
			java.sql.Date lendDate = jdbcTemplate.queryForObject(sql, java.sql.Date.class);
			return  lendDate;
		} catch (Exception e) {
			return null;
		}

	}
	
	
	/**
	 * 貸出IDに紐づく借りている書籍情報を取得する
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
	
	/**
	 * 貸出IDに紐づく借りている書籍情報をリスト化
	 *
	 * @param bookId 書籍ID
	 * @return rentalBookList 貸出中のリスト
	 */
	public List<RentalBookInfo> rentalBookList() {

		List<RentalBookInfo> rentalBookList = jdbcTemplate.query(

				"select rent_id, title, lend_date,return_date from books LEFT outer JOIN rentalbooks ON books.id = rentalbooks.rent_id WHERE rent_id IS NOT NULL ORDER BY title ;",

				new RentalBookInfoRowMapper());

		return rentalBookList;
	}
}



