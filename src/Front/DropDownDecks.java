package Front;

import Back.Deck;
import Back.fileManager.DeckReader;
import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;

import java.util.ArrayList;

public class DropDownDecks extends ComboBox<String> {

    private Deck selectedDeck;

    public DropDownDecks(ArrayList<String> decks) {
        super(FXCollections.observableArrayList(decks));
        this.setValue("Select Deck");
        this.setPrefWidth(Float.POSITIVE_INFINITY);
    }

    public Deck getSelectedDeck() {
        if (this.getValue() == null || this.getValue().equals("Select Deck")) return null;

        Deck deck = DeckReader.readDeck(this.getValue());
        return deck;
    }

    public boolean validSelection() {
        if (getValue().equals("Select Deck")) return false;
        return true;
    }

    public void updateDecks() {
        ArrayList<String> decks = DeckReader.readNames();
        this.setItems(FXCollections.observableArrayList(decks));
    }
}
