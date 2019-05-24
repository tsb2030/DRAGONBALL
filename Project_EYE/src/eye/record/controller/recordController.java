package eye.record.controller;

import java.io.IOException;
import java.text.DateFormatSymbols;
import java.util.Locale;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class recordController {

	@FXML
	private LineChart recordChart;

	// (일~토) 배열
	private String[] weekDays = DateFormatSymbols.getInstance(Locale.KOREAN).getShortWeekdays();

	// 월/일 배열 예시
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
			gameData.getData().add(new XYChart.Data<String,Integer>(weekDays[i+1], aWeekGameData[i]));

		}

		//휴식 데이터 삽입
		for(int i = 0 ; i<aWeekRestData.length ; i++) {
			restData.getData().add(new XYChart.Data<String,Integer>(weekDays[i+1], aWeekRestData[i]));

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
		FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/eye/record/view/MovePopUp.fxml"));
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


	//		// 시선이동트레이닝 - 지그재그 record 팝업 띄우기
	//		public void eyeMovementButton2(ActionEvent e) {
	//			FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/eye/record/view/MovePopUp2.fxml"));
	//			
	//			try {
	//		AnchorPane anotherPage = (AnchorPane) fxmlloader.load();
	//		Scene Scene = new Scene(anotherPage);
	//		Scene.getStylesheets()
	//				.add(getClass().getResource("/eye/record/view/recordDesign.css").toExternalForm());
	//		Stage stage = new Stage();
	//		stage.setScene(Scene);
	//		stage.show();
	////
	//			} catch (IOException e1) {
	//				// TODO Auto-generated catch block
	//				e1.printStackTrace();
	//
	//			}
	//		}



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




}
