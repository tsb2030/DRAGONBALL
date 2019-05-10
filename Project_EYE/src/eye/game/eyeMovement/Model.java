package eye.game.eyeMovement;

import javafx.scene.shape.ArcTo;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;

public class Model {

	//뫼비우스 띠 모양의 경로 만들기
	public Path pathCreate() {
		Path path = new Path();
		path.getElements().add(new MoveTo(0, 0));	//이동의 시작점
		path.getElements().add(new ArcTo(200, 200, 180, -500, 0, true, false));
		path.getElements().add(new ArcTo(200, 200, 180, 0, 0, true, false));
		path.getElements().add(new ArcTo(200, 200, 180, 500, 0, true, true));
		path.getElements().add(new ArcTo(200, 200, 180, 0, 0, true, true));
		return path;
	}

	public Path speedPathCreate() {
		Path path = new Path();

		path.getElements().add(new MoveTo(0,0));
		path.getElements().add(new LineTo(0, 350));

		return path;
	}


}
