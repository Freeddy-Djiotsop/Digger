package digger.logic;

import javafx.scene.canvas.GraphicsContext;

public class Score {

	public int score;

	public void drawScore(GraphicsContext gc) {

		gc.strokeText("Score : " + score, 2, 10);

	}

}
