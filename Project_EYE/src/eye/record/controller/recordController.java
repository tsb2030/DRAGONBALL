package eye.record.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import eye.main.Main;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import eye.db.*;
public class recordController implements Initializable{
	dbconn db = new dbconn();
	
	@FXML
	private LineChart recordChart;
	
	@FXML
	private ImageView backBtn;
	
	@FXML
	private AnchorPane ExplainPage,achieveMainPage;
	
	@FXML // 라벨
	Label zigzagTotal,totalRest,totalExercise,todayRest,todayExercise,orderedTotal,mobiusTotal,fiveDotTotal,findPictureTotal,catchMoleTotal,catchBallTotal;
	
	//db에서 가져올 값 예시
	String todayEx = "0"; // 오늘 운동
	String todayRe = "0"; // 오늘 휴식
	String totalEx = "10"; // 토탈 운동
	String totalRe = "10"; // 토탈 휴식
	String orderedTot = "1"; // 순서대로 토탈
	String mobiusTot = "1"; // 뫼비우스 토탈
	String zigzagTot = "3"; // 지그재그 토탈
	String fiveDotTot = "0"; //5점 카드 토탈
	String catchMoleTot = "0"; // 두더지 토탈
	String catchballTot = "1"; // 캐치볼 토탈
	String findPictureTot = "3"; // 같은그림 찾기 토탈
	
	
	
	// 주간 날짜 배열 예시
	private String[] weekDays = {"5/1","5/2","5/3","5/4","5/5","5/6","5/7"};

	// 월간 날짜 배열 예시
	private String[] monthDays = {"5/1","5/2","5/3","5/4","5/5","5/6","5/7","5/8","5/9","5/10","5/11","5/12","5/13","5/14","5/15","5/16","5/17","5/18","5/19","5/20","5/21","5/22","5/23","5/24","5/25","5/26","5/27","5/28","5/29","5/30","5/31"};

	// -------게임 기록--------

	// 주간 게임 기록 예시
	private int[] aWeekGameData = {3,4,6,7,9,1,2};

	// 한달간 게임 기록 예시 
	private int[] aMonthGameData = {3,4,6,3,3,6,7,8,8,1,3,4,6,3,8,6,7,2,8,1,3,5,5,2,3,4,1,2,4,2,3};

	// -------휴식 기록--------

	// 주간 휴식 기록 에시
	private int[] aWeekRestData = {2,1,4,5,1,2,3};

	// 한달간 휴식 기록 예시 
	private int[] aMonthRestData = {1,1,3,2,1,1,0,0,0,2,3,3,3,3,3,6,7,2,2,1,3,3,5,2,3,4,1,2,4,2,3};

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

		SimpleDateFormat sDateForm = new SimpleDateFormat("yyyy/MM/dd");
		Date currentTime = new Date();
		String cTime = sDateForm.format(currentTime);
		
		// 전체 휴식 / 오늘 휴식
		totalRest.setText(totalRe);
		todayRest.setText(todayRe);
		
		// 전체 게임 / 오늘 게임
		try {
			totalEx = Integer.toString(db.getTotalEx());
			todayEx = Integer.toString(db.getTodayEx(cTime));
			System.out.println("totalEx = "+totalEx+" todayEx= "+todayEx);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		totalExercise.setText(totalEx);
		todayExercise.setText(todayEx);
		
		// 지그재그 전체 게임 횟수 가져오기
		zigzagTotal.setText(zigzagTot);
		// 지그재그 정답 횟수 가져오기
		
		// 뫼비우스 전체 게임 횟수 가져오기
		mobiusTotal.setText(mobiusTot);
		// 뫼비우스 정답 횟수 가져오기
		
		// 5점 카드 트레이닝 전체 게임 횟수 가져오기
		fiveDotTotal.setText(fiveDotTot);
		// 순서대로 전체 횟수
		orderedTotal.setText(orderedTot);
		// 캐치볼 전체 횟수
		catchBallTotal.setText(catchballTot);
		// 두더지 전체 횟수
		catchMoleTotal.setText(catchMoleTot);
		// 같은 그림 찾기 전체 횟수
		findPictureTotal.setText(findPictureTot);
		
		backBtn.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				try {
					Main.setMusic("introMusic", true);
					Parent recordPage = FXMLLoader.load(getClass().getResource("/eye/main/view/main_page.fxml"));
					Scene scene = new Scene(recordPage);
					scene.getStylesheets()
							.add(getClass().getResource("/eye/main/controller/application.css").toExternalForm());
					Stage primaryStage = (Stage) backBtn.getScene().getWindow();
					primaryStage.setScene(scene);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// 주간 기록 버튼
	public void weekButton(ActionEvent e) {

		//차트 타이틀 변경
		recordChart.setTitle("주간 게임/휴식 기록");


		//데이터 생성
		XYChart.Series<String,Integer> gameData = new XYChart.Series<>();
		gameData.setName("게임 횟수");

		XYChart.Series<String,Integer> restData = new XYChart.Series<>();
		restData.setName("휴식 횟수");

		//게임 데이터 삽입
		for(int i = 0 ; i<aWeekGameData.length ; i++) {
			gameData.getData().add(new XYChart.Data<String,Integer>(weekDays[i], aWeekGameData[i]));

		}

		//휴식 데이터 삽입
		for(int i = 0 ; i<aWeekRestData.length ; i++) {
			restData.getData().add(new XYChart.Data<String,Integer>(weekDays[i], aWeekRestData[i]));

		}

		//이전 차트 지우기
		recordChart.getData().clear();

		// 차트에 데이터 삽입
		recordChart.getData().add(gameData);
		recordChart.getData().add(restData);


	}

	// 한달간 기록 버튼 일단...
	public void monthButton(ActionEvent e) {		
		// 차트 타이틀 변경
		recordChart.setTitle("한달간 게임/휴식 기록");

		// 데이터 생성
		XYChart.Series<String,Integer> gameData = new XYChart.Series<>();
		gameData.setName("게임 횟수");

		XYChart.Series<String,Integer> restData = new XYChart.Series<>();
		restData.setName("휴식 횟수");

		// 게임 데이터 삽입
		for(int i = 0 ; i<aMonthGameData.length ; i++) {
			gameData.getData().add(new XYChart.Data<String,Integer>(monthDays[i], aMonthGameData[i]));

		}

		// 휴식 데이터 삽입
		for(int i = 0 ; i<aMonthRestData.length ; i++) {
			restData.getData().add(new XYChart.Data<String,Integer>(monthDays[i], aMonthRestData[i]));

		}

		//이전 차트 지우기
		recordChart.getData().clear();

		// 차트에 데이터 삽입
		recordChart.getData().add(gameData);
		recordChart.getData().add(restData);

	}
	// 1to50 record 팝업 띄우기
	public void oneToFiftyButton(ActionEvent e) {
		FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/eye/record/view/OrderedPopUp.fxml"));

		try {
			AnchorPane anotherPage = (AnchorPane) fxmlloader.load();
			Scene Scene = new Scene(anotherPage);
			Scene.getStylesheets()
			.add(getClass().getResource("/eye/record/view/recordDesign.css").toExternalForm());
			Stage stage = new Stage();
			stage.setScene(Scene);
			stage.show();


		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	//		// 시선이동트레이닝 - 뫼비우스 record 팝업 띄우기
	public void eyeMovementButton(ActionEvent e) {
		FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/eye/record/view/MobiusPopUp.fxml"));
		try {
			AnchorPane anotherPage = (AnchorPane) fxmlloader.load();
			Scene Scene = new Scene(anotherPage);
			Scene.getStylesheets()
			.add(getClass().getResource("/eye/record/view/recordDesign.css").toExternalForm());
			Stage stage = new Stage();
			stage.setScene(Scene);
			stage.show();


		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();

		}
	}


			// 시선이동트레이닝 - 지그재그 record 팝업 띄우기
			public void eyeMovementButton2(ActionEvent e) {
				FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/eye/record/view/ZigzagPopUp.fxml"));
				
				try {
			AnchorPane anotherPage = (AnchorPane) fxmlloader.load();
			Scene Scene = new Scene(anotherPage);
			Scene.getStylesheets()
					.add(getClass().getResource("/eye/record/view/recordDesign.css").toExternalForm());
			Stage stage = new Stage();
			stage.setScene(Scene);
			stage.show();
	//
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
	
				}
			}



	//  두더지 잡기 record 팝업 띄우기
	public void catchMoleGameButton(ActionEvent e) {
		FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/eye/record/view/CatchMolePopUp.fxml"));

		try {
			AnchorPane anotherPage = (AnchorPane) fxmlloader.load();
			Scene Scene = new Scene(anotherPage);
			Scene.getStylesheets()
			.add(getClass().getResource("/eye/record/view/recordDesign.css").toExternalForm());
			Stage stage = new Stage();
			stage.setScene(Scene);
			stage.show();


		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	// 캐치 볼 record 팝업 띄우기
	public void catchBallButton(ActionEvent e) {
		FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/eye/record/view/CatchBallPopUp.fxml"));

		try {
			AnchorPane anotherPage = (AnchorPane) fxmlloader.load();
			Scene Scene = new Scene(anotherPage);
			Scene.getStylesheets()
			.add(getClass().getResource("/eye/record/view/recordDesign.css").toExternalForm());
			Stage stage = new Stage();
			stage.setScene(Scene);
			stage.show();


		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	// 같은 그림 찾기 record 팝업 띄우기
	public void findPictureButton(ActionEvent e) {
		FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/eye/record/view/FindPicturePopUp.fxml"));
		Parent root;
		try {
			AnchorPane anotherPage = (AnchorPane) fxmlloader.load();
			Scene Scene = new Scene(anotherPage);
			Scene.getStylesheets()
			.add(getClass().getResource("/eye/record/view/recordDesign.css").toExternalForm());
			Stage stage = new Stage();
			stage.setScene(Scene);
			stage.show();

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
	
	public void AchievementButton(ActionEvent e) {
		try {
			Parent recordPage = FXMLLoader.load(getClass().getResource("/eye/record/view/achieveMain.fxml"));
			Scene scene = new Scene(recordPage);
			scene.getStylesheets()
					.add(getClass().getResource("/eye/main/controller/application.css").toExternalForm());
			Stage primaryStage = (Stage) backBtn.getScene().getWindow();
			primaryStage.setScene(scene);

		} catch (IOException e1) {
		}

	}
	
	public void ExamButton(ActionEvent e) {
		try {
			Parent recordPage = FXMLLoader.load(getClass().getResource("/eye/record/view/ExamExplain.fxml"));
			Scene scene = new Scene(recordPage);
			scene.getStylesheets()
					.add(getClass().getResource("/eye/main/controller/application.css").toExternalForm());
			Stage primaryStage = (Stage) backBtn.getScene().getWindow();
			primaryStage.setScene(scene);
		} catch (IOException e1) {
		}

	}
	



}
