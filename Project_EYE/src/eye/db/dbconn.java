package eye.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import eye.record.model.recordModel;
import eye.record.model.timesModel;

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
	
	//종류별 휴식 총 횟수
	public int getRest(String name) throws SQLException {
		System.out.println("getTodayEx연결");
		int n =0;
		try {
			getClass().forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:eyeDB.db");
			String sql = "select count(*) as restCnt from times where name=?";
			System.out.println("sql= "+sql+" name="+name);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				n = rs.getInt("restCnt");
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
		case "zigzag":
			kinds = "exercise";
			break;
		case "mobius":
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
	
	//한글 따라가기 기록 저장
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
	
	//오름차순 게임 기록 저장
		public void insertUpGame(String date,String name,int val) throws SQLException{
			System.out.println("insertUpGame연결");
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
		
		//시선이동 게임 기록 저장
				public void insertEyeMoveGame(String date,String name,int val) throws SQLException{
					System.out.println("insertEyeMoveGame연결");
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
	
	//게임 기록 가져오기
	public Double[] getGameData(String name, int i) throws SQLException {
		System.out.println("getGameData 연결");
		Double arr[] = new Double[3]; 
		int n =0;
		String sql=null;
		try {
			getClass().forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:eyeDB.db");
			if(i==0) {
			sql = "select record from records where name = ? order by record limit 3";
			}else {
				sql = "select record from records where name = ? order by record DESC limit 3";
			}
			System.out.println("sql= "+sql);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				arr[n++] = rs.getDouble("record");
			}
			System.out.println("디비끝");
		} catch (Exception e) {
			System.out.println("exception = "+e);
		}
		pstmt.close();
		conn.close();
		return arr;
	}
	
	//정답인 기록 가져오기
		public int getCorRecord(String name) throws SQLException {
			System.out.println("getCorRecord연결");
			int n =0;
			try {
				getClass().forName("org.sqlite.JDBC");
				conn = DriverManager.getConnection("jdbc:sqlite:eyeDB.db");
				String sql = "select count(*) as totalCorRecord from records where name=? and record = 1";
				System.out.println("sql= "+sql+" name="+name);
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, name);
				ResultSet rs = pstmt.executeQuery();
				while(rs.next()) {
					n = rs.getInt("totalCorRecord");
				}
			} catch (Exception e) {
				System.out.println(" totalCorRecord = "+e);
			}
			stmt.close();
			conn.close();
			
			return n;
		}
		
		//랭킹10위 가져오기 오름차순
				public List<recordModel> getTopRecordASC(String name) throws SQLException {
					System.out.println("getTopRecordASC연결");
					List<recordModel> rm =  new ArrayList<recordModel>();
					int n =0;
					try {
						getClass().forName("org.sqlite.JDBC");
						conn = DriverManager.getConnection("jdbc:sqlite:eyeDB.db");
						String sql = "select * from records where name=? order by record limit 10";
						System.out.println("sql= "+sql+" name="+name);
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, name);
						ResultSet rs = pstmt.executeQuery();
						while(rs.next()) {
							recordModel rmt = new recordModel();
							rmt.setRecord(rs.getInt("record"));
							rmt.setDate(rs.getString("date"));
							rm.add(rmt);
						}
					} catch (Exception e) {
						System.out.println(" topRecord = "+e);
					}
					pstmt.close();
					conn.close();
					
					return rm;
				}
				
				//랭킹10위 가져오기 내림차순
				public List<recordModel> getTopRecordDESC(String name) throws SQLException {
					System.out.println("getTopRecordDESC연결");
					List<recordModel> rm =  new ArrayList<recordModel>();
					int n =0;
					try {
						getClass().forName("org.sqlite.JDBC");
						conn = DriverManager.getConnection("jdbc:sqlite:eyeDB.db");
						String sql = "select * from records where name=? order by record desc limit 10";
						System.out.println("sql= "+sql+" name="+name);
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, name);
						ResultSet rs = pstmt.executeQuery();
						while(rs.next()) {
							recordModel rmt = new recordModel();
							rmt.setRecord(rs.getInt("record"));
							rmt.setDate(rs.getString("date"));
							rm.add(rmt);
						}
					} catch (Exception e) {
						System.out.println(" topRecord = "+e);
					}
					pstmt.close();
					conn.close();
					
					return rm;
				}
				
				//일주일 데이터 가져오기
				public List<timesModel> getWeekData(String name) throws SQLException {
					System.out.println("getWeekData연결");
					SimpleDateFormat sDateForm = new SimpleDateFormat("yyyy/MM/dd");
					Date currentTime = new Date();
					Calendar cal = Calendar.getInstance();
					cal.setTime(currentTime);
					cal.add(Calendar.DATE,-7);
					String before = sDateForm.format(cal.getTime());
					String now = sDateForm.format(currentTime);
					System.out.println("7일 전 = "+before+" 현재 = "+now);
					
					List<timesModel> rm =  new ArrayList<timesModel>();
					List<timesModel> weekData =  new ArrayList<timesModel>();
					int n =0;
					int i = -6;
					try {
						getClass().forName("org.sqlite.JDBC");
						conn = DriverManager.getConnection("jdbc:sqlite:eyeDB.db");
						String sql = "SELECT date,count(kinds) as num from times where date >= ? and date <= ? and kinds = ? group by date ORDER by date";
						System.out.println("sql= "+sql+" name="+name);
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, before);
						pstmt.setString(2, now);
						pstmt.setString(3, name);
						ResultSet rs = pstmt.executeQuery();
						System.out.println("-----------------------------------------------");
						while(rs.next()) {
							//날짜비교
							while(true) {
								cal = Calendar.getInstance();
								cal.setTime(currentTime);
								cal.add(Calendar.DATE,i);
								before = sDateForm.format(cal.getTime());
								if(before.equals(rs.getString("date"))) {
									System.out.println("before date = "+before);
									System.out.println("db date="+rs.getString("date")+"db num="+rs.getInt("num"));
									timesModel rmt = new timesModel();
								rmt.setCnt(rs.getInt("num"));
								rmt.setDate(rs.getString("date"));
								rm.add(rmt);
								i++;
								break;
								}else {
									System.out.println("--before date = "+before);
									System.out.println("--db date="+rs.getString("date")+"db num="+rs.getInt("num"));
									timesModel rmt = new timesModel();
									rmt.setCnt(0);
									rmt.setDate(before);
									rm.add(rmt);
									i++;
								}
							}
						}
						System.out.println("-----------------------------------------------");
						
					} catch (Exception e) {
						System.out.println(" topRecord = "+e);
					}
					pstmt.close();
					conn.close();
					
					return rm;
				}
				
				//한달 데이터 가져오기
				public List<timesModel> getMonthData(String name) throws SQLException {
					System.out.println("getMonthData연결");
					SimpleDateFormat sDateForm = new SimpleDateFormat("yyyy/MM/dd");
					Date currentTime = new Date();
					Calendar cal = Calendar.getInstance();
					cal.setTime(currentTime);
					cal.add(Calendar.DATE,-30);
					String before = sDateForm.format(cal.getTime());
					String now = sDateForm.format(currentTime);
					System.out.println("30일 전 = "+before+" 현재 = "+now);
					
					List<timesModel> rm =  new ArrayList<timesModel>();
					List<timesModel> monthData =  new ArrayList<timesModel>();
					int n =0;
					int i = -29;
					try {
						getClass().forName("org.sqlite.JDBC");
						conn = DriverManager.getConnection("jdbc:sqlite:eyeDB.db");
						String sql = "SELECT date,count(kinds) as num from times where date >= ? and date <= ? and kinds = ? group by date ORDER by date";
						System.out.println("sql= "+sql+" name="+name);
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, before);
						pstmt.setString(2, now);
						pstmt.setString(3, name);
						ResultSet rs = pstmt.executeQuery();
						System.out.println("-----------------------------------------------");
						while(rs.next()) {
							//날짜비교
							while(true) {
								cal = Calendar.getInstance();
								cal.setTime(currentTime);
								cal.add(Calendar.DATE,i);
								before = sDateForm.format(cal.getTime());
								if(before.equals(rs.getString("date"))) {
									System.out.println("before date = "+before);
									System.out.println("db date="+rs.getString("date")+"db num="+rs.getInt("num"));
									timesModel rmt = new timesModel();
								rmt.setCnt(rs.getInt("num"));
								rmt.setDate(rs.getString("date"));
								rm.add(rmt);
								i++;
								break;
								}else {
									System.out.println("--before date = "+before);
									System.out.println("--db date="+rs.getString("date")+"db num="+rs.getInt("num"));
									timesModel rmt = new timesModel();
									rmt.setCnt(0);
									rmt.setDate(before);
									rm.add(rmt);
									i++;
								}
							}
						}
						System.out.println("-----------------------------------------------");
						
					} catch (Exception e) {
						System.out.println(" topRecord = "+e);
					}
					pstmt.close();
					conn.close();
					
					return rm;
				}
}
