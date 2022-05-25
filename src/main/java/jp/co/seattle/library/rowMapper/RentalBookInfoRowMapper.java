package jp.co.seattle.library.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.RowMapper;

import jp.co.seattle.library.dto.RentalBookInfo;

@Configuration
public class RentalBookInfoRowMapper implements RowMapper<RentalBookInfo> {

	@Override
	    public RentalBookInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
	        
		RentalBookInfo rentalbookInfo = new RentalBookInfo();
	
		 rentalbookInfo.setBookId(rs.getInt("rent_id"));
		 rentalbookInfo.setTitle(rs.getString("title"));
		 rentalbookInfo.setLendDate(rs.getDate("lend_date"));
		 rentalbookInfo.setReturnDate(rs.getDate("return_date"));
     
     return rentalbookInfo;
}
}
