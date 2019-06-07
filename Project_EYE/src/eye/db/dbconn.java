package eye.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class dbconn {
	
	public void insert(String date,String name,double val) throws SQLException{
		System.out.println("db연결");
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			getClass().forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:eyeDB.db");
			String sql = "insert into records(date,name,record) values(?,?,?)";
			System.out.println("sql= "+sql);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, date);
			pstmt.setString(2, name);
			pstmt.setDouble(3, val);
			int r = pstmt.executeUpdate();
			System.out.println("변경 row: "+r);
			System.out.println("디비끝");
		} catch (Exception e) {
			System.out.println("exception = "+e);
		}
		pstmt.close();
		conn.close();
	}
}
