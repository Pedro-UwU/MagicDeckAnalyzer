package Front;

import Back.Card;
import Back.Deck;
import Back.api.ScryFall.ScryReader;
import Front.Table.DeckInfoTable;
import Front.Table.TableCard;
import Front.Table.TableDeck;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class DeckInfoVisualizer extends HBox {

    ImageView firstCard = new ImageView();
    DeckInfoTable table = new DeckInfoTable();


    Deck deck;

    public DeckInfoVisualizer(int space, int width, int height) {
        super(space);
        this.setPadding(new Insets(space,0,space,0));

        firstCard.setPreserveRatio(true);

        VBox cardImageBox = new VBox(0);
        cardImageBox.setAlignment(Pos.CENTER_RIGHT);
        cardImageBox.getChildren().addAll(firstCard);
        firstCard.setFitWidth(width/2 - 5*space);

        table.setOnMouseClicked(event -> {
            Card c = table.getSelectionModel().getSelectedItem();
            if (deck != null && c != null) firstCard.setImage(new Image(ScryReader.getImageFromCard(c)));
        });

        table.setMinWidth(width/2);


        this.getChildren().addAll(table,cardImageBox);


    }

    public void setDeck(Deck d) {
        if (d != null) {
            deck = d;
            TableDeck tableDeck = new TableDeck(d);
            table.setDeck(tableDeck);
            firstCard.setImage(null);
        }
    }


}
