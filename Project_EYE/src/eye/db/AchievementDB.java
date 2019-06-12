package eye.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import eye.game.eyeMovement1.Playcontroller;
import eye.game.eyeMovement1.Successcontroller;
import eye.game.eyeMovement2.Failcontroller;

public class AchievementDB {
	public static double WeekPlay = 0;
	public static double MonthPlay = 0;
	public static double Record = 0;
	public static double Lucky = 0;
	public static double Human = 0;
	public static double Double = 0;
	public static double Evolution = 0;
	public static double Saryunan = 0;
	public static double Easy = 0;
	public static double Novice = 0;
	public static double Fresh = 0;
	public static double Color = 0;
	public static double Void = 0;
	public static double Perfect = 0;
	public static double Mistake = 0;
	public static double Dangerous = 0;

	public void ach() {
		Connection c = null;
		Statement stmt = null;
		String sql = "";

		int DayPlaycnt = 0;
		int DayRestcnt = 0;
		int WeekPlaycnt = 0;
		int WeekRestcnt = 0;
		int MonthPlaycnt = 0;
		int MonthRestcnt = 0;
		int Recordcnt = 0;
		int Luckycnt = 0;
		int Humancnt = 0;
		int Doublecnt = 0;
		int Evolutioncnt = 0;
		int Saryunancnt = 0;
		int Easycnt = 0;
		int Novicecnt = 0;
		int Freshcnt = 0;
		int Colorcnt = 0;
		int Voidcnt = 0;
		int Perfectcnt = 0;
		int Mistakecnt = 0;
		int Dangerouscnt = 0;
		int Monthcnt = 0;
		int Monthcnt2 = 0;
		int Weekcnt = 0;
		int Weekcnt2 = 0;

		SimpleDateFormat Day = new SimpleDateFormat("yyyy-MM-dd");
		Date currentTime = new Date();
		String Java_Day = Day.format(currentTime);
		String Sql_Day = "";

		Calendar c1 = new GregorianCalendar();
		c1.add(Calendar.DATE, -1); // 오늘날짜로부터 -1
		String Java_yesDay = Day.format(c1.getTime()); // String으로 저장

		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:eyeDB.db");

			stmt = c.createStatement();

			ResultSet rst = stmt.executeQuery("SELECT*FROM achievement where ID = 21;");
			while (rst.next()) {
				Monthcnt = rst.getInt("Playcount");
				Sql_Day = rst.getString("Day_time");
			}
			rst.close();

			if (!Java_Day.equals(Sql_Day.toString()) && Monthcnt == 1) {
				sql = "UPDATE achievement set Playcount = 0 where ID = 21;";
				stmt.executeUpdate(sql);
				sql = "UPDATE achievement set Day_time = Date('now','localtime') where ID = 21;";
				stmt.executeUpdate(sql);
				Monthcnt = 0;
			}

			ResultSet rst2 = stmt.executeQuery("SELECT*FROM achievement where ID = 22;");
			while (rst.next()) {
				Monthcnt2 = rst2.getInt("Playcount");
				Sql_Day = rst2.getString("Day_time");
			}
			rst2.close();

			if (!Java_Day.equals(Sql_Day.toString()) && Monthcnt2 == 1) {
				sql = "UPDATE achievement set Playcount = 0 where ID = 22;";
				stmt.executeUpdate(sql);
				sql = "UPDATE achievement set Day_time = Date('now','localtime') where ID = 22;";
				stmt.executeUpdate(sql);
				Monthcnt2 = 0;
			}

			ResultSet rst3 = stmt.executeQuery("SELECT*FROM achievement where ID = 23;");
			while (rst.next()) {
				Weekcnt = rst3.getInt("Playcount");
				Sql_Day = rst3.getString("Day_time");
			}
			rst3.close();

			if (!Java_Day.equals(Sql_Day.toString()) && Weekcnt == 1) {
				sql = "UPDATE achievement set Playcount = 0 where ID = 23;";
				stmt.executeUpdate(sql);
				sql = "UPDATE achievement set Day_time = Date('now','localtime') where ID = 23;";
				stmt.executeUpdate(sql);
				Weekcnt = 0;
			}

			ResultSet rst4 = stmt.executeQuery("SELECT*FROM achievement where ID = 24;");
			while (rst.next()) {
				Weekcnt2 = rst4.getInt("Playcount");
				Sql_Day = rst4.getString("Day_time");
			}
			rst4.close();

			if (!Java_Day.equals(Sql_Day.toString()) && Weekcnt2 == 1) {
				sql = "UPDATE achievement set Playcount = 0 where ID = 24;";
				stmt.executeUpdate(sql);
				sql = "UPDATE achievement set Day_time = Date('now','localtime') where ID = 24;";
				stmt.executeUpdate(sql);
				Weekcnt2 = 0;
			}

			ResultSet rs = stmt.executeQuery("SELECT * FROM achievement where ID = 1;");
			while (rs.next()) {
				DayPlaycnt = rs.getInt("Playcount");
				Sql_Day = rs.getString("Day_time");
			}
			rs.close();
			if (Java_Day.equals(Sql_Day.toString()) && DayPlaycnt != 3) {
				sql = "UPDATE achievement set Playcount = Playcount +1 where ID = 1;";
				stmt.executeUpdate(sql);

			} else if (Java_Day.equals(Sql_Day.toString()) && DayPlaycnt == 3) {
				System.out.println("데이플레이카운트 오늘은그만");
			} else {
				sql = "UPDATE achievement set Playcount = 1 where ID = 1;";
				stmt.executeUpdate(sql);
				sql = "UPDATE achievement set Day_time = Date('now','localtime') where ID = 1;";
				stmt.executeUpdate(sql);
			}

			ResultSet rs2 = stmt.executeQuery("SELECT * FROM achievement where ID = 2;");
			while (rs2.next()) {
				DayRestcnt = rs2.getInt("Playcount");
				Sql_Day = rs2.getString("Day_time");
			}
			rs2.close();

			if (Java_Day.equals(Sql_Day.toString()) && DayRestcnt != 3) {
				sql = "UPDATE achievement set Playcount = Playcount +1 where ID = 2;";
				stmt.executeUpdate(sql);
			} else if (Java_Day.equals(Sql_Day.toString()) && DayRestcnt == 3) {
				System.out.println("데이레스트카운트 오늘은그만");
			} else {
				sql = "UPDATE achievement set Playcount = 1 where ID = 2;";
				stmt.executeUpdate(sql);
				sql = "UPDATE achievement set Day_time = Date('now','localtime') where ID = 2;";
				stmt.executeUpdate(sql);
			}

			ResultSet rs3 = stmt.executeQuery("SELECT * FROM achievement where ID = 3;");
			while (rs3.next()) {
				WeekPlaycnt = rs3.getInt("Playcount");
				Sql_Day = rs3.getString("Day_time");
			}
			rs3.close();

			if (Weekcnt < 1 && DayPlaycnt == 2 && DayRestcnt == 2 && WeekPlaycnt != 7
					&& (Java_yesDay.equals(Sql_Day.toString()) || Java_Day.equals(Sql_Day.toString()))) {
				sql = "UPDATE achievement set Playcount = Playcount +1 where ID = 3;";
				stmt.executeUpdate(sql);
				sql = "UPDATE achievement set Playcount = Playcount +1 where ID = 23";
				stmt.executeUpdate(sql);
				sql = "UPDATE achievement set Day_time = Date('now','localtime') where ID = 3;";
				stmt.executeUpdate(sql);
				sql = "UPDATE achievement set Day_time = Date('now','localtime') where ID = 23;";
				stmt.executeUpdate(sql);
			} else if (Java_Day.equals(Sql_Day.toString()) && DayPlaycnt <= 3) {
				System.out.println("주간카운트 오늘은그만");
			} else if (WeekPlaycnt == 7) {
				System.out.println("개근 도전 성공");
			} else if (Java_yesDay.equals(Sql_Day.toString()) || Java_Day.equals(Sql_Day.toString())) {

			} else {
				sql = "UPDATE achievement set Playcount = 0 where ID = 3;";
				stmt.executeUpdate(sql);
				sql = "UPDATE achievement set Day_time = Date('now','localtime') where ID = 3;";
				stmt.executeUpdate(sql);
			}

//			ResultSet rs4 = stmt.executeQuery("SELECT * FROM achievement where ID = 4;");
//			while (rs4.next()) {
//				WeekRestcnt = rs4.getInt("Playcount");
//				Sql_Day = rs4.getString("Day_time");
//			}
//			rs4.close();

//			if (Weekcnt2 < 1 && DayRestcnt == 2 && WeekRestcnt != 7
//					&& (Java_yesDay.equals(Sql_Day.toString()) || Java_Day.equals(Sql_Day.toString()))) {
//				sql = "UPDATE achievement set Playcount = Playcount +1 where ID = 4;";
//				stmt.executeUpdate(sql);
//				sql = "UPDATE achievement set Playcount = Playcount +1 where ID = 24";
//				stmt.executeUpdate(sql);
//				sql = "UPDATE achievement set Day_time = Date('now','localtime') where ID = 4;";
//				stmt.executeUpdate(sql);
//				sql = "UPDATE achievement set Day_time = Date('now','localtime') where ID = 24;";
//				stmt.executeUpdate(sql);
//			} else if (Java_Day.equals(Sql_Day.toString()) && DayRestcnt <= 3) {
//				System.out.println("주간레스트카운트 오늘은그만");
//			} else if (WeekRestcnt == 7) {
//				System.out.println("주간레스트 도전성공");
//			} else if (Java_yesDay.equals(Sql_Day.toString()) || Java_Day.equals(Sql_Day.toString())) {
//
//			} else {
//				sql = "UPDATE achievement set Playcount = 1 where ID = 4;";
//				stmt.executeUpdate(sql);
//				sql = "UPDATE achievement set Day_time = Date('now','localtime') where ID = 4;";
//				stmt.executeUpdate(sql);
//			}

			ResultSet rs5 = stmt.executeQuery("SELECT * FROM achievement where ID = 5;");
			while (rs5.next()) {
				MonthPlaycnt = rs5.getInt("Playcount");
				Sql_Day = rs5.getString("Day_time");
			}
			rs5.close();

			if (Monthcnt < 1 & DayPlaycnt == 2 && DayRestcnt ==2 && MonthPlaycnt != 30
					&& (Java_yesDay.equals(Sql_Day.toString()) || Java_Day.equals(Sql_Day.toString()))) {
				sql = "UPDATE achievement set Playcount = Playcount +1 where ID = 5;";
				stmt.executeUpdate(sql);
				sql = "UPDATE achievement set Playcount = Playcount +1 where ID = 21";
				stmt.executeUpdate(sql);
				sql = "UPDATE achievement set Day_time = Date('now','localtime') where ID = 5;";
				stmt.executeUpdate(sql);
				sql = "UPDATE achievement set Day_time = Date('now','localtime') where ID = 21;";
				stmt.executeUpdate(sql);
			} else if (Java_Day.equals(Sql_Day.toString())) {
				System.out.println("월간카운트 오늘은그만");
			} else if (Java_yesDay.equals(Sql_Day.toString()) || Java_Day.equals(Sql_Day.toString())) {

			} else if (MonthPlaycnt == 30) {
				System.out.println("개근30 도전성공");
			} else {
				sql = "UPDATE achievement set Playcount = 0 where ID = 5;";
				stmt.executeUpdate(sql);
				sql = "UPDATE achievement set Day_time = Date('now','localtime') where ID = 5;";
				stmt.executeUpdate(sql);
			}

//			ResultSet rs6 = stmt.executeQuery("SELECT * FROM achievement where ID = 6;");
//			while (rs6.next()) {
//				MonthRestcnt = rs6.getInt("Playcount");
//				Sql_Day = rs6.getString("Day_time");
//			}
//			rs6.close();
//
//			if (Monthcnt2 < 1 && DayRestcnt == 2 && MonthRestcnt != 30
//					&& (Java_yesDay.equals(Sql_Day.toString()) || Java_Day.equals(Sql_Day.toString()))) {
//				sql = "UPDATE achievement set Playcount = Playcount +1 where ID = 6;";
//				stmt.executeUpdate(sql);
//				sql = "UPDATE achievement set Playcount = Playcount +1 where ID = 22";
//				stmt.executeUpdate(sql);
//				sql = "UPDATE achievement set Day_time = Date('now','localtime') where ID = 6;";
//				stmt.executeUpdate(sql);
//				sql = "UPDATE achievement set Day_time = Date('now','localtime') where ID = 22;";
//				stmt.executeUpdate(sql);
//			} else if (Java_Day.equals(Sql_Day.toString())) {
//				System.out.println("월간레스트카운트 오늘은그만");
//			} else if (Java_yesDay.equals(Sql_Day.toString()) || Java_Day.equals(Sql_Day.toString())) {
//
//			} else if (MonthRestcnt == 30) {
//				System.out.println("월간레스트 도전성공");
//			} else {
//				sql = "UPDATE achievement set Playcount = 1 where ID = 6;";
//				stmt.executeUpdate(sql);
//				sql = "UPDATE achievement set Day_time = Date('now','localtime') where ID = 6;";
//				stmt.executeUpdate(sql);
//			}

			ResultSet rs7 = stmt.executeQuery("SELECT * FROM achievement where ID = 7;");
			while (rs7.next()) {
				Recordcnt = rs7.getInt("Playcount");
				Sql_Day = rs7.getString("Day_time");
			}
			rs7.close();
			if (Recordcnt != 1) {
				sql = "UPDATE achievement set Playcount = Playcount +1 where ID = 7;";
				stmt.executeUpdate(sql);
				sql = "UPDATE achievement set Day_time = Date('now','localtime') where ID = 7;";
				stmt.executeUpdate(sql);
			} else if (Recordcnt == 1) {
				System.out.println("기록 도전과제 성공");
			}

			ResultSet rs8 = stmt.executeQuery("SELECT * FROM achievement where ID = 8;");
			while (rs8.next()) {
				Luckycnt = rs8.getInt("Playcount");
				Sql_Day = rs8.getString("Day_time");
			}
			rs8.close();

			if (Luckycnt != 1) {
				sql = "UPDATE achievement set Playcount = Playcount +1 where ID = 8;";
				stmt.executeUpdate(sql);
				sql = "UPDATE achievement set Day_time = Date('now','localtime') where ID = 8;";
				stmt.executeUpdate(sql);
			} else if (Luckycnt == 1) {
				System.out.println("럭키세븐 도전과제 성공");
			}

			ResultSet rs9 = stmt.executeQuery("SELECT * FROM achievement where ID = 9;");
			while (rs9.next()) {
				Humancnt = rs9.getInt("Playcount");
				Sql_Day = rs9.getString("Day_time");
			}
			rs9.close();
			if (Humancnt != 1) {
				sql = "UPDATE achievement set Playcount = Playcount +1 where ID = 9;";
				stmt.executeUpdate(sql);
				sql = "UPDATE achievement set Day_time = Date('now','localtime') where ID = 9;";
				stmt.executeUpdate(sql);
			} else if (Humancnt == 1) {
				System.out.println("사람인가 도전과제 성공");
			}

			ResultSet rs10 = stmt.executeQuery("SELECT * FROM achievement where ID = 10;");
			while (rs10.next()) {
				Doublecnt = rs10.getInt("Playcount");
				Sql_Day = rs10.getString("Day_time");
			}
			rs10.close();
			if (Doublecnt != 1) {
				sql = "UPDATE achievement set Playcount = Playcount +1 where ID = 10;";
				stmt.executeUpdate(sql);
				sql = "UPDATE achievement set Day_time = Date('now','localtime') where ID = 10;";
				stmt.executeUpdate(sql);
			} else if (Doublecnt == 1) {
				System.out.println("클릭이더블 도전과제 성공");
			}

			ResultSet rs11 = stmt.executeQuery("SELECT * FROM achievement where ID = 11;");
			while (rs11.next()) {
				Evolutioncnt = rs11.getInt("Playcount");
				Sql_Day = rs11.getString("Day_time");
			}
			rs11.close();
			if (Evolutioncnt != 1) {
				sql = "UPDATE achievement set Playcount = Playcount +1 where ID = 11;";
				stmt.executeUpdate(sql);
				sql = "UPDATE achievement set Day_time = Date('now','localtime') where ID = 11;";
				stmt.executeUpdate(sql);
			} else if (Evolutioncnt == 1) {
				System.out.println("인류의진화 도전과제 성공");
			}

			ResultSet rs12 = stmt.executeQuery("SELECT * FROM achievement where ID = 12;");
			while (rs12.next()) {
				Saryunancnt = rs12.getInt("Playcount");
				Sql_Day = rs12.getString("Day_time");
			}
			rs12.close();

			if (Saryunancnt != 1) {
				sql = "UPDATE achievement set Playcount = Playcount +1 where ID = 12;";
				stmt.executeUpdate(sql);
				sql = "UPDATE achievement set Day_time = Date('now','localtime') where ID = 12;";
				stmt.executeUpdate(sql);
			} else if (Saryunancnt == 1) {
				System.out.println("사륜안 도전과제 성공");
			}

			ResultSet rs13 = stmt.executeQuery("SELECT * FROM achievement where ID = 13;");
			while (rs13.next()) {
				Easycnt = rs13.getInt("Playcount");
				Sql_Day = rs13.getString("Day_time");
			}
			rs13.close();

			if (Easycnt != 5 && Successcontroller.eyeAchievementEasyValue == true) {
				sql = "UPDATE achievement set Playcount = Playcount +1 where ID = 13;";
				stmt.executeUpdate(sql);
			} else if (Easycnt == 5) {
				System.out.println("쉽죠 도전과제 성공");
			}

			ResultSet rs14 = stmt.executeQuery("SELECT * FROM achievement where ID = 14;");
			while (rs14.next()) {
				Novicecnt = rs14.getInt("Playcount");
				Sql_Day = rs14.getString("Day_time");
			}
			rs14.close();

			if (Novicecnt != 1 && Playcontroller.eyeAchievementNoviceValue == true) {
				sql = "UPDATE achievement set Playcount = Playcount +1 where ID = 14;";
				stmt.executeUpdate(sql);
				sql = "UPDATE achievement set Day_time = Date('now','localtime') where ID = 14;";
				stmt.executeUpdate(sql);
			} else if (Novicecnt == 1) {
				System.out.println("초보자 도전과제 성공");
			}

			ResultSet rs15 = stmt.executeQuery("SELECT * FROM achievement where ID = 15;");
			while (rs15.next()) {
				Freshcnt = rs15.getInt("Playcount");
				Sql_Day = rs15.getString("Day_time");
			}
			rs15.close();

			if (Freshcnt != 20 && (Playcontroller.eyeAchievementFreshValue == true
					|| eye.game.eyeMovement2.Playcontroller.eyeAchievementFreshValue == true)) {
				sql = "UPDATE achievement set Playcount = Playcount +1 where ID = 15;";
				stmt.executeUpdate(sql);
				sql = "UPDATE achievement set Day_time = Date('now','localtime') where ID = 15;";
				stmt.executeUpdate(sql);
			} else if (Freshcnt == 20) {
				System.out.println("깨끗한 도전과제 성공");
			}

			ResultSet rs16 = stmt.executeQuery("SELECT * FROM achievement where ID = 16;");
			while (rs16.next()) {
				Colorcnt = rs16.getInt("Playcount");
				Sql_Day = rs16.getString("Day_time");
			}
			rs16.close();

			if (Colorcnt != 7 && Successcontroller.eyeAchievementEasyValue == true) {
				sql = "UPDATE achievement set Playcount = Playcount +1 where ID = 16;";
				stmt.executeUpdate(sql);
				sql = "UPDATE achievement set Day_time = Date('now','localtime') where ID = 16;";
				stmt.executeUpdate(sql);
			} else if (Colorcnt == 7) {
				System.out.println("컬러아티스트 도전과제 성공");
			}

			ResultSet rs17 = stmt.executeQuery("SELECT * FROM achievement where ID = 17;");
			while (rs17.next()) {
				Voidcnt = rs17.getInt("Playcount");
				Sql_Day = rs17.getString("Day_time");
			}
			rs17.close();

			if (Voidcnt != 7 && Failcontroller.eyeAchievementVoidValue == true) {
				sql = "UPDATE achievement set Playcount = Playcount +1 where ID = 17;";
				stmt.executeUpdate(sql);
				sql = "UPDATE achievement set Day_time = Date('now','localtime') where ID = 17;";
				stmt.executeUpdate(sql);
			} else if (Voidcnt == 7) {
				System.out.println("안보여 도전과제 성공");
			}

			ResultSet rs18 = stmt.executeQuery("SELECT * FROM achievement where ID = 18;");
			while (rs18.next()) {
				Perfectcnt = rs18.getInt("Playcount");
				Sql_Day = rs18.getString("Day_time");
			}
			rs18.close();

			if (Perfectcnt != 1) {
				sql = "UPDATE achievement set Playcount = Playcount +1 where ID = 18;";
				stmt.executeUpdate(sql);
				sql = "UPDATE achievement set Day_time = Date('now','localtime') where ID = 18;";
				stmt.executeUpdate(sql);
			} else if (Perfectcnt == 1) {
				System.out.println("완벽주의자 도전과제 성공");
			}

			ResultSet rs19 = stmt.executeQuery("SELECT * FROM achievement where ID = 19;");
			while (rs19.next()) {
				Mistakecnt = rs19.getInt("Playcount");
				Sql_Day = rs19.getString("Day_time");
			}
			rs19.close();

			if (Mistakecnt != 1) {
				sql = "UPDATE achievement set Playcount = Playcount +1 where ID = 19;";
				stmt.executeUpdate(sql);
				sql = "UPDATE achievement set Day_time = Date('now','localtime') where ID = 19;";
				stmt.executeUpdate(sql);
			} else if (Mistakecnt == 1) {
				System.out.println("실수 도전과제 성공");
			}

			ResultSet rs20 = stmt.executeQuery("SELECT * FROM achievement where ID = 20;");
			while (rs20.next()) {
				Dangerouscnt = rs20.getInt("Playcount");
				Sql_Day = rs20.getString("Day_time");
			}
			rs20.close();

			if (Dangerouscnt != 1) {
				sql = "UPDATE achievement set Playcount = Playcount +1 where ID = 20;";
				stmt.executeUpdate(sql);
				sql = "UPDATE achievement set Day_time = Date('now','localtime') where ID = 20;";
				stmt.executeUpdate(sql);
			} else if (Dangerouscnt == 1) {
				System.out.println("아슬아슬 도전과제 성공");
			}

			stmt.close();
			c.close();

		} catch (Exception e) {
			System.out.println(e);
		}
		System.out.println("tsuccess");
	}

	public void achcnt() {
		Connection c = null;
		Statement stmt = null;

		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:eyeDB.db");

			stmt = c.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT * FROM achievement where ID = 3;");
			while (rs.next()) {
				WeekPlay = rs.getInt("Playcount");
			}
			rs.close();


			ResultSet rs2 = stmt.executeQuery("SELECT * FROM achievement where ID = 5;");
			while (rs2.next()) {
				MonthPlay = rs2.getInt("Playcount");
			}
			rs2.close();

			ResultSet rs4 = stmt.executeQuery("SELECT * FROM achievement where ID = 7;");
			while (rs4.next()) {
				Record = rs4.getInt("Playcount");
			}
			rs4.close();

			ResultSet rs5 = stmt.executeQuery("SELECT * FROM achievement where ID = 8;");
			while (rs5.next()) {
				Lucky = rs5.getInt("Playcount");
			}
			rs5.close();

			ResultSet rs6 = stmt.executeQuery("SELECT * FROM achievement where ID = 9;");
			while (rs6.next()) {
				Human = rs6.getInt("Playcount");
			}
			rs6.close();

			ResultSet rs7 = stmt.executeQuery("SELECT * FROM achievement where ID = 10;");
			while (rs7.next()) {
				Double = rs7.getInt("Playcount");
			}
			rs7.close();

			ResultSet rs8 = stmt.executeQuery("SELECT * FROM achievement where ID = 11;");
			while (rs8.next()) {
				Evolution = rs8.getInt("Playcount");
			}
			rs8.close();

			ResultSet rs9 = stmt.executeQuery("SELECT * FROM achievement where ID = 12;");
			while (rs9.next()) {
				Saryunan = rs9.getInt("Playcount");
			}
			rs9.close();

			ResultSet rs10 = stmt.executeQuery("SELECT * FROM achievement where ID = 13;");
			while (rs10.next()) {
				Easy = rs10.getInt("Playcount");
			}
			rs10.close();

			ResultSet rs11 = stmt.executeQuery("SELECT * FROM achievement where ID = 14;");
			while (rs11.next()) {
				Novice = rs11.getInt("Playcount");
			}
			rs11.close();

			ResultSet rs12 = stmt.executeQuery("SELECT * FROM achievement where ID = 15;");
			while (rs12.next()) {
				Fresh = rs12.getInt("Playcount");
			}
			rs12.close();

			ResultSet rs13 = stmt.executeQuery("SELECT * FROM achievement where ID = 16;");
			while (rs13.next()) {
				Color = rs13.getInt("Playcount");
			}
			rs13.close();

			ResultSet rs14 = stmt.executeQuery("SELECT * FROM achievement where ID = 17;");
			while (rs14.next()) {
				Void = rs14.getInt("Playcount");
			}
			rs14.close();

			ResultSet rs15 = stmt.executeQuery("SELECT * FROM achievement where ID = 18;");
			while (rs15.next()) {
				Perfect = rs15.getInt("Playcount");
			}
			rs15.close();

			ResultSet rs16 = stmt.executeQuery("SELECT * FROM achievement where ID = 19;");
			while (rs16.next()) {
				Mistake = rs16.getInt("Playcount");
			}
			rs16.close();

			ResultSet rs17 = stmt.executeQuery("SELECT * FROM achievement where ID = 20;");
			while (rs17.next()) {
				Dangerous = rs17.getInt("Playcount");
			}
			rs17.close();

			stmt.close();
			c.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
