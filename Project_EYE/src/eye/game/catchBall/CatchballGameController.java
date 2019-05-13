package eye.game.catchBall;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import com.jfoenix.controls.JFXButton;

import eye.main.Main;
import javafx.animation.Animation.Status;
import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Polyline;
import javafx.stage.Stage;
import javafx.util.Duration;

public class CatchballGameController implements Initializable {

	// 이전 페이지에서 가져온 속도값
	SelectSpeedPageController sspc = new SelectSpeedPageController();

	@FXML
	private AnchorPane bigPanne; // 전체화면 지정

	@FXML
	private AnchorPane GamePane; // 안에 AnchorPane지정

	@FXML
	private Button pausebutton; // 일시 정지 버튼

	// 현재 스체이지 저장
	public static Stage currentStage;
	// (Stage) startButton.getScene().getWindow();

	@FXML
	private Label timeLabel; // 시간초 표시

	@FXML
	private Label ScoreLabel; // 점수 표시

	@FXML
	private Label judgeYourBehavior; // 한 판당 너가 잘했는지 못했는지 말해줌ㅇ

	private static double numX1 = 50; // 1번 좌표
	private static double numY1 = 50;

	private static double numX2 = 500; // 2번 좌표
	private static double numY2 = 50;

	private static double numX3 = 950; // 3번 좌표
	private static double numY3 = 50;

	private static double numX4 = 50; // 4번 좌표
	private static double numY4 = 250;

	private static double numX5 = 500; // 5번 좌표
	private static double numY5 = 250;

	private static double numX6 = 950; // 6번 좌표
	private static double numY6 = 250;

	private static double numX7 = 50; // 7번 좌표
	private static double numY7 = 450;

	private static double numX8 = 500; // 8번 좌표
	private static double numY8 = 450;

	private static double numX9 = 950; // 9번 좌표
	private static double numY9 = 450;

	private boolean flag = false;
	@FXML
	private Circle followCircle; // 돌아댕기는 원

	@FXML
	private Circle catchCircle; // 따라다니는 원

	double speedValue = SelectSpeedPageController.gameSpeed;

	private double catchCircleX = 50.0; // catchBallCircle의 처음 좌표
	private double catchCircleY = 50.0;

	@FXML
	private Button startButton; // pause되었을 때 restart기능

	@FXML
	private JFXButton backButton; // 이전 창으로 돌아가는 기능

	// 선택된 라인을 저장할 배열 선언
	private double[] checkLine = new double[16];

	// 맞았는지 틀렸는지검사
	private boolean correct = true;

	// 총 몇번 틀렸는지
	private int falseCount = 0;

	// 한 판에 점수
	private int smallScore = 0;

	// 총 점수
	public static int bigScore = 0;

	// lineIndex
	private int lineIndex = -1;

	// Timer 설정
	private Clock timer;

	// 경로 그려주는 라인
	private Line line;

	// 경로에 하나씩 추가하기위한 index
	private int lineRouteIndex = 0;

	private Line[] lines = new Line[8];

	private PathTransition catchBalltransition;

	private PathTransition followBalltransition;

	private boolean justOne = false;

	// transition정의
	PathTransition transition;

	// 게임 시간을 저장해서 리턴해줄 정적 변수
	public static int timeTime;

	// 화면이 처음 켜졌을 경우 실행되는 메소드들
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		timer = new Clock();
		// 처음엔 모든 circle들이 보이지 않게 설정
		followCircle.setVisible(false);
		catchCircle.setVisible(false);
		followCircleStart();

	}

	// 게임이 종료되었을 때 실행되는 메소드
	public void gameOver()
			throws UnsupportedAudioFileException, IOException, LineUnavailableException, URISyntaxException {
		currentStage = (Stage) pausebutton.getScene().getWindow();
		// 시간이 종료되거나, 3번 틀릴 경우
		if (justOne == false) {
			if (timer.getTime() <= 0 || falseCount > 2) {
				timer.animation.pause();
				bigPanne.setOpacity(0.45);
				if (followBalltransition.getStatus() == Status.RUNNING)
					followBalltransition.pause();

				if (catchBalltransition.getStatus() == Status.RUNNING)
					catchBalltransition.pause();
				FXMLLoader EndGamePopup = new FXMLLoader(Main.class.getResource("../game/catchBall/EndGamePopup.fxml"));

				justOne = true;
				try {
					AnchorPane anotherPage = (AnchorPane) EndGamePopup.load();
					// 다른창 띄우는 작업 .... 2
					Scene EndGamePopupScene = new Scene(anotherPage);
					Stage stage = new Stage();
					stage.setScene(EndGamePopupScene);
					stage.show();
					// 다른창 띄우는 작업 .... 2 끝.
				} catch (IOException e) {
				}
			}

		}

	}

	// line의 값을 하나 하나 복붙한다.
	public void setCheckLine(double[] line) {
		for (int i = 0; i < line.length; i++) {
			this.checkLine[i] = line[i];
		}
	}

	// Restart 기능으로 생각
	@FXML
	void startGame(MouseEvent event) {
		timer.animation.play();
		bigPanne.setOpacity(1.0);
		if (followBalltransition.getStatus() == Status.PAUSED)
			followBalltransition.play();
		if (catchBalltransition.getStatus() == Status.PAUSED)
			catchBalltransition.play();
	}

	@FXML
	void backButtonAction(ActionEvent event) {
		try {
			Parent SelectSpeedPage = FXMLLoader.load(getClass().getResource("SelectSpeedPage.fxml")); // 불러올 페이지 지정
			Scene scene = new Scene(SelectSpeedPage);
//				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());  // css 지정
			Stage primaryStage = (Stage) backButton.getScene().getWindow(); // 현재 윈도우 가져오기
			primaryStage.setScene(scene);
			primaryStage.setTitle("SelectSpeed");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//
	@FXML
	void pauseEvent(ActionEvent event) {
		// pauseEvent Start!
		timer.animation.pause();
		bigPanne.setOpacity(0.45);
		if (followBalltransition.getStatus() == Status.RUNNING)
			followBalltransition.pause();
		if (catchBalltransition.getStatus() == Status.RUNNING)
			catchBalltransition.pause();
	}

	// 배열을 0으로 초기화... 사실 필요한가 싶다. 시간날때 천천히 생각해보자
	public double[] initArray(double[] line) {
		for (int i = 0; i < line.length; i++) {
			line[i] = 0;
		}
		return line;
	}

	public void drawLine(double startX, double startY, double endX, double endY) {
		line = new Line(startX, startY, endX, endY);
		line.setStroke(Color.LIGHTGREEN);
		line.getStrokeDashArray().addAll(20d, 10d);
		line.setStrokeWidth(5);
		addLines(line);
		// 나중에 빨간 공의 움직임을 Thread로 구현하게 된다면 건드려볼만한 내용
//		FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1), line);
//		fadeTransition.setFromValue(0.0);
//		fadeTransition.setToValue(0.75);
//		fadeTransition.play();
		GamePane.getChildren().addAll(line);
	}

	// 라인들을 모두 안보이게 해주는 메소드
	public void InVisibleLine() {
		boolean lineNull = false;
		for (int i = 0; i < lines.length; i++) {
			if (lines[i] == null) {
				lineNull = true;
				for (int j = 0; j < i; j++) {
					lines[j].setOpacity(0);
				}
				break;
			}

		}
		if (lineNull == false)
			for (int i = 0; i < lines.length; i++) {
				lines[i].setOpacity(0);
			}
		lineRouteIndex = 0;
	}

	// 한 판에 그릴 라인들을 모두 모아줄 메소드
	public void addLines(Line line) {
		lines[lineRouteIndex] = line;
		lineRouteIndex++;
	}

	@FXML
	void keyEventHandler(KeyEvent event) throws InterruptedException, UnsupportedAudioFileException, IOException,
			LineUnavailableException, URISyntaxException {
		String yourBehavior = "";
		if (flag == true) {
			catchBalltransition = new PathTransition();
			catchBalltransition.setNode(catchCircle);
			catchBalltransition.setDuration(Duration.seconds(speedValue / 8));
//			if(followBalltransition.getStatus() == Status.RUNNING)
//				catchBalltransition.pause();

			switch (event.getCode()) {
			case UP:

				Polyline polyLineStepUp = new Polyline();
				if (catchCircleY <= 60)
					return;
				else {
					polyLineStepUp.getPoints().addAll(
							new Double[] { catchCircleX, catchCircleY, catchCircleX, catchCircleY - (250 - 100 / 2) });

					catchBalltransition.setPath(polyLineStepUp);
					catchBalltransition.setCycleCount(1);
					catchBalltransition.play();
					catchCircleY = catchCircleY - (250 - 100 / 2);

					// 한 번의 움직임이 맞았는지 틀렸는지 체크

					// 한번의 이동이 맞은 경우
					if (checkLine[++lineIndex] == catchCircleX && checkLine[++lineIndex] == catchCircleY) {
						smallScore++;
//						Thread.sleep(500);
						drawLine(catchCircleX, catchCircleY + 200, catchCircleX, catchCircleY);
						ScoreLabel.setText(String.valueOf(bigScore + smallScore));
					} else { // 한번의 움직임이 틀릴 경우
						correct = false;
						falseCount++;
					}

					// 게임 한 판이 종료 되는지 아닌지 검사
					if (smallScore == 8 || correct == false) {
						switch (lineIndex) {
						case 0:case 1:case 2:case 3:
							yourBehavior = "Animal";
							break;
						case 4:case 5:case 6:case 7:
							yourBehavior = "Australopithecus";
							break;

						case 8:case 9:case 10:case 11:
							yourBehavior = "Homo sapiens";
							break;

						case 12:case 13:case 14:case 15:
							yourBehavior = "Human";
							break;

						default:
							break;
						}
						judgeYourBehavior.setText(yourBehavior);
						bigScore += smallScore;
						smallScore = 0;
						correct = true;
						checkLine = initArray(checkLine); // 배열을 다시 0으로 초기화
						lineIndex = -1; // 배열을 검사할 인데스 초기화
						InVisibleLine();
						catchCircle.setVisible(false);
						followCircleStart(); // 다시 followCircleStart()시작
					}
					// 틀려서 게임이 종료될 경우
					gameOver();

				}

				break;
			case DOWN:

				Polyline polyLineStepDown = new Polyline();
				if (catchCircleY >= 440)
					return;
				else {
					polyLineStepDown.getPoints().addAll(
							new Double[] { catchCircleX, catchCircleY, catchCircleX, catchCircleY + (250 - 100 / 2) });

					catchBalltransition.setPath(polyLineStepDown);
					catchBalltransition.setCycleCount(1);
					catchBalltransition.play();
					catchCircleY = catchCircleY + (250 - 100 / 2);
				}
				// 한 번의 움직임이 맞았는지 틀렸는지 체크
				if (checkLine[++lineIndex] == catchCircleX && checkLine[++lineIndex] == catchCircleY) {
					smallScore++;
//					Thread.sleep(500);
					drawLine(catchCircleX, catchCircleY - 200, catchCircleX, catchCircleY);
					ScoreLabel.setText(String.valueOf(bigScore + smallScore));
				} else {
					correct = false;
					falseCount++;
				}

				// 게임 한 판이 종료 되는지 아닌지 검사
				if (smallScore == 8 || correct == false) {
					switch (lineIndex) {
					case 0:case 1:case 2:case 3:
						yourBehavior = "Animal";
						break;
					case 4:case 5:case 6:case 7:
						yourBehavior = "Australopithecus";
						break;

					case 8:case 9:case 10:case 11:
						yourBehavior = "Homo sapiens";
						break;

					case 12:case 13:case 14:case 15:
						yourBehavior = "Human";
						break;

					default:
						break;
					}
					judgeYourBehavior.setText(yourBehavior);
					bigScore += smallScore;
					smallScore = 0;
					correct = true;
					checkLine = initArray(checkLine);
					lineIndex = -1;
					InVisibleLine();
					catchCircle.setVisible(false);
					followCircleStart();
				}
				// 틀려서 게임이 종료될 경우
				gameOver();

				break;
			case LEFT:

				Polyline polyLineStepLeft = new Polyline();
				if (catchCircleX <= 60)
					return;
				else {
					polyLineStepLeft.getPoints().addAll(
							new Double[] { catchCircleX, catchCircleY, catchCircleX - (500 - 100 / 2), catchCircleY });

					catchBalltransition.setPath(polyLineStepLeft);
					catchBalltransition.setCycleCount(1);
					catchBalltransition.play();
					catchCircleX = catchCircleX - (500 - 100 / 2);
				}
				// 한 번의 움직임이 맞았는지 틀렸는지 체크
				if (checkLine[++lineIndex] == catchCircleX && checkLine[++lineIndex] == catchCircleY) {
					smallScore++;
//					Thread.sleep(500);
					drawLine(catchCircleX + 450, catchCircleY, catchCircleX, catchCircleY);
					ScoreLabel.setText(String.valueOf(bigScore + smallScore));
				} else {
					correct = false;
					falseCount++;
				}

				// 게임 한 판이 종료 되는지 아닌지 검사
				if (smallScore == 8 || correct == false) {
					switch (lineIndex) {
					case 0:case 1:case 2:case 3:
						yourBehavior = "Animal";
						break;
					case 4:case 5:case 6:case 7:
						yourBehavior = "Australopithecus";
						break;

					case 8:case 9:case 10:case 11:
						yourBehavior = "Homo sapiens";
						break;

					case 12:case 13:case 14:case 15:
						yourBehavior = "Human";
						break;

					default:
						break;
					}
					judgeYourBehavior.setText(yourBehavior);
					bigScore += smallScore;
					smallScore = 0;
					correct = true;
					checkLine = initArray(checkLine);
					lineIndex = -1;
					InVisibleLine();
					catchCircle.setVisible(false);
					followCircleStart();
				}
				// 틀려서 게임이 종료될 경우
				gameOver();

				break;
			case RIGHT:

				Polyline polyLineStepRight = new Polyline();
				if (catchCircleX >= 940)
					return;
				else {
					polyLineStepRight.getPoints().addAll(
							new Double[] { catchCircleX, catchCircleY, catchCircleX + (500 - 100 / 2), catchCircleY });

					catchBalltransition.setPath(polyLineStepRight);
					catchBalltransition.setCycleCount(1);
					catchBalltransition.play();
					catchCircleX = catchCircleX + (500 - 100 / 2);

				}
				// 한 번의 움직임이 맞았는지 틀렸는지 체크
				if (checkLine[++lineIndex] == catchCircleX && checkLine[++lineIndex] == catchCircleY) {
					smallScore++;
//					Thread.sleep(500);
					drawLine(catchCircleX - 450, catchCircleY, catchCircleX, catchCircleY);
					ScoreLabel.setText(String.valueOf(bigScore + smallScore));
				} else {
					correct = false;
					falseCount++;
				}

				// 게임 한 판이 종료 되는지 아닌지 검사
				if (smallScore == 8 || correct == false) {
					switch (lineIndex) {
					case 0:case 1:case 2:case 3:
						yourBehavior = "Animal";
						break;
					case 4:case 5:case 6:case 7:
						yourBehavior = "Australopithecus";
						break;

					case 8:case 9:case 10:case 11:
						yourBehavior = "Homo sapiens";
						break;

					case 12:case 13:case 14:case 15:
						yourBehavior = "Human";
						break;

					default:
						break;
					}
					judgeYourBehavior.setText(yourBehavior);
					bigScore += smallScore;
					smallScore = 0;
					correct = true;
					checkLine = initArray(checkLine);
					lineIndex = -1;
					InVisibleLine();
					catchCircle.setVisible(false);
					followCircleStart();
				}
				// 틀려서 게임이 종료될 경우
				gameOver();
				break;

			default:
				System.out.println("currentSpeed? = " + speedValue);
				break;
			}
		}

	}

	public void followCircleStart() {

		// catchCircle으르 안보이게 설정
		catchCircle.setVisible(false);
		flag = false;

		// 스피드 값을 받아오고

		// follow를 시작
		followCircle.setVisible(true);
		followBalltransition = new PathTransition();
		followBalltransition.setNode(followCircle);
		followBalltransition.setDuration(Duration.seconds(speedValue / 2 + 3));

		Path path = new Path();
		// 1~4까지 랜덤으로 int값 출력
		double ranValue = Math.random();
		int value = (int) (ranValue * 4) + 1;
		switch (value) {
		case 1:
			// 시작 위치 1번

			path.getElements().add(new MoveTo(numX1, numY1));

			double ranValueLine = Math.random();
			int line = (int) (ranValueLine * 8) + 1;
			switch (line) {

			case 1:
				path.getElements().add(new LineTo(numX2, numY2));
				path.getElements().add(new LineTo(numX5, numY5));
				path.getElements().add(new LineTo(numX4, numY4));
				path.getElements().add(new LineTo(numX7, numY7));
				path.getElements().add(new LineTo(numX8, numY8));
				path.getElements().add(new LineTo(numX9, numY9));
				path.getElements().add(new LineTo(numX6, numY6));
				path.getElements().add(new LineTo(numX3, numY3));

				followBalltransition.setPath(path);
				followBalltransition.play();

				double[] newline1 = { numX2, numY2, numX5, numY5, numX4, numY4, numX7, numY7, numX8, numY8, numX9,
						numY9, numX6, numY6, numX3, numY3 };

				setCheckLine(newline1);

				// setAnswerPath
				break;
			case 2:
				path.getElements().add(new LineTo(numX2, numY2));
				path.getElements().add(new LineTo(numX3, numY3));
				path.getElements().add(new LineTo(numX6, numY6));
				path.getElements().add(new LineTo(numX5, numY5));
				path.getElements().add(new LineTo(numX4, numY4));
				path.getElements().add(new LineTo(numX7, numY7));
				path.getElements().add(new LineTo(numX8, numY8));
				path.getElements().add(new LineTo(numX9, numY9));

				followBalltransition.setPath(path);
				followBalltransition.play();

				double[] newline2 = { numX2, numY2, numX3, numY3, numX6, numY6, numX5, numY5, numX4, numY4, numX7,
						numY7, numX8, numY8, numX9, numY9 };

				setCheckLine(newline2);

				break;
			case 3:
				path.getElements().add(new LineTo(numX2, numY2));
				path.getElements().add(new LineTo(numX3, numY3));
				path.getElements().add(new LineTo(numX6, numY6));
				path.getElements().add(new LineTo(numX9, numY9));
				path.getElements().add(new LineTo(numX8, numY8));
				path.getElements().add(new LineTo(numX5, numY5));
				path.getElements().add(new LineTo(numX4, numY4));
				path.getElements().add(new LineTo(numX7, numY7));

				followBalltransition.setPath(path);
				followBalltransition.play();

				double[] newline3 = { numX2, numY2, numX3, numY3, numX6, numY6, numX9, numY9, numX8, numY8, numX5,
						numY5, numX4, numY4, numX7, numY7 };

				setCheckLine(newline3);

				break;
			case 4:
				path.getElements().add(new LineTo(numX2, numY2));
				path.getElements().add(new LineTo(numX3, numY3));
				path.getElements().add(new LineTo(numX6, numY6));
				path.getElements().add(new LineTo(numX9, numY9));
				path.getElements().add(new LineTo(numX8, numY8));
				path.getElements().add(new LineTo(numX7, numY7));
				path.getElements().add(new LineTo(numX4, numY4));
				path.getElements().add(new LineTo(numX5, numY5));

				followBalltransition.setPath(path);
				followBalltransition.play();

				double[] newline4 = { numX2, numY2, numX3, numY3, numX6, numY6, numX9, numY9, numX8, numY8, numX7,
						numY7, numX4, numY4, numX5, numY5 };

				setCheckLine(newline4);

				break;
			case 5:
				path.getElements().add(new LineTo(numX4, numY4));
				path.getElements().add(new LineTo(numX5, numY5));
				path.getElements().add(new LineTo(numX2, numY2));
				path.getElements().add(new LineTo(numX3, numY3));
				path.getElements().add(new LineTo(numX6, numY6));
				path.getElements().add(new LineTo(numX9, numY9));
				path.getElements().add(new LineTo(numX8, numY8));
				path.getElements().add(new LineTo(numX7, numY7));

				followBalltransition.setPath(path);
				followBalltransition.play();

				double[] newline5 = { numX4, numY4, numX5, numY5, numX2, numY2, numX3, numY3, numX6, numY6, numX9,
						numY9, numX8, numY8, numX7, numY7 };

				setCheckLine(newline5);

				break;
			case 6:
				path.getElements().add(new LineTo(numX4, numY4));
				path.getElements().add(new LineTo(numX7, numY7));
				path.getElements().add(new LineTo(numX8, numY8));
				path.getElements().add(new LineTo(numX5, numY5));
				path.getElements().add(new LineTo(numX2, numY2));
				path.getElements().add(new LineTo(numX3, numY3));
				path.getElements().add(new LineTo(numX6, numY6));
				path.getElements().add(new LineTo(numX9, numY9));

				followBalltransition.setPath(path);
				followBalltransition.play();

				double[] newline6 = { numX4, numY4, numX7, numY7, numX8, numY8, numX5, numY5, numX2, numY2, numX3,
						numY3, numX6, numY6, numX9, numY9 };

				setCheckLine(newline6);

				break;
			case 7:
				path.getElements().add(new LineTo(numX4, numY4));
				path.getElements().add(new LineTo(numX7, numY7));
				path.getElements().add(new LineTo(numX8, numY8));
				path.getElements().add(new LineTo(numX9, numY9));
				path.getElements().add(new LineTo(numX6, numY6));
				path.getElements().add(new LineTo(numX3, numY3));
				path.getElements().add(new LineTo(numX2, numY2));
				path.getElements().add(new LineTo(numX5, numY5));

				followBalltransition.setPath(path);
				followBalltransition.play();

				double[] newline7 = { numX4, numY4, numX7, numY7, numX8, numY8, numX9, numY9, numX6, numY6, numX3,
						numY3, numX2, numY2, numX5, numY5 };

				setCheckLine(newline7);

				break;
			case 8:
				path.getElements().add(new LineTo(numX4, numY4));
				path.getElements().add(new LineTo(numX7, numY7));
				path.getElements().add(new LineTo(numX8, numY8));
				path.getElements().add(new LineTo(numX9, numY9));
				path.getElements().add(new LineTo(numX6, numY6));
				path.getElements().add(new LineTo(numX5, numY5));
				path.getElements().add(new LineTo(numX2, numY2));
				path.getElements().add(new LineTo(numX3, numY3));

				followBalltransition.setPath(path);
				followBalltransition.play();

				double[] newline8 = { numX4, numY4, numX7, numY7, numX8, numY8, numX9, numY9, numX6, numY6, numX5,
						numY5, numX2, numY2, numX3, numY3 };

				setCheckLine(newline8);

				break;

			default:

				break;
			}

			break;
		case 2:
			// 시작 위치 3번

			path.getElements().add(new MoveTo(numX3, numY3));

			double ranValueLine2 = Math.random();
			int line2 = (int) (ranValueLine2 * 8) + 1;
			switch (line2) {
			case 1:
				path.getElements().add(new LineTo(numX2, numY2));
				path.getElements().add(new LineTo(numX5, numY5));
				path.getElements().add(new LineTo(numX6, numY6));
				path.getElements().add(new LineTo(numX9, numY9));
				path.getElements().add(new LineTo(numX8, numY8));
				path.getElements().add(new LineTo(numX7, numY7));
				path.getElements().add(new LineTo(numX4, numY4));
				path.getElements().add(new LineTo(numX1, numY1));

				followBalltransition.setPath(path);
				followBalltransition.play();

				double[] newline1 = { numX2, numY2, numX5, numY5, numX6, numY6, numX9, numY9, numX8, numY8, numX7,
						numY7, numX4, numY4, numX1, numY1 };

				setCheckLine(newline1);

				break;
			case 2:
				path.getElements().add(new LineTo(numX2, numY2));
				path.getElements().add(new LineTo(numX1, numY1));
				path.getElements().add(new LineTo(numX4, numY4));
				path.getElements().add(new LineTo(numX7, numY7));
				path.getElements().add(new LineTo(numX8, numY8));
				path.getElements().add(new LineTo(numX5, numY5));
				path.getElements().add(new LineTo(numX6, numY6));
				path.getElements().add(new LineTo(numX9, numY9));

				followBalltransition.setPath(path);
				followBalltransition.play();

				double[] newline2 = { numX2, numY2, numX1, numY1, numX4, numY4, numX7, numY7, numX8, numY8, numX5,
						numY5, numX6, numY6, numX9, numY9 };

				setCheckLine(newline2);

				break;
			case 3:
				path.getElements().add(new LineTo(numX2, numY2));
				path.getElements().add(new LineTo(numX1, numY1));
				path.getElements().add(new LineTo(numX4, numY4));
				path.getElements().add(new LineTo(numX5, numY5));
				path.getElements().add(new LineTo(numX6, numY6));
				path.getElements().add(new LineTo(numX9, numY9));
				path.getElements().add(new LineTo(numX8, numY8));
				path.getElements().add(new LineTo(numX7, numY7));

				followBalltransition.setPath(path);
				followBalltransition.play();

				double[] newline3 = { numX2, numY2, numX1, numY1, numX4, numY4, numX5, numY5, numX6, numY6, numX9,
						numY9, numX8, numY8, numX7, numY7 };

				setCheckLine(newline3);

				break;
			case 4:
				path.getElements().add(new LineTo(numX2, numY2));
				path.getElements().add(new LineTo(numX1, numY1));
				path.getElements().add(new LineTo(numX4, numY4));
				path.getElements().add(new LineTo(numX7, numY7));
				path.getElements().add(new LineTo(numX8, numY8));
				path.getElements().add(new LineTo(numX9, numY9));
				path.getElements().add(new LineTo(numX6, numY6));
				path.getElements().add(new LineTo(numX5, numY5));

				followBalltransition.setPath(path);
				followBalltransition.play();

				double[] newline4 = { numX2, numY2, numX1, numY1, numX4, numY4, numX7, numY7, numX8, numY8, numX9,
						numY9, numX6, numY6, numX5, numY5 };

				setCheckLine(newline4);

				break;
			case 5:
				path.getElements().add(new LineTo(numX6, numY6));
				path.getElements().add(new LineTo(numX5, numY5));
				path.getElements().add(new LineTo(numX2, numY2));
				path.getElements().add(new LineTo(numX1, numY1));
				path.getElements().add(new LineTo(numX4, numY4));
				path.getElements().add(new LineTo(numX7, numY7));
				path.getElements().add(new LineTo(numX8, numY8));
				path.getElements().add(new LineTo(numX9, numY9));

				followBalltransition.setPath(path);
				followBalltransition.play();

				double[] newline5 = { numX6, numY6, numX5, numY5, numX2, numY2, numX1, numY1, numX4, numY4, numX7,
						numY7, numX8, numY8, numX9, numY9 };

				setCheckLine(newline5);

				break;
			case 6:
				path.getElements().add(new LineTo(numX6, numY6));
				path.getElements().add(new LineTo(numX9, numY9));
				path.getElements().add(new LineTo(numX8, numY8));
				path.getElements().add(new LineTo(numX5, numY5));
				path.getElements().add(new LineTo(numX2, numY2));
				path.getElements().add(new LineTo(numX1, numY1));
				path.getElements().add(new LineTo(numX4, numY4));
				path.getElements().add(new LineTo(numX7, numY7));

				followBalltransition.setPath(path);
				followBalltransition.play();

				double[] newline6 = { numX6, numY6, numX9, numY9, numX8, numY8, numX5, numY5, numX2, numY2, numX1,
						numY1, numX4, numY4, numX7, numY7 };

				setCheckLine(newline6);

				break;
			case 7:
				path.getElements().add(new LineTo(numX6, numY6));
				path.getElements().add(new LineTo(numX9, numY9));
				path.getElements().add(new LineTo(numX8, numY8));
				path.getElements().add(new LineTo(numX7, numY7));
				path.getElements().add(new LineTo(numX4, numY4));
				path.getElements().add(new LineTo(numX1, numY1));
				path.getElements().add(new LineTo(numX2, numY2));
				path.getElements().add(new LineTo(numX5, numY5));

				followBalltransition.setPath(path);
				followBalltransition.play();

				double[] newline7 = { numX6, numY6, numX9, numY9, numX8, numY8, numX7, numY7, numX4, numY4, numX1,
						numY1, numX2, numY2, numX5, numY5 };

				setCheckLine(newline7);

				break;
			case 8:
				path.getElements().add(new LineTo(numX6, numY6));
				path.getElements().add(new LineTo(numX9, numY9));
				path.getElements().add(new LineTo(numX8, numY8));
				path.getElements().add(new LineTo(numX7, numY7));
				path.getElements().add(new LineTo(numX4, numY4));
				path.getElements().add(new LineTo(numX5, numY5));
				path.getElements().add(new LineTo(numX2, numY2));
				path.getElements().add(new LineTo(numX1, numY1));

				followBalltransition.setPath(path);
				followBalltransition.play();

				double[] newline8 = { numX6, numY6, numX9, numY9, numX8, numY8, numX7, numY7, numX4, numY4, numX5,
						numY5, numX2, numY2, numX1, numY1 };

				setCheckLine(newline8);

				break;

			default:

				break;
			}

			break;

		case 3:
			// 시작 위치 7번

			path.getElements().add(new MoveTo(numX7, numY7));

			double ranValueLine3 = Math.random();
			int line3 = (int) (ranValueLine3 * 8) + 1;
			switch (line3) {
			case 1:
				path.getElements().add(new LineTo(numX4, numY4));
				path.getElements().add(new LineTo(numX5, numY5));
				path.getElements().add(new LineTo(numX8, numY8));
				path.getElements().add(new LineTo(numX9, numY9));
				path.getElements().add(new LineTo(numX6, numY6));
				path.getElements().add(new LineTo(numX3, numY3));
				path.getElements().add(new LineTo(numX2, numY2));
				path.getElements().add(new LineTo(numX1, numY1));

				followBalltransition.setPath(path);
				followBalltransition.play();

				double[] newline1 = { numX4, numY4, numX5, numY5, numX8, numY8, numX9, numY9, numX6, numY6, numX3,
						numY3, numX2, numY2, numX1, numY1 };

				setCheckLine(newline1);

				break;
			case 2:
				path.getElements().add(new LineTo(numX4, numY4));
				path.getElements().add(new LineTo(numX1, numY1));
				path.getElements().add(new LineTo(numX2, numY2));
				path.getElements().add(new LineTo(numX5, numY5));
				path.getElements().add(new LineTo(numX8, numY8));
				path.getElements().add(new LineTo(numX9, numY9));
				path.getElements().add(new LineTo(numX6, numY6));
				path.getElements().add(new LineTo(numX3, numY3));

				followBalltransition.setPath(path);
				followBalltransition.play();

				double[] newline2 = { numX4, numY4, numX1, numY1, numX2, numY2, numX5, numY5, numX8, numY8, numX9,
						numY9, numX6, numY6, numX3, numY3 };

				setCheckLine(newline2);

				break;
			case 3:
				path.getElements().add(new LineTo(numX4, numY4));
				path.getElements().add(new LineTo(numX1, numY1));
				path.getElements().add(new LineTo(numX2, numY2));
				path.getElements().add(new LineTo(numX3, numY3));
				path.getElements().add(new LineTo(numX6, numY6));
				path.getElements().add(new LineTo(numX9, numY9));
				path.getElements().add(new LineTo(numX8, numY8));
				path.getElements().add(new LineTo(numX5, numY5));

				followBalltransition.setPath(path);
				followBalltransition.play();

				double[] newline3 = { numX4, numY4, numX1, numY1, numX2, numY2, numX3, numY3, numX6, numY6, numX9,
						numY9, numX8, numY8, numX5, numY5 };

				setCheckLine(newline3);

				break;
			case 4:
				path.getElements().add(new LineTo(numX4, numY4));
				path.getElements().add(new LineTo(numX1, numY1));
				path.getElements().add(new LineTo(numX2, numY2));
				path.getElements().add(new LineTo(numX3, numY3));
				path.getElements().add(new LineTo(numX6, numY6));
				path.getElements().add(new LineTo(numX5, numY5));
				path.getElements().add(new LineTo(numX8, numY8));
				path.getElements().add(new LineTo(numX9, numY9));

				followBalltransition.setPath(path);
				followBalltransition.play();

				double[] newline4 = { numX4, numY4, numX1, numY1, numX2, numY2, numX3, numY3, numX6, numY6, numX5,
						numY5, numX8, numY8, numX9, numY9 };

				setCheckLine(newline4);

				break;
			case 5:
				path.getElements().add(new LineTo(numX8, numY8));
				path.getElements().add(new LineTo(numX5, numY5));
				path.getElements().add(new LineTo(numX4, numY4));
				path.getElements().add(new LineTo(numX1, numY1));
				path.getElements().add(new LineTo(numX2, numY2));
				path.getElements().add(new LineTo(numX3, numY3));
				path.getElements().add(new LineTo(numX6, numY6));
				path.getElements().add(new LineTo(numX9, numY9));

				followBalltransition.setPath(path);
				followBalltransition.play();

				double[] newline5 = { numX8, numY8, numX5, numY5, numX4, numY4, numX1, numY1, numX2, numY2, numX3,
						numY3, numX6, numY6, numX9, numY9 };

				setCheckLine(newline5);

				break;
			case 6:
				path.getElements().add(new LineTo(numX8, numY8));
				path.getElements().add(new LineTo(numX9, numY9));
				path.getElements().add(new LineTo(numX6, numY6));
				path.getElements().add(new LineTo(numX5, numY5));
				path.getElements().add(new LineTo(numX4, numY4));
				path.getElements().add(new LineTo(numX1, numY1));
				path.getElements().add(new LineTo(numX2, numY2));
				path.getElements().add(new LineTo(numX3, numY3));

				followBalltransition.setPath(path);
				followBalltransition.play();

				double[] newline6 = { numX8, numY8, numX9, numY9, numX6, numY6, numX5, numY5, numX4, numY4, numX1,
						numY1, numX2, numY2, numX3, numY3 };

				setCheckLine(newline6);

				break;
			case 7:
				path.getElements().add(new LineTo(numX8, numY8));
				path.getElements().add(new LineTo(numX9, numY9));
				path.getElements().add(new LineTo(numX6, numY6));
				path.getElements().add(new LineTo(numX3, numY3));
				path.getElements().add(new LineTo(numX2, numY2));
				path.getElements().add(new LineTo(numX1, numY1));
				path.getElements().add(new LineTo(numX4, numY4));
				path.getElements().add(new LineTo(numX5, numY5));

				followBalltransition.setPath(path);
				followBalltransition.play();

				double[] newline7 = { numX8, numY8, numX9, numY9, numX6, numY6, numX3, numY3, numX2, numY2, numX1,
						numY1, numX4, numY4, numX5, numY5 };

				setCheckLine(newline7);

				break;
			case 8:
				path.getElements().add(new LineTo(numX8, numY8));
				path.getElements().add(new LineTo(numX9, numY9));
				path.getElements().add(new LineTo(numX6, numY6));
				path.getElements().add(new LineTo(numX3, numY3));
				path.getElements().add(new LineTo(numX2, numY2));
				path.getElements().add(new LineTo(numX5, numY5));
				path.getElements().add(new LineTo(numX4, numY4));
				path.getElements().add(new LineTo(numX1, numY1));

				followBalltransition.setPath(path);
				followBalltransition.play();

				double[] newline8 = { numX8, numY8, numX9, numY9, numX6, numY6, numX3, numY3, numX2, numY2, numX5,
						numY5, numX4, numY4, numX1, numY1 };

				setCheckLine(newline8);

				break;

			default:

				break;
			}

			break;
		case 4:
			// 시작위치 9번

			path.getElements().add(new MoveTo(numX9, numY9));

			double ranValueLine4 = Math.random();
			int line4 = (int) (ranValueLine4 * 8) + 1;
			switch (line4) {
			case 1:
				path.getElements().add(new LineTo(numX6, numY6));
				path.getElements().add(new LineTo(numX5, numY5));
				path.getElements().add(new LineTo(numX8, numY8));
				path.getElements().add(new LineTo(numX7, numY7));
				path.getElements().add(new LineTo(numX4, numY4));
				path.getElements().add(new LineTo(numX1, numY1));
				path.getElements().add(new LineTo(numX2, numY2));
				path.getElements().add(new LineTo(numX3, numY3));

				followBalltransition.setPath(path);
				followBalltransition.play();

				double[] newline1 = { numX6, numY6, numX5, numY5, numX8, numY8, numX7, numY7, numX4, numY4, numX1,
						numY1, numX2, numY2, numX3, numY3 };

				setCheckLine(newline1);

				break;
			case 2:
				path.getElements().add(new LineTo(numX6, numY6));
				path.getElements().add(new LineTo(numX3, numY3));
				path.getElements().add(new LineTo(numX2, numY2));
				path.getElements().add(new LineTo(numX1, numY1));
				path.getElements().add(new LineTo(numX4, numY4));
				path.getElements().add(new LineTo(numX5, numY5));
				path.getElements().add(new LineTo(numX8, numY8));
				path.getElements().add(new LineTo(numX7, numY7));

				followBalltransition.setPath(path);
				followBalltransition.play();

				double[] newline2 = { numX6, numY6, numX3, numY3, numX2, numY2, numX1, numY1, numX4, numY4, numX5,
						numY5, numX8, numY8, numX7, numY7 };

				setCheckLine(newline2);

				break;
			case 3:
				path.getElements().add(new LineTo(numX6, numY6));
				path.getElements().add(new LineTo(numX3, numY3));
				path.getElements().add(new LineTo(numX2, numY2));
				path.getElements().add(new LineTo(numX1, numY1));
				path.getElements().add(new LineTo(numX4, numY4));
				path.getElements().add(new LineTo(numX7, numY7));
				path.getElements().add(new LineTo(numX8, numY8));
				path.getElements().add(new LineTo(numX5, numY5));

				followBalltransition.setPath(path);
				followBalltransition.play();

				double[] newline3 = { numX6, numY6, numX3, numY3, numX2, numY2, numX1, numY1, numX4, numY4, numX7,
						numY7, numX8, numY8, numX5, numY5 };

				setCheckLine(newline3);

				break;
			case 4:
				path.getElements().add(new LineTo(numX6, numY6));
				path.getElements().add(new LineTo(numX3, numY3));
				path.getElements().add(new LineTo(numX2, numY2));
				path.getElements().add(new LineTo(numX5, numY5));
				path.getElements().add(new LineTo(numX8, numY8));
				path.getElements().add(new LineTo(numX7, numY7));
				path.getElements().add(new LineTo(numX4, numY4));
				path.getElements().add(new LineTo(numX1, numY1));

				followBalltransition.setPath(path);
				followBalltransition.play();

				double[] newline4 = { numX6, numY6, numX3, numY3, numX2, numY2, numX5, numY5, numX8, numY8, numX7,
						numY7, numX4, numY4, numX1, numY1 };

				setCheckLine(newline4);

				break;
			case 5:
				path.getElements().add(new LineTo(numX8, numY8));
				path.getElements().add(new LineTo(numX5, numY5));
				path.getElements().add(new LineTo(numX6, numY6));
				path.getElements().add(new LineTo(numX3, numY3));
				path.getElements().add(new LineTo(numX2, numY2));
				path.getElements().add(new LineTo(numX1, numY1));
				path.getElements().add(new LineTo(numX4, numY4));
				path.getElements().add(new LineTo(numX7, numY7));

				followBalltransition.setPath(path);
				followBalltransition.play();

				double[] newline5 = { numX8, numY8, numX5, numY5, numX6, numY6, numX3, numY3, numX2, numY2, numX1,
						numY1, numX4, numY4, numX7, numY7 };

				setCheckLine(newline5);

				break;
			case 6:
				path.getElements().add(new LineTo(numX8, numY8));
				path.getElements().add(new LineTo(numX7, numY7));
				path.getElements().add(new LineTo(numX4, numY4));
				path.getElements().add(new LineTo(numX1, numY1));
				path.getElements().add(new LineTo(numX2, numY2));
				path.getElements().add(new LineTo(numX5, numY5));
				path.getElements().add(new LineTo(numX6, numY6));
				path.getElements().add(new LineTo(numX3, numY3));

				followBalltransition.setPath(path);
				followBalltransition.play();

				double[] newline6 = { numX8, numY8, numX7, numY7, numX4, numY4, numX1, numY1, numX2, numY2, numX5,
						numY5, numX6, numY6, numX3, numY3 };

				setCheckLine(newline6);

				break;
			case 7:
				path.getElements().add(new LineTo(numX8, numY8));
				path.getElements().add(new LineTo(numX7, numY7));
				path.getElements().add(new LineTo(numX4, numY4));
				path.getElements().add(new LineTo(numX1, numY1));
				path.getElements().add(new LineTo(numX2, numY2));
				path.getElements().add(new LineTo(numX3, numY3));
				path.getElements().add(new LineTo(numX6, numY6));
				path.getElements().add(new LineTo(numX5, numY5));

				followBalltransition.setPath(path);
				followBalltransition.play();

				double[] newline7 = { numX8, numY8, numX7, numY7, numX4, numY4, numX1, numY1, numX2, numY2, numX3,
						numY3, numX6, numY6, numX5, numY5 };

				setCheckLine(newline7);

				break;
			case 8:
				path.getElements().add(new LineTo(numX8, numY8));
				path.getElements().add(new LineTo(numX7, numY7));
				path.getElements().add(new LineTo(numX4, numY4));
				path.getElements().add(new LineTo(numX5, numY5));
				path.getElements().add(new LineTo(numX6, numY6));
				path.getElements().add(new LineTo(numX3, numY3));
				path.getElements().add(new LineTo(numX2, numY2));
				path.getElements().add(new LineTo(numX1, numY1));

				followBalltransition.setPath(path);
				followBalltransition.play();

				double[] newline8 = { numX8, numY8, numX7, numY7, numX4, numY4, numX5, numY5, numX6, numY6, numX3,
						numY3, numX2, numY2, numX1, numY1 };

				setCheckLine(newline8);

				break;

			default:
				break;
			}
			break;
		}

		followBalltransition.setOnFinished((e) -> {
			// 게임 오버 검사 (follow가 한번 돌고 난 후 게임 종료하게 만듬)
			try {
				gameOver();
			} catch (UnsupportedAudioFileException | IOException | LineUnavailableException | URISyntaxException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			if (value == 1) {
				Path path1 = new Path();
				MoveTo moveTo1 = new MoveTo(0.0, 0.0);
				LineTo lineTo1 = new LineTo(numX1, numY1);
				this.catchCircleX = numX1;
				this.catchCircleY = numY1;
				PathTransition pathTransition = new PathTransition();
				pathTransition.setDuration(Duration.millis(0.1));
				pathTransition.setNode(catchCircle);
				path1.getElements().add(moveTo1);
				path1.getElements().add(lineTo1);
				pathTransition.setPath(path1);
				pathTransition.play();
			} else if (value == 2) {
				Path path1 = new Path();
				MoveTo moveTo1 = new MoveTo(0.0, 0.0);
				LineTo lineTo1 = new LineTo(numX3, numY3);
				this.catchCircleX = numX3;
				this.catchCircleY = numY3;
				PathTransition pathTransition = new PathTransition();
				pathTransition.setDuration(Duration.millis(0.1));
				pathTransition.setNode(catchCircle);
				path1.getElements().add(moveTo1);
				path1.getElements().add(lineTo1);
				pathTransition.setPath(path1);
				pathTransition.play();
			} else if (value == 3) {
				Path path1 = new Path();
				MoveTo moveTo1 = new MoveTo(0.0, 0.0);
				LineTo lineTo1 = new LineTo(numX7, numY7);
				this.catchCircleX = numX7;
				this.catchCircleY = numY7;
				PathTransition pathTransition = new PathTransition();
				pathTransition.setDuration(Duration.millis(0.1));
				pathTransition.setNode(catchCircle);
				path1.getElements().add(moveTo1);
				path1.getElements().add(lineTo1);
				pathTransition.setPath(path1);
				pathTransition.play();
			} else {
				Path path1 = new Path();
				MoveTo moveTo1 = new MoveTo(0.0, 0.0);
				LineTo lineTo1 = new LineTo(numX9, numY9);
				this.catchCircleX = numX9;
				this.catchCircleY = numY9;
				PathTransition pathTransition = new PathTransition();
				pathTransition.setDuration(Duration.millis(0.1));
				pathTransition.setNode(catchCircle);
				path1.getElements().add(moveTo1);
				path1.getElements().add(lineTo1);
				pathTransition.setPath(path1);
				pathTransition.play();
			}

			catchCircle.setVisible(true);
			flag = true;
		});

	}

	public class Clock extends Pane {

		private Timeline animation;
		private int timeTmp = 60;
		private String S = "";

		public Clock() {
			animation = new Timeline(new KeyFrame(Duration.seconds(1), e -> timeLabel()));
			animation.setCycleCount(Timeline.INDEFINITE);
			animation.play();
		}

		private void timeLabel() {
			if (timeTmp > 0)
				timeTmp--;
			timeTime = timeTmp;
			S = timeTmp + "";
			timeLabel.setText(S);
		}

		public int getTime() {
			return timeTmp;
		}
	}

}