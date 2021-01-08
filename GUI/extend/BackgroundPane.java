package digger.gui.extend;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class BackgroundPane extends Pane {
	

    final ImageView bg_img;

    public BackgroundPane(String img) {

        bg_img = new ImageView(new Image(getClass().getResourceAsStream(img)));

        bg_img.setFitWidth(650);
        bg_img.setFitHeight(500);

        this.getChildren().add(bg_img);
    }
}
