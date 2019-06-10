package eye.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class dbconn {
	Connection conn = null;
	PreparedStatement pstmt = null;
	Statement stmt = null;
	
	//총 휴식횟수
	public int getTotalRest() throws SQLException {
		System.out.println("getTotalRest연결");
		int n =0;
		try {
			getClass().forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:eyeDB.db");
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select count(*) as totalRest from times where kinds='rest'");
			while(rs.next()) {
				n = rs.getInt("totalRest");
			}
		} catch (Exception e) {
			System.out.println("exception = "+e);
		}
		stmt.close();
		conn.close();
		
		return n;
	}
	
	//오늘 총 휴식횟수
	public int getTodayRest(String date) throws SQLException {
		System.out.println("getTodayEx연결");
		int n =0;
		try {
			getClass().forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:eyeDB.db");
			String sql = "select count(*) as todayRest from times where kinds='rest' and date=?";
			System.out.println("sql= "+sql+" date="+date);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, date);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				n = rs.getInt("todayRest");
			}
		} catch (Exception e) {
			System.out.println(" today exception = "+e);
		}
		stmt.close();
		conn.close();
		
		return n;
	}
	
	//총 운동횟수
	public int getTotalEx() throws SQLException {
		System.out.println("getTotalEx연결");
		int n =0;
		try {
			getClass().forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:eyeDB.db");
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select count(*) as totalEx from times where kinds='exercise'");
			while(rs.next()) {
				n = rs.getInt("totalEx");
			}
		} catch (Exception e) {
			System.out.println("exception = "+e);
		}
		stmt.close();
		conn.close();
		
		return n;
	}
	
	//오늘 총 운동횟수
	public int getTodayEx(String date) throws SQLException {
		System.out.println("getTodayEx연결");
		int n =0;
		try {
			getClass().forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:eyeDB.db");
			String sql = "select count(*) as todayEx from times where kinds='exercise' and date=?";
			System.out.println("sql= "+sql+" date="+date);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, date);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				n = rs.getInt("todayEx");
			}
		} catch (Exception e) {
			System.out.println(" today exception = "+e);
		}
		stmt.close();
		conn.close();
		
		return n;
	}
	
	//게임 종류별 총 횟수 가져오기
	public int getTotalEx(String name) throws SQLException {
		System.out.println("getTodayEx연결");
		int n =0;
		try {
			getClass().forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:eyeDB.db");
			String sql = "select count(*) as totalEx from times where name=?";
			System.out.println("sql= "+sql+" name="+name);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				n = rs.getInt("totalEx");
			}
		} catch (Exception e) {
			System.out.println(" today exception = "+e);
		}
		stmt.close();
		conn.close();
		
		return n;
	}
	
	//게임 종류별 맞춘 횟수 가져오기
	public int getTotalAnswer(String name) throws SQLException {
		System.out.println("getTodayEx연결");
		int n =0;
		try {
			getClass().forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:eyeDB.db");
			String sql = "select count(*) as totalEx from times where name=?";
			System.out.println("sql= "+sql+" name="+name);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				n = rs.getInt("totalEx");
			}
		} catch (Exception e) {
			System.out.println(" today exception = "+e);
		}
		stmt.close();
		conn.close();
		
		return n;
	}
	
	//실행 횟수 입력
	public void insertTimes(String name,String date) throws SQLException {
		System.out.println("insertTimes연결");
		String kinds = null;
		
		switch (name) {
		case "follow":
			kinds = "exercise";
			break;
		case "eyeMove1":
			kinds = "exercise";
			break;
		case "eyeMove2":
			kinds = "exercise";
			break;
		case "fiveDot":
			kinds = "exercise";
			break;
		case "catchMole":
			kinds = "exercise";
			break;
		case "catchBall":
			kinds = "exercise";
			break;
		case "findPicture":
			kinds ="exercise";
			break;

		default:
			kinds = "rest";
			break;
		}
		
		try {
			getClass().forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:eyeDB.db");
			String sql = "insert into times(date,kinds,name) values(?,?,?)";
			System.out.println("sql= "+sql);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, date);
			pstmt.setString(2, kinds);
			pstmt.setString(3, name);
			int r = pstmt.executeUpdate();
			System.out.println("변경 row: "+r);
			System.out.println("디비끝");
		} catch (Exception e) {
			System.out.println("exception = "+e);
		}
		pstmt.close();
		conn.close();
	}
	//한글 따라가기 기록 전송
	public void insertKorGame(String date,String name,double val) throws SQLException{
		System.out.println("insertKorGame연결");
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
	
	//한글 따라가기 기록 가져오기
	public Double[] getKordata() throws SQLException {
		System.out.println("getKordata 연결");
		Double arr[] = new Double[3]; 
		try {
			getClass().forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:eyeDB.db");
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from records order by record LIMIT 3");
			int x=0;
			while(rs.next()) {
				arr[x++] = rs.getDouble("record");
			}
		} catch (Exception e) {
			System.out.println("exception = "+e);
		}
		System.out.println("디비끝");
		stmt.close();
		conn.close();
		return arr;
	}
}
