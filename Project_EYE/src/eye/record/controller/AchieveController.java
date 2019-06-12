package eye.record.controller;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormatSymbols;
import java.util.Locale;
import java.util.ResourceBundle;

import eye.db.AchievementDB;
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
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AchieveController implements Initializable {
	AchievementDB aDB = new AchievementDB();

	@FXML
	private AnchorPane achieveMainPage;

	@FXML
	private LineChart recordChart;

	@FXML
	private ImageView backBtn, attendance7, attendance30, trophy, luckySeven, AI, docTrio, humanEvol, fourPoint, baekJW,
			baby, glowEye, starNight, bat, perfect2, mistake2, timeBomb;

	@FXML
	private ProgressIndicator Week, Month, Record, Seven, Human, Double, Evolution, Saryunan, Easy, Novice, Fresh,
			Color, Void, Perfect, Mistake, Dangerous;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		attendance7.setOpacity(0.3);
		attendance30.setOpacity(0.3);
		trophy.setOpacity(0.3);
		luckySeven.setOpacity(0.3);
		AI.setOpacity(0.3);
		docTrio.setOpacity(0.3);
		humanEvol.setOpacity(0.3);
		fourPoint.setOpacity(0.3);
		baekJW.setOpacity(0.3);
		baby.setOpacity(0.3);
		glowEye.setOpacity(0.3);
		starNight.setOpacity(0.3);
		bat.setOpacity(0.3);
		perfect2.setOpacity(0.3);
		mistake2.setOpacity(0.3);
		timeBomb.setOpacity(0.3);
		aDB.achcnt();
		Opa();
		// TODO Auto-generated method stub
		backBtn.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				try {
					Main.setMusic("introMusic", true, 1);
					Parent recordPage = FXMLLoader.load(getClass().getResource("/eye/record/view/recordMain.fxml"));
					Scene scene = new Scene(recordPage);
					scene.getStylesheets()
							.add(getClass().getResource("/eye/record/view/recordDesign.css").toExternalForm());
					Stage primaryStage = (Stage) backBtn.getScene().getWindow();
					primaryStage.setScene(scene);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		Week.setProgress(AchievementDB.WeekPlay / 7);
		Month.setProgress(AchievementDB.MonthPlay / 30);
		Record.setProgress(AchievementDB.Record);
		Seven.setProgress(AchievementDB.Lucky);
		Human.setProgress(AchievementDB.Human);
		Double.setProgress(AchievementDB.Double);
		Evolution.setProgress(AchievementDB.Evolution);
		Saryunan.setProgress(AchievementDB.Saryunan);
		Easy.setProgress(AchievementDB.Easy / 5);
		Novice.setProgress(AchievementDB.Novice);
		Fresh.setProgress(AchievementDB.Fresh / 20);
		Color.setProgress(AchievementDB.Color / 7);
		Void.setProgress(AchievementDB.Void / 7);
		Perfect.setProgress(AchievementDB.Perfect);
		Mistake.setProgress(AchievementDB.Mistake);
		Dangerous.setProgress(AchievementDB.Dangerous);
	}

	public void Opa() {
		aDB.achcnt();
		if (AchievementDB.WeekPlay == 7) {
			attendance7.setOpacity(1.0);
		}
		if (AchievementDB.MonthPlay == 30) {
			attendance30.setOpacity(1.0);
		}
		if (AchievementDB.Record == 1) {
			System.out.println("종호가하라고해서");
			trophy.setOpacity(1.0);
		}
		if (AchievementDB.Lucky == 1) {
			System.out.println("종호가하라고해서");
			luckySeven.setOpacity(1.0);
		}
		if (AchievementDB.Human == 1) {
			AI.setOpacity(1.0);
		}
		if (AchievementDB.Double == 1) {
			docTrio.setOpacity(1.0);
		}
		if (AchievementDB.Evolution == 1) {
			humanEvol.setOpacity(1.0);
		}
		if (AchievementDB.Saryunan == 1) {
			fourPoint.setOpacity(1);
		}
		if (AchievementDB.Easy == 5) {
			baekJW.setOpacity(1);
		}
		if (AchievementDB.Novice == 1) {
			baby.setOpacity(1);
		}
		if (AchievementDB.Fresh == 20) {
			glowEye.setOpacity(1);
		}
		if (AchievementDB.Color == 7) {
			starNight.setOpacity(1);
		}
		if (AchievementDB.Void == 7) {
			bat.setOpacity(1);
		}
		if (AchievementDB.Perfect == 1) {
			perfect2.setOpacity(1);
		}
		if (AchievementDB.Mistake == 1) {
			mistake2.setOpacity(1);
		}
		if (AchievementDB.Dangerous == 1) {
			timeBomb.setOpacity(1);
		}
	}

}
