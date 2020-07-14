package Front;

import Back.Deck;
import Back.fileManager.DeckReader;
import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;

import java.util.ArrayList;

public class DropDownDecks extends ComboBox<String> {

    Deck selectedDeck;

    public DropDownDecks(ArrayList<String> decks) {
        super(FXCollections.observableArrayList(decks));
        this.setValue("Select Deck");
        this.setPrefWidth(Float.POSITIVE_INFINITY);
    }

    public Deck getSelectedDeck() {
        if (this.getValue() == "Select Deck") return null;

        Deck deck = DeckReader.readDeck(this.getValue());
        return deck;
    }
}