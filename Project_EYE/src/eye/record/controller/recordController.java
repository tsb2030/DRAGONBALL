package eye.record.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import eye.db.dbconn;
import eye.main.Main;
import eye.record.model.timesModel;
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
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
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

	@FXML
	Label mobCorrect,mobWinningAvg,zigCorrect,zigWinningAvg;

	//휴식 라벨
	@FXML
	Label closeEye,seeFar,massage,rollEye,warmEye;

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
	String mobCor = "0";
	String mobWinAvg = "0";
	String zigCor = "0";
	String zigWinAvg = "0";

	// 휴식 값 예시
	String clolseEyeTotal = "0";
	String seeFarTotal = "0";
	String massageTotal = "0";
	String rollEyeTotal = "0";
	String warmEyeTotal = "0";



	// 주간 날짜 배열 예시
	private String[] weekDays = new String[7];

	// 월간 날짜 배열 예시
	private String[] monthDays = new String[30];
	// -------게임 기록--------

	// 주간 게임 기록 예시
	private int[] aWeekGameData = new int[7];

	// 한달간 게임 기록 예시 
	private int[] aMonthGameData = new int[30];

	// -------휴식 기록--------

	// 주간 휴식 기록 에시
	private int[] aWeekRestData = new int[7];

	// 한달간 휴식 기록 예시 
	private int[] aMonthRestData = new int[30];

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

		SimpleDateFormat sDateForm = new SimpleDateFormat("yyyy/MM/dd");
		Date currentTime = new Date();
		String cTime = sDateForm.format(currentTime);

		// 전체 휴식 / 오늘 휴식
		try {
			totalRe = Integer.toString(db.getTotalRest());
			todayRe = Integer.toString(db.getTodayRest(cTime));
			System.out.println("totalRe = "+totalRe+" todayRe= "+todayRe);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
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
		try {
			zigzagTot = Integer.toString(db.getTotalEx("zigzag"));
			System.out.println("zigzagTot = "+zigzagTot);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		zigzagTotal.setText(zigzagTot);

		// 지그재그 정답 횟수 가져오기
		try {
			zigCor = Integer.toString(db.getCorRecord("zigzag"));
			System.out.println("zigCor = "+zigCor);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		zigCorrect.setText(zigCor);

		// 지그재그 승률
		Double zigCorNum = Double.parseDouble(zigCor);
		Double zigzagTotNum = Double.parseDouble(zigzagTot);
		Double zigavg = ((Double)zigCorNum/(Double)zigzagTotNum)*100;
		zigWinAvg = Double.toString(zigavg);
		System.out.println("지그재그 승률 = "+zigWinAvg);
		zigWinningAvg.setText(zigWinAvg+" %");

		// 뫼비우스 전체 게임 횟수 가져오기
		try {
			mobiusTot = Integer.toString(db.getTotalEx("mobius"));
			System.out.println("mobiusTot = "+mobiusTot);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		mobiusTotal.setText(mobiusTot);

		// 뫼비우스 정답 횟수 가져오기
		try {
			mobCor = Integer.toString(db.getCorRecord("mobius"));
			System.out.println("mobCor = "+mobCor);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		mobCorrect.setText(mobCor);

		// 뫼비우스 승률
		Double mobCorNum = Double.parseDouble(mobCor);
		Double mobiusTotNum = Double.parseDouble(mobiusTot);
		Double mobavg = ((Double)mobCorNum/(Double)mobiusTotNum)*100;
		mobWinAvg = Double.toString(mobavg);
		mobWinningAvg.setText(mobWinAvg);

		// 5점 카드 트레이닝 전체 게임 횟수 가져오기
		try {
			fiveDotTot = Integer.toString(db.getTotalEx("fiveDot"));
			System.out.println("fiveDotTot = "+fiveDotTot);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		fiveDotTotal.setText(fiveDotTot);

		// 순서대로 전체 횟수
		try {
			orderedTot = Integer.toString(db.getTotalEx("follow"));
			System.out.println("orderedTot = "+orderedTot);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		orderedTotal.setText(orderedTot);

		// 캐치볼 전체 횟수
		try {
			catchballTot = Integer.toString(db.getTotalEx("catchBall"));
			System.out.println("catchballTot = "+catchballTot);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		catchBallTotal.setText(catchballTot);

		// 두더지 전체 횟수
		try {
			catchMoleTot = Integer.toString(db.getTotalEx("catchMole"));
			System.out.println("catchMoleTot = "+catchMoleTot);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		catchMoleTotal.setText(catchMoleTot);
		// 같은 그림 찾기 전체 횟수
		try {
			findPictureTot = Integer.toString(db.getTotalEx("findPicture"));
			System.out.println("findPictureTot = "+findPictureTot);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		findPictureTotal.setText(findPictureTot);

		// 휴식 total
		// 눈 꼭 감기
		try {
			clolseEyeTotal = Integer.toString(db.getRest("ClosedEyeRest"));
			System.out.println("clolseEyeTotal = "+clolseEyeTotal);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		closeEye.setText(clolseEyeTotal);

		// 멀리보기
		try {
			seeFarTotal = Integer.toString(db.getRest("lookAfarRest"));
			System.out.println("seeFarTotal = "+seeFarTotal);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		seeFar.setText(seeFarTotal);

		// 눈 마사지
		try {
			massageTotal = Integer.toString(db.getRest("eyeMassageRest"));
			System.out.println("massageTotal = "+massageTotal);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		massage.setText(massageTotal);

		// 눈 굴리기
		try {
			rollEyeTotal = Integer.toString(db.getRest("eyeRollingRest"));
			System.out.println("rollEyeTotal = "+rollEyeTotal);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		rollEye.setText(rollEyeTotal);

		// 온찜질
		try {
			warmEyeTotal = Integer.toString(db.getRest("warmEyeRest"));
			System.out.println("warmEyeTotal = "+warmEyeTotal);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		warmEye.setText(warmEyeTotal);

		backBtn.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				try {
					Main.setMusic("introMusic", true, 1);
					Parent recordPage = FXMLLoader.load(getClass().getResource("/eye/main/view/main_page.fxml"));
					Scene scene = new Scene(recordPage);
					scene.getStylesheets()
					.add(getClass().getResource("/eye/main/controller/application.css").toExternalForm());
					Stage primaryStage = (Stage) backBtn.getScene().getWindow();
					primaryStage.setResizable(false);
					primaryStage.setScene(scene);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// 주간 기록 버튼
	public void weekButton(ActionEvent e) {
		//운동
		List<timesModel> exer =  new ArrayList<timesModel>();
		try {
			exer = db.getWeekData("exercise");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("출력");
		for(int i =0;i<exer.size();i++) {
			timesModel rcm = exer.get(i);
			System.out.println("date = "+rcm.getDate()+" num = "+rcm.getCnt());
			weekDays[i]=rcm.getDate();
			aWeekGameData[i] = rcm.getCnt();
		}
		//휴식
		List<timesModel> restM =  new ArrayList<timesModel>();
		try {
			restM = db.getWeekData("rest");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("출력");
		for(int i =0;i<restM.size();i++) {
			timesModel rcm = restM.get(i);
			System.out.println("date = "+rcm.getDate()+" num = "+rcm.getCnt());
			weekDays[i]=rcm.getDate();
			aWeekRestData[i] = rcm.getCnt();
		}
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
		//운동
				List<timesModel> exer =  new ArrayList<timesModel>();
				try {
					exer = db.getMonthData("exercise");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println("출력");
				for(int i =0;i<exer.size();i++) {
					timesModel rcm = exer.get(i);
					System.out.println("date = "+rcm.getDate()+" num = "+rcm.getCnt());
					monthDays[i]=rcm.getDate();
					aMonthGameData[i] = rcm.getCnt();
				}
				//휴식
				List<timesModel> restM =  new ArrayList<timesModel>();
				try {
					restM = db.getMonthData("rest");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println("출력");
				for(int i =0;i<restM.size();i++) {
					timesModel rcm = restM.get(i);
					System.out.println("date = "+rcm.getDate()+" num = "+rcm.getCnt());
					monthDays[i]=rcm.getDate();
					aMonthRestData[i] = rcm.getCnt();
				}
				
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
			stage.setResizable(false);
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
			stage.setResizable(false);
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
			stage.setResizable(false);
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
			stage.setResizable(false);
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
			stage.setResizable(false);
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
			stage.setResizable(false);
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
			primaryStage.setResizable(false);
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
			primaryStage.setResizable(false);
			primaryStage.setScene(scene);
		} catch (IOException e1) {
		}

	}




}
