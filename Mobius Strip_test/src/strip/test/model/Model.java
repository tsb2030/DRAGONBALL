package strip.test.model;

import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;

//뫼비우스띠처럼 움직일 수 있게 공의 좌표를 계산한다. 
public class Model {

	private int direction = 0; //방향을 의미. 0은 왼쪽 , 1은 오른쪽.
	private int x, y;

	public Model(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	public double getX() { return x;	}

	public void setX(int x) { this.x = x;	}

	public double getY() {	return y;	}

	public void setY(int y) {	this.y = y;	}
	
	public void calculate() {

		if(x >= 100 || x <= 700) {
			if(x >= 225){

				if(y <= 300) {
					--x;
					++y;
				}
				else {
					++x;
					--y;
				}
			}			
			else{
				if(y <= 300) {
					x--;
					y--;
				}
				else {
					x++;
					y++;
				}
			}
			if(x <= 575){

				if(y <= 300) {
					x++;
					y--;
				}
				else {
					x--;
					y--;
				}
			}			
			else{

				if(y <= 300) {
					x++;
					y++;
				}
				else {
					x--;
					y++;
				}
			}
		}
	}

	public void createPath() {
		Path path  = new Path();
		path.getElements().add(new MoveTo(0,0));
		
	}

}
