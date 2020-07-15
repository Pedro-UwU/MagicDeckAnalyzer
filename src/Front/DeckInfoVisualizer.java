package Front;

import Back.Deck;
import Back.api.ScryFall.ScryReader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class DeckInfoVisualizer extends HBox {

    VBox buttonsBox;
    Label deckCards = new Label("No deck Selected");
    ScrollPane deckInfo = new ScrollPane();
    Button showCardButton = new Button("Show First Card");
    ImageView firstCard = new ImageView();
    Deck deck;

    public DeckInfoVisualizer(int space, int width, int height) {
        super(space);
        this.setPadding(new Insets(space,0,space,0));
        buttonsBox = new VBox(space);

        showCardButton.setOnAction(event -> {
            if (deck != null) firstCard.setImage(new Image(ScryReader.getImageFromCard(deck.getFirstCard())));
        });


        buttonsBox.setMaxWidth(width/6);
        showCardButton.setPrefWidth(width);
        deckInfo.setMinWidth(width/3);
        firstCard.setPreserveRatio(true);

        VBox cardImageBox = new VBox(0);
        cardImageBox.setAlignment(Pos.CENTER);
        cardImageBox.getChildren().addAll(firstCard);
        cardImageBox.setMaxWidth(width/2);
        firstCard.setFitWidth(width/2 - 5*space);

        buttonsBox.getChildren().add(showCardButton);
        this.getChildren().addAll(buttonsBox,deckInfo,cardImageBox);


        deckInfo.setContent(deckCards);
        deckInfo.setFitToWidth(true);



    }

    public void setDeck(Deck d) {
        if (d != null) {
            deck = d;
            deckCards.setText(d.toStringWithRepeated());
        }
    }


}
