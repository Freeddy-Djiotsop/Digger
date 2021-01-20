package digger.gui.extend;

import java.io.IOException;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Image_label extends Label {

    final ImageView iv;
    final Image img_of_label;

    public Image_label(String img) throws IOException {

        img_of_label = new Image(getClass().getResourceAsStream(img));
        this.setGraphic(iv = new ImageView());
        iv.setImage(img_of_label);
    }

    public ImageView get_image_view() {
        return iv;
    }

}