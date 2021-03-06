package eye.set.controller;
//설정 페이지 컨트롤러

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXToggleButton;

import eye.Music;
import eye.main.Main;
import eye.main.controller.mainController;
import javafx.animation.Animation.Status;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class setController implements Initializable {
	@FXML
	private AnchorPane ExplainPage;
	public static Stage mainPage;

	String timeInterval[] = { "30분", "1시간", "1시간 30분", "2시간", "2시간 30분", "3시간", "3시간 30분", "4시간", "4시간 30분", "5시간" };

	public static Clock clock;
	public static LocalTime currentTime = LocalTime.now();
	public static LocalDate currentDate = LocalDate.now();
	int nextDay = currentDate.getDayOfMonth();
	int today = currentDate.getDayOfMonth();

	public static int currentRestCount = 0;
	public static int totalRestCount = 0;

	public static ArrayList<Integer> restList = new ArrayList<Integer>();
	int restCycle = 0;
	int startDisturbTime;
	int endDisturbTime;
	private int varStartDisturbTime;
	private int varEndDisturbTime;

	public static int restType;
	boolean rotateFlag = false;
	boolean BGMFlag = false;
	boolean effectFlag = false;
	boolean flag = false;// 게임이 진행 중인지 받아올 변수
	boolean tomarrowFlag = false;
	boolean isStartComboboxSet = false; // 콤보박스 세팅을 하고 확인을 눌렀는가?
	boolean isEndComboboxSet = false;

	public static boolean isPause = false; // 현재 pause상태인지?
	public static boolean isStop = false; // 알람 stop변수

	public static boolean isRestStart = false;
	public static boolean isGameStart = false;

	// 만약 운동중에 휴식 알람을 울려야 한다면
	public static boolean maintainRestEvent = false;

	@FXML
	private JFXComboBox<Integer> combobox1;

	@FXML
	private JFXComboBox<Integer> combobox2;

	@FXML
	private JFXComboBox<String> combobox3;

	@FXML
	private JFXToggleButton BGMToggle;

	@FXML
	private JFXToggleButton effectToggle;

	ObservableList<Integer> timeList = FXCollections.observableArrayList();
	ObservableList<String> intervalList = FXCollections.observableArrayList();

	// 초기화 버튼 액션 이벤트
	@FXML
	void cancel(MouseEvent event) throws IOException {
		mainPage = (Stage) BGMToggle.getScene().getWindow();
		if (isPause == false) {
			isStop = false;
			mainController.doNotAlaram = 2;
			Music effectMusic = new Music("setButtonClickEffect", false, 2);
			effectMusic.start();
			// 기존에 알람이 켜져 있다면 멈추는 구문
			if (setController.clock != null) {

				if (setController.clock.animation.getStatus() == Status.RUNNING) {
					setController.clock.animation.stop();
					setController.clock = null;
				} else {
					setController.clock = new Clock();
				}
			}
//			mainController.alarmOnImageView.setOpacity(1);
//			mainController.alarmOffImageView.setOpacity(0);
			setController.clock = new Clock();
			varStartDisturbTime = 8;// 기본 알람 방지 종료 시간 = 8(다음날)
			varEndDisturbTime = 21;// 기본 알람 방지 시작 시간 = 9(저녁 9시)
			today = currentDate.getDayOfMonth();
			nextDay = currentDate.getDayOfMonth() + 1;
			restCycle = 1;// 기본 알람 주기 = 3시간
			restType = 1;
			startDisturbTime = varStartDisturbTime;
			endDisturbTime = varEndDisturbTime;

			// 창을 이전 페이지(main)페이지로 가게 함
			Main.setMusic("introMusic", true, 1);
			Parent mainPage = FXMLLoader.load(getClass().getResource("/eye/main/view/main_page.fxml"));
			ExplainPage.getChildren().setAll(mainPage);
			System.out.println();
			System.out.println("varStartDisturbTime = " + varStartDisturbTime);
			System.out.println("varEndDisturbTime = " + varEndDisturbTime);
			System.out.println("restCycle = " + restCycle);
			System.out.println("restType = " + restType);
			System.out.println();
		}

	}

	// 확인 버튼 액션 이벤트
	@FXML
	void submit(MouseEvent event) throws IOException {
		mainPage = (Stage) BGMToggle.getScene().getWindow();
		if (isPause == false) {
			Music effectMusic = new Music("setButtonClickEffect", false, 2);
			effectMusic.start();
			isStop = false;
			mainController.doNotAlaram = 2;
			System.out.println("submit");
			// 기존에 알람이 켜져 있다면 멈추는 구문
			if (setController.clock != null) {
				if (setController.clock.animation.getStatus() == Status.RUNNING) {
					setController.clock.animation.stop();
					setController.clock = null;
				}
			}
//			mainController.alarmOnImageView.setOpacity(1);
//			mainController.alarmOffImageView.setOpacity(0);
			
			if (!isStartComboboxSet) // 기본 알람 방지 종료 시간 = 8(다음날)
				varStartDisturbTime = 8;
			if (!isEndComboboxSet) // 기본 알람 방지 시작 시간 = 9(저녁 9시)
				varEndDisturbTime = 21;
			if (restCycle == 0) // 기본 알람 주기 = 30분
				restCycle = 1;
			if (restType == 0) // 없으면 1 = 짧음
				restType = 1;

			startDisturbTime = varStartDisturbTime;
			endDisturbTime = varEndDisturbTime;

			setController.clock = new Clock();

			// 창을 이전 페이지(main)페이지로 가게 함
			Main.setMusic("introMusic", true, 1);
			Parent mainPage = FXMLLoader.load(getClass().getResource("/eye/main/view/main_page.fxml"));
			ExplainPage.getChildren().setAll(mainPage);
			System.out.println();
			System.out.println("varStartDisturbTime = " + varStartDisturbTime);
			System.out.println("varEndDisturbTime = " + varEndDisturbTime);
			System.out.println("restCycle = " + restCycle);
			System.out.println("restType = " + restType);
			System.out.println();
		}

	}

	// backButton Action
	@FXML
	void backButtonAction(MouseEvent event) throws IOException {
		if (isPause == false) {
			Music effectMusic = new Music("setButtonClickEffect", false, 2);
			effectMusic.start();
			Main.setMusic("introMusic", true, 1);
			Parent mainPage = FXMLLoader.load(getClass().getResource("/eye/main/view/main_page.fxml"));
			ExplainPage.getChildren().setAll(mainPage);
		}

	}

	// shorRestplay
	public static void playShorRest() throws IOException {
		currentRestCount++;
		isRestStart = true;
		System.out.println("현재 currentRestCount = " + currentRestCount);
		System.out.println("현재 totalRestCount = " + totalRestCount);
		double ran = Math.random();
		int intValue = (int) (ran * 5) + 1;
		Parent parent;
		Scene scene;
		// 이미 배열에 차있으면 다른 변수를 가져오도록 한다.
		while (restList.contains(intValue)) {
			double ran2 = Math.random();
			intValue = (int) (ran2 * 5) + 1;
		}
		switch (intValue) {
		case 1:
			// 휴식 페이지 1번을 부르는 기능! 눈꼭 감기
			restList.add(1);
			// 음악 바꾸기
			if (restList.size() == 1)
				Main.setMusic("restMusic", true, 1);
			parent = FXMLLoader.load(Main.class.getResource("/eye/rest/view/ClosedEyeInfo.fxml"));
			scene = new Scene(parent);
			mainPage.setScene(scene);
			break;
		case 2:
			// 휴식 페이지 2번을 부르는 기능! 멀리보기
			restList.add(2);
			// 음악 바꾸기
			if (restList.size() == 1)
				Main.setMusic("restMusic", true, 1);
			parent = FXMLLoader.load(Main.class.getResource("/eye/rest/view/LookAfarInfo.fxml"));
			scene = new Scene(parent);
			mainPage.setScene(scene);
			break;
		case 3:
			// 휴식 페이지 1번을 부르는 기능! 눈 마사지
			restList.add(3);
			// 음악 바꾸기
			if (restList.size() == 1)
				Main.setMusic("restMusic2", true, 1);
			parent = FXMLLoader.load(Main.class.getResource("/eye/rest/view/EyeMassageInfo.fxml"));
			scene = new Scene(parent);
			mainPage.setScene(scene);
			break;
		case 4:
			// 휴식 페이지 1번을 부르는 기능! 눈 굴리기
			restList.add(4);
			// 음악 바꾸기
			if (restList.size() == 1)
				Main.setMusic("restMusic2", true, 1);
			parent = FXMLLoader.load(Main.class.getResource("/eye/rest/view/EyeRollingInfo.fxml"));
			scene = new Scene(parent);
			mainPage.setScene(scene);
			break;
		case 5:
			// 휴식 페이지 1번을 부르는 기능! 눈 온찜질
			restList.add(5);
			// 음악 바꾸기
			if (restList.size() == 1)
				Main.setMusic("restMusic2", true, 1);
			parent = FXMLLoader.load(Main.class.getResource("/eye/rest/view/WarmEyeInfo.fxml"));
			scene = new Scene(parent);
			mainPage.setScene(scene);
			break;

		default:
			isSizeFull();
			break;
		}
	}

	// 만약 배열의 크기가 다 찼을 경우( == 5개의 휴식을 모두 취했을 경우) 배열을 비워두도록 한다.
	public static void isSizeFull() {
		if (restList.size() == 5)
			restList.clear();
	}

	// LongRestEvent!!
	public static void playLongRest() throws IOException {
		currentRestCount++;
		isRestStart = true;
		System.out.println("현재 currentRestCount = " + currentRestCount);
		System.out.println("현재 totalRestCount = " + totalRestCount);
		double ran = Math.random();
		int intValue = (int) (ran * 5) + 1;
		Parent parent;
		Scene scene;
		// 이미 배열에 차있으면 다른 변수를 가져오도록 한다.
		while (restList.contains(intValue)) {
			double ran2 = Math.random();
			intValue = (int) (ran2 * 5) + 1;
		}

		switch (intValue) {
		case 1:
			// 휴식 페이지 1번을 부르는 기능! 눈꼭 감기
			restList.add(1);
			// 음악 바꾸기
			if (restList.size() == 1)
				Main.setMusic("restMusic1", true, 1);
			parent = FXMLLoader.load(Main.class.getResource("/eye/rest/view/ClosedEyeInfo.fxml"));
			scene = new Scene(parent);
			mainPage.setScene(scene);
			break;
		case 2:
			// 휴식 페이지 2번을 부르는 기능! 멀리보기
			restList.add(2);
			// 음악 바꾸기
			if (restList.size() == 1)
				Main.setMusic("restMusic1", true, 1);
			parent = FXMLLoader.load(Main.class.getResource("/eye/rest/view/LookAfarInfo.fxml"));
			scene = new Scene(parent);
			mainPage.setScene(scene);
			break;
		case 3:
			// 휴식 페이지 1번을 부르는 기능! 눈 마사지
			restList.add(3);
			// 음악 바꾸기
			if (restList.size() == 1)
				Main.setMusic("restMusic2", true, 1);
			parent = FXMLLoader.load(Main.class.getResource("/eye/rest/view/EyeMassageInfo.fxml"));
			scene = new Scene(parent);
			mainPage.setScene(scene);
			break;
		case 4:
			// 휴식 페이지 1번을 부르는 기능! 눈 굴리기
			restList.add(4);
			// 음악 바꾸기
			if (restList.size() == 1)
				Main.setMusic("restMusic2", true, 1);
			parent = FXMLLoader.load(Main.class.getResource("/eye/rest/view/EyeRollingInfo.fxml"));
			scene = new Scene(parent);
			mainPage.setScene(scene);
			break;
		case 5:
			// 휴식 페이지 1번을 부르는 기능! 눈 온찜질
			restList.add(5);
			// 음악 바꾸기
			if (restList.size() == 1)
				Main.setMusic("restMusic2", true, 1);
			parent = FXMLLoader.load(Main.class.getResource("/eye/rest/view/WarmEyeInfo.fxml"));
			scene = new Scene(parent);
			mainPage.setScene(scene);
			break;

		default:
			isSizeFull();
			break;
		}
	}

	// RotateRestEvent!!
	public static void playRotateRest() throws IOException {
		double ran = Math.random();
		int intValue = (int) (ran * 2) + 1;

		if (intValue == 1) {
			totalRestCount = 3;
			playShorRest();
		} else {
			totalRestCount = 5;
			playLongRest();
		}
	}

	// 짧은 버튼 눌렀을 경우 type지정
	@FXML
	void restTypeIsShort(MouseEvent event) {
		Music effectMusic = new Music("setButtonClickEffect", false, 2);
		effectMusic.start();
		if (isPause == false) {
			restType = 1;
			System.out.println("resType = short");
		}

	}

	// 긴 버튼을 눌렀을 경우 type지정
	@FXML
	void restTypeIsLong(MouseEvent event) {
		Music effectMusic = new Music("setButtonClickEffect", false, 2);
		effectMusic.start();
		if (isPause == false) {
			restType = 2;
			System.out.println("resType = long");
		}

	}

	// 보통 버튼을 눌렀을 경우 type지정
	@FXML
	void restTypeIsRotate(MouseEvent event) {
		Music effectMusic = new Music("setButtonClickEffect", false, 2);
		effectMusic.start();
		if (isPause == false) {
			restType = 3;
			System.out.println("resType = rotate");
		}

	}

	// 휴식 이벤트 시작
	public void playRest(int restType) throws IOException {
		switch (restType) {

		// ShortEvent
		case 1:
			totalRestCount = 3;
			playShorRest();
			System.out.println("play short Rest");
			break;

		// LongEvent
		case 2:
			totalRestCount = 5;
			playLongRest();
			System.out.println("play long Rest");
			break;

		// RotateEvent!
		case 3:
			playRotateRest();
			System.out.println("play rotate Rest");
			break;

		default:
			break;
		}
	}

	// 토글 기본 색깔 바꾸기
	public void setToggleColors() {
		System.out.println("change Color");
		Color firstBGMLUnToggleColor = (Color) BGMToggle.getUnToggleColor();
		Color firstBGMLUnToggleLineColor = (Color) BGMToggle.getUnToggleLineColor();
		Color firstBGMLToggleColor = (Color) BGMToggle.getToggleColor();
		Color firstBGMLToggleLineColor = (Color) BGMToggle.getToggleLineColor();

		Color firstEffectLUnToggleColor = (Color) effectToggle.getUnToggleColor();
		Color firstEffectLUnToggleLineColor = (Color) effectToggle.getUnToggleLineColor();
		Color firstEffectLToggleColor = (Color) effectToggle.getToggleColor();
		Color firstEffectLToggleLineColor = (Color) effectToggle.getToggleLineColor();

		BGMToggle.setUnToggleColor(firstBGMLToggleColor);
		BGMToggle.setUnToggleLineColor(firstBGMLToggleLineColor);
		BGMToggle.setToggleColor(firstBGMLUnToggleColor);
		BGMToggle.setToggleLineColor(firstBGMLUnToggleLineColor);

		effectToggle.setUnToggleColor(firstEffectLToggleColor);
		effectToggle.setUnToggleLineColor(firstEffectLToggleLineColor);
		effectToggle.setToggleColor(firstEffectLUnToggleColor);
		effectToggle.setToggleLineColor(firstEffectLUnToggleLineColor);
	}

	// 초기화 할때 사용하는 메소드
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// 처음에 켜진 상태이기 때문에 바꿈..
		setToggleColors();
		setToggleLocation();
		// 토글 위치 검사 함수

		for (int i = 0; i < 24; i++) {
			timeList.add(i);
		}
		for (int i = 0; i < timeInterval.length; i++) {
			intervalList.add(timeInterval[i]);
		}
		// 방해금지 시간 콤보박스에 0~23시 추가
		combobox1.setItems(timeList);
		combobox2.setItems(timeList);
		// 실행주기 간격 콤보박스에 timeInterval배열 값들 추가
		combobox3.setItems(intervalList);
	}

	// 토글위치 재조정 함수
	public void setToggleLocation() {
		if (Music.BGMFlag == false) {
			BGMToggle.fire();
		}
		if (Music.effectFlag == false) {
			effectToggle.fire();
		}
	}

	// BGMToggle
	@FXML
	void BGMToggleAction(ActionEvent event)
			throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		if (isPause == false) {
			Music effectMusic = new Music("setButtonClickEffect", false, 2);
			effectMusic.start();
			if (BGMFlag == false) {
				System.out.println("BGM music close");
				Main.closeMusic();
				Music.BGMFlag = false;
				BGMFlag = true;
			} else {
				System.out.println("BGM music restart");
				Music.BGMFlag = true;
				Main.reStartMusic(true, 1);
				BGMFlag = false;
			}
		}
	}

	// effectToggle
	@FXML
	void effectToggleAction(ActionEvent event) {
		if (isPause == false) {
			Music effectMusic = new Music("setButtonClickEffect", false, 2);
			effectMusic.start();
			if (effectFlag == false) {
				System.out.println("effect music close");
				Music.effectFlag = false;
				effectFlag = true;
			} else {
				System.out.println("effect music restart");
				Music.effectFlag = true;
				effectFlag = false;
			}
		}
	}

	// 방해금지 종료시간 설정
	@FXML
	void setEndDisturbTime(ActionEvent event) {
		Music effectMusic = new Music("setButtonClickEffect", false, 2);
		effectMusic.start();
		if (isPause == false) {
			isEndComboboxSet = true;
			varEndDisturbTime = (int) combobox2.getValue();
			System.out.println("varEndDisturbTime = " + varEndDisturbTime);
		}

	}

	// 방해금지 시작시간 설정
	@FXML
	void setStartDisturbTime(ActionEvent event) {
		Music effectMusic = new Music("setButtonClickEffect", false, 2);
		effectMusic.start();
		if (isPause == false) {
			isStartComboboxSet = true;
			varStartDisturbTime = (int) combobox1.getValue();
			System.out.println("varStartDisturbTime = " + varStartDisturbTime);
		}

	}

	// 실행 주기 설정
	@FXML
	void setRestCycle(ActionEvent event) {
		Music effectMusic = new Music("setButtonClickEffect", false, 2);
		effectMusic.start();
		if (isPause == false) {
			String s = (String) combobox3.getValue();
			System.out.println(s);
			findOne(s);
		}

	}

	// 입력된 값에 따라 integer값을 restCycle에 저장해 둔다.
	public void findOne(String s) {
		switch (s) {
		case "30분":
			restCycle = 1;
			break;
		case "1시간":
			restCycle = 2;
			break;
		case "1시간 30분":
			restCycle = 3;
			break;
		case "2시간":
			restCycle = 4;
			break;
		case "2시간 30분":
			restCycle = 5;
			break;
		case "3시간":
			restCycle = 6;
			break;
		case "3시간 30분":
			restCycle = 7;
			break;
		case "4시간":
			restCycle = 8;
			break;
		case "4시간 30분":
			restCycle = 9;
			break;
		case "5시간":
			restCycle = 10;
			break;

		default:
			break;
		}
	}

	// pause Button
	public void pause() {
		Music effectMusic = new Music("setButtonClickEffect", false, 2);
		effectMusic.start();
		isPause = true;
		ExplainPage.setOpacity(0.45);
	}

	// 타이머
	public class Clock extends Pane {

		int currentDateDay = currentDate.getDayOfMonth();
		int currnetTimeHour = currentTime.getHour();
		private Timeline animation;
		private int timeTmp = 1;

		public Clock() {

			animation = new Timeline(new KeyFrame(Duration.seconds(1), e -> {

				// 시간이 째깍 흘러갑니다.
				timeLabel();

				// 방해금지 시간이 아니라면!
				System.out.println("현재 날짜: " + currentDateDay);
				System.out.println("현재 시각 : " + currnetTimeHour);
				System.out.println("방해 시작 시각: " + startDisturbTime);
				System.out.println("방해 종료 시각: " + endDisturbTime);
				System.out.println("현재 초 : " + currentTime.getSecond());
				System.out.println("알람 시작 초 : " + (currentTime.getSecond() + restCycle * 10));
				if (isStop == true) {
					clock.animation.stop();
					clock = null;

					mainController.alarmOnImageView.setVisible(false);
					mainController.alarmOffImageView.setVisible(true);
				}

				if (!((currnetTimeHour > startDisturbTime) && (currnetTimeHour < endDisturbTime))) {
					// 알람 설정 주기를 설정하였는가?
					if (restCycle != 0) {
						// 현재 휴식 프로그램을 실행 중인가? 설정 안했으면 false
						System.out.println("flag가 false면알람 주기 시작" + flag);
						if (isRestStart == false && isGameStart == false) {
							if (timeTmp == (restCycle * 10)) {
								// alarmPopup등장
								isPause = true;
								FXMLLoader fxmlLoader = new FXMLLoader(
										Main.class.getResource("/eye/set/view/alarmPopup.fxml"));
								try {
									AnchorPane alarmPopupPane = (AnchorPane) fxmlLoader.load();
									Scene scene = new Scene(alarmPopupPane);
									Stage stage = new Stage();
									stage.setResizable(false);
									stage.setScene(scene);
									stage.show();
								} catch (IOException e1) {
									e1.printStackTrace();
								}
							}
						} else {
							maintainRestEvent = true;
						}
					}
				}
				if (timeTmp == (restCycle * 10))
					timeTmp -= (restCycle * 10);
				System.out.println("현재timeTmp ========== " + timeTmp);

			}));
			animation.setCycleCount(Timeline.INDEFINITE);
			animation.play();
		}

		private void timeLabel() {
			if (isRestStart == false && isGameStart == false) {
				timeTmp++;
			}

		}

		public int getTime() {
			return timeTmp;
		}
	}
}
