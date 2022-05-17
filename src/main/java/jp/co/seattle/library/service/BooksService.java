package jp.co.seattle.library.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import jp.co.seattle.library.dto.BookDetailsInfo;
import jp.co.seattle.library.dto.BookInfo;
import jp.co.seattle.library.rowMapper.BookDetailsInfoRowMapper;
import jp.co.seattle.library.rowMapper.BookInfoRowMapper;

/**
 * 書籍サービス
 * 
 * booksテーブルに関する処理を実装する
 */
@Service
public class BooksService {
	final static Logger logger = LoggerFactory.getLogger(BooksService.class);
	@Autowired
	private JdbcTemplate jdbcTemplate;

	/**
	 * 書籍リストを取得する
	 *
	 * @return 書籍リスト
	 */
	public List<BookInfo> getBookList() {

		List<BookInfo> getedBookList = jdbcTemplate.query(

				"select id, title, author, publisher, publish_date, thumbnail_url  from books ORDER BY title ;",
				new BookInfoRowMapper());

		return getedBookList;
	}

	/**
	 * 書籍情報を更新する
	 *
	 * @param bookInfo 書籍情報
	 */
	public void updatebookInfo(BookDetailsInfo bookInfo) {
		String sql;
		if (bookInfo.getThumbnailUrl() != null) {

			sql = "UPDATE books SET title = " + "'" + bookInfo.getTitle() + "'" + "," + "author =" + "'"
					+ bookInfo.getAuthor() + "'" + "," + "publisher =" + "'" + bookInfo.getPublisher() + "'" + ","
					+ "publish_date =" + "'" + bookInfo.getPublishDate() + "'" + "," + "isbn =" + "'"
					+ bookInfo.getISBN() + "'" + "," + "description =" + "'" + bookInfo.getDescription() + "'" + ","
					+ "thumbnail_url =" + "'" + bookInfo.getThumbnailUrl() + "'" + "where id =" + bookInfo.getBookId()
					+ ";";
		} else {
			sql = "UPDATE books SET title = " + "'" + bookInfo.getTitle() + "'" + "," + "author =" + "'"
					+ bookInfo.getAuthor() + "'" + "," + "publisher =" + "'" + bookInfo.getPublisher() + "'" + ","
					+ "publish_date =" + "'" + bookInfo.getPublishDate() + "'" + "," + "isbn =" + "'"
					+ bookInfo.getISBN() + "'" + "," + "description =" + "'" + bookInfo.getDescription() + "'"
					+ "where id =" + bookInfo.getBookId() + ";";

		}

		jdbcTemplate.update(sql);

	}

	/**
	 * 書籍リストを削除する
	 *
	 * @param bookId 書籍ID
	 * 
	 */
	// publicは持ってくる！ deleteBookからbookIdをもってきます。 →持ってきました！ bookIdは0でした！！
	public void deleteBook(int bookId) {

		String sql = "delete from books where id =" + bookId + ";";
		jdbcTemplate.update(sql);

	}

	/**
	 * 書籍IDに紐づく書籍詳細情報を取得する
	 *
	 * @param bookId 書籍ID
	 * @return 書籍情報
	 */
	public BookDetailsInfo getBookInfo(int bookId) {

		// JSPに渡すデータを設定する
		String sql = "select * ,case when rent_id is NULL THEN '貸出可' ELSE '貸出中' end as status FROM books LEFT outer JOIN rentalbooks ON books.id = rentalbooks.rent_id where books.id ="
				+ bookId + ";";

		BookDetailsInfo bookDetailsInfo = jdbcTemplate.queryForObject(sql, new BookDetailsInfoRowMapper());

		return bookDetailsInfo;

	}

	/**
	 * 書籍を登録する
	 *
	 * @param bookInfo 書籍情報
	 */
	public void registBook(BookDetailsInfo bookInfo) {

		String sql = "INSERT INTO books (title, author,publisher,publish_date, isbn, description, thumbnail_name, thumbnail_url, reg_date,upd_date) VALUES ('"
				+ bookInfo.getTitle() + "','" + bookInfo.getAuthor() + "','" + bookInfo.getPublisher() + "','"
				+ bookInfo.getPublishDate() + "','" + bookInfo.getISBN() + "','" + bookInfo.getDescription() + "','"
				+ bookInfo.getThumbnailName() + "','" + bookInfo.getThumbnailUrl() + "'," + "now()," + "now())";

		jdbcTemplate.update(sql);

	}

	/**
	 * 最新の書籍IDを取得する
	 *
	 * @return 最新の書籍ID
	 */

	public int getMaxId() {
		String sql = "SELECT max(id) FROM books ";

		int bookId = jdbcTemplate.queryForObject(sql, Integer.class);

		return bookId;
	}
	
	/**
	 * 最新の書籍IDを取得する
	 *
	 *@param title 書籍名
	 *@return 書籍名に基づく書籍情報
	 */

	public List<BookInfo> searchBookList(String title) {

		List<BookInfo> getedBookList = jdbcTemplate.query(

				"select id, title, author, publisher, publish_date, thumbnail_url from books where title like '%" + title + "%';",
				new BookInfoRowMapper());

		return getedBookList;
	}

}
