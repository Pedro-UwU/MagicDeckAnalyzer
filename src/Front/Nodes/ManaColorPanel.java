package Front.Nodes;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.io.File;

public class ManaColorPanel extends HBox {
    private static int TOTAL_MANA_COLORS = 5;
    
    ImageCheckBox[] checkBoxes = new ImageCheckBox[TOTAL_MANA_COLORS];
    

    public ManaColorPanel(int spacing, int width, int height) {
        super(spacing);

        Image redIcon = new Image(new File("./ManaIcons/red.png").toURI().toString());
        Image whiteIcon = new Image(new File("./ManaIcons/white.png").toURI().toString());
        Image greenIcon = new Image(new File("./ManaIcons/green.png").toURI().toString());
        Image blackIcon = new Image(new File("./ManaIcons/black.png").toURI().toString());
        Image blueIcon = new Image(new File("./ManaIcons/blue.png").toURI().toString());

        ImageCheckBox red = new ImageCheckBox(width/TOTAL_MANA_COLORS, "R", redIcon);
        ImageCheckBox white = new ImageCheckBox(width/TOTAL_MANA_COLORS, "W", whiteIcon);
        ImageCheckBox green = new ImageCheckBox(width/TOTAL_MANA_COLORS, "G", greenIcon);
        ImageCheckBox black = new ImageCheckBox(width/TOTAL_MANA_COLORS, "B", blackIcon);
        ImageCheckBox blue = new ImageCheckBox(width/TOTAL_MANA_COLORS, "U", blueIcon);

        checkBoxes[0] = red;
        checkBoxes[1] = white;
        checkBoxes[2] = green;
        checkBoxes[3] = black;
        checkBoxes[4] = blue;
        
        getChildren().addAll(checkBoxes);
    }
    
    public String getSelected() {
        String s = "";
        for (ImageCheckBox c : checkBoxes) {
            if (c.isSelected()) s += c.getValue();
        }
        if (s.equals("")) s = "GREY";
        return s;
    }
}
