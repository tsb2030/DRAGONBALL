package eye.game.follow;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import eye.Music;
import eye.db.AchievementDB;
import eye.db.dbconn;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

//숫자게임페이지에 달려있는 컨트롤러
public class otfGameController implements Initializable {

	public static int otfGame=0;
	
	dbconn db = new dbconn();
	AchievementDB aDB = new AchievementDB();

	public static Stage currentStage;

	@FXML
	private ImageView btnBefore, pauseBtn, reStartBtn;
	private JFXButton btnarr[] = new JFXButton[25];

	@FXML
	private Pane mainPanel;

	public static int bul = 3;

	// 도전과제 체크변수
	public static boolean numHuman = false;
	public static boolean numMistake = false;
	public static boolean numPerfect = false;
	public static boolean bestRate = false;
	public static boolean luckyS = false;

	public int checkPerfect = 0;
	public int checkLucky = 0;

	// arr1은 1~25 랜덤수, arr2는 26~50까지의 랜덤수를 가지기 위해 정의
	@FXML
	private Text timer;
	public static String result;
	private int arr1[] = new int[25];
	private int arr2[] = new int[25];

	// 글자의 위치 색깔 크기를 랜덤으로 만들기위한 배열
	private int posRand[] = new int[25];
	private int colorRand[] = new int[25];
	private int sizeRand[] = new int[25];

	Random rd = new Random();
	// 현재 어떤 수를 눌러야 하는지 판별하기위해서 사용되는 수
	private int number = 1;

	// 타임라인을 사용하기위한 정의
	private Boolean isStart = false; // 시작인지 판단할 필드.
	private Timeline timeLine;
	private DoubleProperty timeSeconds = new SimpleDoubleProperty();
	private Duration time = Duration.ZERO;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		buttonSetting();
		for (int i = 0; i < 25; i++) {

			mainPanel.getChildren().add(btnarr[i]);
		}
		timeLine = new Timeline(new KeyFrame(Duration.seconds(1), e -> timeCheck())); // timeLine 객체 초기화
		timeLine.setCycleCount(Timeline.INDEFINITE);
		timeLine.play();

		btnBefore.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				// TODO Auto-generated method stub
				try {
					Music effectMusic = new Music("generalMouseClickedEffect", false, 2);
					effectMusic.start();
					Parent gameInfo = FXMLLoader.load(getClass().getResource("gameIntroNum.fxml")); // 불러올 페이지 지정
					Scene scene = new Scene(gameInfo);
					scene.getStylesheets().add(getClass().getResource("intro.css").toExternalForm()); // css 지정
					Stage primaryStage = (Stage) btnBefore.getScene().getWindow(); // 현재 윈도우 가져오기
					primaryStage.setResizable(false);
					primaryStage.setScene(scene);
					primaryStage.setTitle("순서대로 따라가기 - 1 to 50");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		pauseBtn.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				Music effectMusic = new Music("generalMouseClickedEffect", false, 2);
				effectMusic.start();
				// TODO Auto-generated method stub
				for (int i = 0; i < 25; i++) {
					btnarr[i].setDisable(true);
				}
				timeLine.stop();
			}
		});

		reStartBtn.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				Music effectMusic = new Music("generalMouseClickedEffect", false, 2);
				effectMusic.start();
				// TODO Auto-generated method stub
				for (int i = 0; i < 25; i++) {
					btnarr[i].setDisable(false);
				}
				timeLine.play();
			}
		});

		start();
	}

	//시간검사
			public void timeCheck() {
				String str = timer.getText();
				System.out.println("시간: "+str);
				double checkTime = Double.parseDouble(str);
				if(checkTime>60) {
					otfGame=1;
					timeLine.stop();
					for (int i = 0; i < 25; i++) {
						btnarr[i].setDisable(true);
					}
					if (checkLucky == 3) {
						luckyS = true;
						aDB.ach();
					}
					String timeStr = timer.getText();
					double check = Double.parseDouble(timeStr);
					if (check <= 15) {
						numHuman = true;
						aDB.ach();
					}
					if (checkPerfect == 0) {
						numPerfect = true;
						aDB.ach();
					}
					double val = Double.parseDouble(timeStr);
					System.out.println("val = " + val);
					SimpleDateFormat sDateForm = new SimpleDateFormat("yyyy/MM/dd");
					Date currentTime = new Date();
					String cTime = sDateForm.format(currentTime);
					try {
						db.insertTimes("follow", cTime);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					currentStage = (Stage) timer.getScene().getWindow();
					result = timer.getText();
					FXMLLoader endGamePopup = new FXMLLoader(
							getClass().getResource("gameSuccess.fxml"));
					try {
						AnchorPane anotherPage = (AnchorPane) endGamePopup.load();
						Scene endGamePopupScene = new Scene(anotherPage);
						endGamePopupScene.getStylesheets().add(getClass()
								.getResource("/eye/main/controller/application.css").toExternalForm());
						Stage stage = new Stage();
						stage.setResizable(false);
						stage.setScene(endGamePopupScene);
						stage.show();
					} catch (IOException e) {
					}
				}
			}
			
	// 버튼을 정의와 이벤트핸들러를 달아주고 1~25까지의 랜덤수를 각각의 버튼에 달아준다.
	public void buttonSetting() {
		System.out.println("버튼세팅");
		randomSet();
		int ly = 60;
		// 버튼정의
		for (int i = 0; i < 25; i++) {
			btnarr[i] = new JFXButton();
		}
		int tmp = 0;
		for (int i = 0; i < 5; i++) {
			int lx = 100;
			for (int j = 0; j < 5; j++) {
				// 버튼에 이벤트를 달아주는 부분
				btnarr[tmp].setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						/*
						 * 버튼의 텍스트를 가져와 그 숫자가 number와 비교해서 같으면 arr2배열에 있는 수를 차례대로 넣어주고 number를 1 더해준다.
						 * 만약 number가 25보다 클 시에는 number값만 1올려주고 텍스트에 ""을 넣어준다.
						 */
						Button btn = new Button();
						try {
							btn = (Button) event.getSource();
							String btnstr = btn.getText();
							if (Integer.parseInt(btnstr) == 7)
								checkLucky++;
							if (Integer.parseInt(btnstr) == number) {
								if (number <= 25) {
									btn.setText(arr2[number - 1] + "");
								} else {
									btn.setText("");
								}
								int prand = rd.nextInt(4) + 1;
								int crand = rd.nextInt(5) + 1;
								int srand = rd.nextInt(3) + 1;

								if (prand == 1) {
									btn.setAlignment(Pos.TOP_LEFT);
								} else if (prand == 2) {
									btn.setAlignment(Pos.TOP_RIGHT);
								} else if (prand == 3) {
									btn.setAlignment(Pos.BOTTOM_RIGHT);
								} else if (prand == 4) {
									btn.setAlignment(Pos.BOTTOM_LEFT);
								}
								if (crand == 1) {
									btn.setStyle("-fx-text-fill: black; -fx-font-size: 45.0px;");
								} else if (crand == 2) {
									btn.setStyle("-fx-text-fill: lightblue; -fx-font-size: 25.0px;");
								} else if (crand == 3) {
									btn.setStyle("-fx-text-fill: red; -fx-font-size: 35.0px;");
								} else if (crand == 4) {
									btn.setStyle("-fx-text-fill: pink; -fx-font-size: 35.0px;");
								} else if (crand == 5) {
									btn.setStyle("-fx-text-fill: green; -fx-font-size: 45.0px;");
								}
								number++;
								System.out.println(number);
								// number가 51이되면 게임이 끝난 것이므로 타임라인을 멈추고 숫자버튼이 눌리지않게 처리한다.
								if (number == 51) {
									timeLine.stop();
									for (int i = 0; i < 25; i++) {
										btnarr[i].setDisable(true);
									}
									if (checkLucky == 3) {
										luckyS = true;
										aDB.ach();
									}
									String timeStr = timer.getText();
									double check = Double.parseDouble(timeStr);
									if (check <= 15) {
										numHuman = true;
										aDB.ach();
									}
									if (checkPerfect == 0) {
										numPerfect = true;
										aDB.ach();
									}
									double val = Double.parseDouble(timeStr);
									System.out.println("val = " + val);
									SimpleDateFormat sDateForm = new SimpleDateFormat("yyyy/MM/dd");
									Date currentTime = new Date();
									String cTime = sDateForm.format(currentTime);
									db.insertKorGame(cTime, "numGame", val);
									db.insertTimes("follow", cTime);
									currentStage = (Stage) timer.getScene().getWindow();
									result = timer.getText();
									FXMLLoader endGamePopup = new FXMLLoader(
											getClass().getResource("gameSuccess.fxml"));
									try {
										AnchorPane anotherPage = (AnchorPane) endGamePopup.load();
										Scene endGamePopupScene = new Scene(anotherPage);
										endGamePopupScene.getStylesheets().add(getClass()
												.getResource("/eye/main/controller/application.css").toExternalForm());
										Stage stage = new Stage();
										stage.setResizable(false);
										stage.setScene(endGamePopupScene);
										stage.show();
									} catch (IOException e) {
									}
								}
							} else {
								checkLucky = 0;
								checkPerfect++;
								numMistake = true;
							}
						} catch (Exception e) {
							// TODO: handle exception
						}
					}
				});
				// 각각 버튼의 크기와 위치를 설정해주기 위한 부분
				if (posRand[tmp] == 1) {
					btnarr[tmp].setAlignment(Pos.TOP_LEFT);
				} else if (posRand[tmp] == 2) {
					btnarr[tmp].setAlignment(Pos.TOP_RIGHT);
				} else if (posRand[tmp] == 3) {
					btnarr[tmp].setAlignment(Pos.BOTTOM_RIGHT);
				} else if (posRand[tmp] == 4) {
					btnarr[tmp].setAlignment(Pos.BOTTOM_LEFT);
				}
				if (colorRand[tmp] == 1) {
					btnarr[tmp].setStyle("-fx-text-fill: black; -fx-font-size: 45.0px;");
				} else if (colorRand[tmp] == 2) {
					btnarr[tmp].setStyle("-fx-text-fill: lightblue; -fx-font-size: 45.0px;");
				} else if (colorRand[tmp] == 3) {
					btnarr[tmp].setStyle("-fx-text-fill: red; -fx-font-size: 25.0px;");
				} else if (colorRand[tmp] == 4) {
					btnarr[tmp].setStyle("-fx-text-fill: pink; -fx-font-size: 35.0px;");
				} else if (colorRand[tmp] == 5) {
					btnarr[tmp].setStyle("-fx-text-fill: green; -fx-font-size: 25.0px;");
				}
				btnarr[tmp].setPrefSize(120, 120);
				btnarr[tmp].setLayoutX(lx);
				btnarr[tmp++].setLayoutY(ly);
				lx += 240;
			}
			ly += 120;
		}
	}

	// 게임 시작시에 버튼의 텍스트에 1~25까지의 랜덤수를 넣어주기 위한 부분
	public void textSetting(int[] intarr) {
		int tmp = 0;
		for (int i = 0; i < 25; i++) {
			btnarr[tmp].setText(intarr[tmp] + "");
			tmp++;
		}
	}

	public void randomSet() {
		for (int i = 0; i < 25; i++) {
			posRand[i] = rd.nextInt(4) + 1;
			colorRand[i] = rd.nextInt(5) + 1;
			sizeRand[i] = rd.nextInt(3) + 1;
		}
		System.out.println(Arrays.toString(posRand));
		System.out.println(Arrays.toString(colorRand));
		System.out.println(Arrays.toString(sizeRand));
	}

	// arr1,arr2에 각각 1~25,26~50까지의 수를 넣어주는부분
	public void random() {
		for (int i = 0; i < 25;) {
			Boolean tp = true;
			int r = rd.nextInt(25) + 1;
			for (int j = 0; j < 25; j++) {
				if (arr1[j] == r)
					tp = false;
			}
			if (tp) {
				arr1[i] = r;
				i++;
			}
		}
		for (int i = 0; i < 25;) {
			Boolean tp = true;
			int r = rd.nextInt(25) + 26;
			for (int j = 0; j < 25; j++) {
				if (arr2[j] == r)
					tp = false;
			}
			if (tp) {
				arr2[i] = r;
				i++;
			}
		}
	}

	// 게임 시작시 실행되는 메소드
	public void start() {
		random();
		textSetting(arr1);
		timeLine.stop(); // 새로 시간을 측정하려면 timeLine이 초기화되야 하므로 stop()
		time = Duration.ZERO; // time의 값도 새로 측정 할 때마다 0이되어야 함.
		timer.textProperty().bind(timeSeconds.asString()); // timeCheck 에 timeSeconds 값 대입
		isStart = true; // newButton을 클릭했으므로 isStart 값 true로
		// 스탑워치를 시작하는부분
		if (isStart == true) {

			if (timeLine == null) {
				// 딱히 할 거 없음.
			} else {
				timeLine = new Timeline(new KeyFrame(Duration.millis(10), // 0.01초 단위로 체크
						new EventHandler<ActionEvent>() {
							@Override
							public void handle(ActionEvent t) {
								Duration duration = ((KeyFrame) t.getSource()).getTime();
								time = time.add(duration);
								timeSeconds.set(time.toSeconds());
							}
						}));
				timeLine.setCycleCount(Timeline.INDEFINITE);
				timeLine.play();
			}

		}
	}

	// 팝업창 부분
	// 게임 종료시 나오는 완료창
	public void showEndPopUp() {
		// 오늘 하루 운동횟수 추가
		AchievementDB.DayPlaycount = true;
		aDB.ach();
		FXMLLoader another = new FXMLLoader(getClass().getResource("gameSuccess.fxml")); // 불러올 팝업창 지정
		try {
			AnchorPane anotherPage = (AnchorPane) another.load();
			// 다른창 띄우는 작업 .... 2
			Scene anotherScene = new Scene(anotherPage);
			Stage stage = new Stage();
			stage.setResizable(false);
			stage.setScene(anotherScene);
			stage.show();
			// 다른창 띄우는 작업 .... 2 끝.
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
