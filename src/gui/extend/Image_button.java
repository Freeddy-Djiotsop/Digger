package digger.gui.extend;

import java.io.IOException;
import javafx.beans.binding.Bindings;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Image_button extends Button {

    final ImageView iv;
    final Image image, image1;

    public Image_button(String pressed, String n_pressed) throws IOException {

        image = new Image(getClass().getResourceAsStream(pressed));
        image1 = new Image(getClass().getResourceAsStream(n_pressed));
        iv = new ImageView();
        this.setGraphic(iv);
        iv.imageProperty().bind(Bindings.when(this.pressedProperty()).then(image).otherwise(image1));

        this.setBackground(null);

    }

    public ImageView get_image_view() {
        return iv;
    }
}

