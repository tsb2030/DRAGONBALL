package eye.game.catchBall;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import eye.main.Main;
import javafx.animation.Animation.Status;
import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class CatchballGameController implements Initializable {

	@FXML
	private AnchorPane bigPanne; // 전체 AnchorPane

	@FXML
	private AnchorPane GamePane; // 공이 움직이는 AnchoPane

	@FXML
	ImageView BackBtn, PauseBtn;

	int pauseSwitch = 0;

	// 현재 Stage를 저장하기 위한 변수
	public static Stage currentStage;

	@FXML
	private Text timeLabel; // 시간을 표시하기 위한 Text
	
    @FXML
    private Label ScoreLabel;	//
    
    @FXML
    private Label judgeYourBehavior;

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

	//catchBall을 움직일수 있게 할지 말지를 결정해주는 변수
	private boolean flag = false;
	@FXML
	private Circle followCircle;

	@FXML
	private Circle catchCircle;

	double speedValue = IntroducePageController.gameSpeed;

	private double catchCircleX = 50.0;
	private double catchCircleY = 50.0;

	@FXML
	private Button startButton; // 재실행 버튼

	// 실행 경로의 x좌표와 y좌표를 저장하기 위한 double형 배열
	private double[] checkLine = new double[16];

	private boolean correct = true;

	// 게임 전체 틀린 횟수
	private int falseCount = 0;

	// 한 판당 점수
	private int smallScore = 0;

	// 전체 게임의 총 점수
	public static int bigScore = 0;

	// lineIndex
	private int lineIndex = -1;

	// Timer 객체
	private Clock timer;

	private Line line;

	// 라인 지정을 위한 인덱스
	private int lineRouteIndex = 0;

	// 8개의 라인들을 저장하기 위한 배열
	private Line[] lines = new Line[8];

	private PathTransition catchBalltransition;

	private PathTransition followBalltransition;

	private boolean justOne = false;

	// PathTransition변수
	PathTransition transition;

	// 현재까지 진행된 시간값을 전달하기 위한 변수
	public static int timeTime;


	// 처음 창이 실행되었을 경우
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	System.out.println("java version: "+System.getProperty("java.version"));
		System.out.println("javafx.version: " + System.getProperty("javafx.version"));
		timer = new Clock();

		followCircle.setVisible(false);
		catchCircle.setVisible(false);
		followCircleStart();

		// backButton이 실행되었을 경우
		BackBtn.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				try {
					Parent SelectSpeedPage = FXMLLoader.load(getClass().getResource("IntroducePage.fxml"));
					Scene scene = new Scene(SelectSpeedPage);
					scene.getStylesheets()
							.add(getClass().getResource("../../main/controller/application.css").toExternalForm());
					Stage primaryStage = (Stage) BackBtn.getScene().getWindow();
					primaryStage.setScene(scene);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		// pauseButton이 실행되었을 경우
		PauseBtn.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				// pauseEvent Start!
				timer.animation.pause();
				bigPanne.setOpacity(0.45);
				if (followBalltransition.getStatus() == Status.RUNNING)
					followBalltransition.pause();
				if (catchBalltransition.getStatus() == Status.RUNNING)
					catchBalltransition.pause();
			}
		});
		
		startButton.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				timer.animation.play();
				bigPanne.setOpacity(1.0);
				if (followBalltransition.getStatus() == Status.PAUSED)
					followBalltransition.play();
				if (catchBalltransition.getStatus() == Status.PAUSED)
					catchBalltransition.play();
			}
		});

	}

	// 게임이 종료되었을 때 호출되는 이벤트
	public void gameOver()
			throws UnsupportedAudioFileException, IOException, LineUnavailableException, URISyntaxException {
		currentStage = (Stage) PauseBtn.getScene().getWindow();
		if (justOne == false) { // 종료 메소드가 한번 실행 되었다면 두번 반복되지 않기 위해 boolean값을 이용하였다.
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
					Scene EndGamePopupScene = new Scene(anotherPage);
					EndGamePopupScene.getStylesheets()
							.add(getClass().getResource("../../main/controller/application.css").toExternalForm());
					Stage stage = new Stage();
					stage.setScene(EndGamePopupScene);
					stage.show();
				} catch (IOException e) {
				}
			}

		}

	}

	// 입력받은 라인배열로 checkLine배열의 값들을 채운다. 
	public void setCheckLine(double[] line) {
		for (int i = 0; i < line.length; i++) {
			this.checkLine[i] = line[i];
		}
	}

	// pause되어있던 상태를 원상태로 되돌린다.
//	@FXML
//	void startGame(MouseEvent event) {
//		timer.animation.play();
//		bigPanne.setOpacity(1.0);
//		if (followBalltransition.getStatus() == Status.PAUSED)
//			followBalltransition.play();
//		if (catchBalltransition.getStatus() == Status.PAUSED)
//			catchBalltransition.play();
//	}

//	@FXML
//	void pauseEvent(ActionEvent event) {
//		// pauseEvent Start!
//		timer.animation.pause();
//		bigPanne.setOpacity(0.45);
//		if (followBalltransition.getStatus() == Status.RUNNING)
//			followBalltransition.pause();
//		if (catchBalltransition.getStatus() == Status.RUNNING)
//			catchBalltransition.pause();
//	}

	// 배열을 초기화 시킨다.(자바에선 사실상 필요없다.)
	public double[] initArray(double[] line) {
		for (int i = 0; i < line.length; i++) {
			line[i] = 0;
		}
		return line;
	}

	//catchBall이 간 경로를 그려주는 메소드
	public void drawLine(double startX, double startY, double endX, double endY) {
		line = new Line(startX, startY, endX, endY);
		line.setStroke(Color.LIGHTGREEN);
		line.getStrokeDashArray().addAll(20d, 10d);
		line.setStrokeWidth(5);
		addLines(line);
		GamePane.getChildren().addAll(line);
	}

	// 그린 경로를 배열에 하나씩 더해주는 과정
	public void addLines(Line line) {
		lines[lineRouteIndex] = line;
		lineRouteIndex++;
	}

	// 한 판이 종료되면 그렸던 경로를 안보이게 해준다.
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
	
	//catchBall을 움직이게 하는 키 이벤트 메소드
	@FXML
	void keyEventHandler(KeyEvent event) throws InterruptedException, UnsupportedAudioFileException, IOException,
			LineUnavailableException, URISyntaxException {
		String yourBehavior = "";
		if (flag == true) {
			catchBalltransition = new PathTransition();
			catchBalltransition.setNode(catchCircle);
			catchBalltransition.setDuration(Duration.seconds(0.0125));

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
					
					if (checkLine[++lineIndex] == catchCircleX && checkLine[++lineIndex] == catchCircleY) {
						smallScore++;
						System.out.println("smallScore : " + smallScore);
						drawLine(catchCircleX, catchCircleY + 200, catchCircleX, catchCircleY);
						System.out.println("마자춤");
						ScoreLabel.setText(String.valueOf(bigScore + smallScore));
					} else { 
						correct = false;
						System.out.println("틀렸춤");
						falseCount++;
					}
					
					if (smallScore == 8 || correct == false) {
						switch (lineIndex) {
						case 0:
						case 1:
						case 2:
						case 3:
							yourBehavior = "Animal";
							break;
						case 4:
						case 5:
						case 6:
						case 7:
							yourBehavior = "Australopithecus";
							break;

						case 8:
						case 9:
						case 10:
						case 11:
							yourBehavior = "Homo sapiens";
							break;

						case 12:
						case 13:
						case 14:
						case 15:
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
			
				if (checkLine[++lineIndex] == catchCircleX && checkLine[++lineIndex] == catchCircleY) {
					smallScore++;
					drawLine(catchCircleX, catchCircleY - 200, catchCircleX, catchCircleY);
					ScoreLabel.setText(String.valueOf(bigScore + smallScore));
				} else {
					correct = false;
					falseCount++;
				}
				
				
				if (smallScore == 8 || correct == false) {
					switch (lineIndex) {
					case 0:
					case 1:
					case 2:
					case 3:
						yourBehavior = "Animal";
						break;
					case 4:
					case 5:
					case 6:
					case 7:
						yourBehavior = "Australopithecus";
						break;

					case 8:
					case 9:
					case 10:
					case 11:
						yourBehavior = "Homo sapiens";
						break;

					case 12:
					case 13:
					case 14:
					case 15:
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
					gameOver();
					
				}

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
			
				if (checkLine[++lineIndex] == catchCircleX && checkLine[++lineIndex] == catchCircleY) {
					smallScore++;
					drawLine(catchCircleX + 450, catchCircleY, catchCircleX, catchCircleY);
					ScoreLabel.setText(String.valueOf(bigScore + smallScore));
				} else {
					correct = false;
					falseCount++;
				}
				
				if (smallScore == 8 || correct == false) {
					switch (lineIndex) {
					case 0:
					case 1:
					case 2:
					case 3:
						yourBehavior = "Animal";
						break;
					case 4:
					case 5:
					case 6:
					case 7:
						yourBehavior = "Australopithecus";
						break;

					case 8:
					case 9:
					case 10:
					case 11:
						yourBehavior = "Homo sapiens";
						break;

					case 12:
					case 13:
					case 14:
					case 15:
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
					
					gameOver();
				}

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
				
				if (checkLine[++lineIndex] == catchCircleX && checkLine[++lineIndex] == catchCircleY) {
					smallScore++;
					drawLine(catchCircleX - 450, catchCircleY, catchCircleX, catchCircleY);
					ScoreLabel.setText(String.valueOf(bigScore + smallScore));
				} else {
					correct = false;
					falseCount++;
				}
			
				
				if (smallScore == 8 || correct == false) {
					switch (lineIndex) {
					case 0:
					case 1:
					case 2:
					case 3:
						yourBehavior = "Animal";
						break;
					case 4:
					case 5:
					case 6:
					case 7:
						yourBehavior = "Australopithecus";
						break;

					case 8:
					case 9:
					case 10:
					case 11:
						yourBehavior = "Homo sapiens";
						break;

					case 12:
					case 13:
					case 14:
					case 15:
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
					gameOver();
				}
				break;

			default:
				break;
			}
		}

	}

	public void followCircleStart() {

		catchCircle.setVisible(false);
		flag = false;

		followCircle.setVisible(true);
		followBalltransition = new PathTransition();
		followBalltransition.setNode(followCircle);
		followBalltransition.setDuration(Duration.seconds(speedValue / 2 + 3));

		Path path = new Path();
		double ranValue = Math.random();
		int value = (int) (ranValue * 4) + 1;
		switch (value) {
		case 1:

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
			try {
				gameOver();
			} catch (UnsupportedAudioFileException | IOException | LineUnavailableException | URISyntaxException e1) {
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
			S = "Time: " + timeTmp + "";
			timeLabel.setText(S);
		}

		public int getTime() {
			return timeTmp;
		}
	}

}