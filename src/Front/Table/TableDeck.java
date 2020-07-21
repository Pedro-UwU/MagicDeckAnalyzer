package Front.Table;

import Back.Card;
import Back.Deck;
import Back.DeckColor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class TableDeck extends Deck {
    private ArrayList<TableCard> tableCards = new ArrayList<>();
    private ArrayList<TableCard> tableSideboard = new ArrayList<>();

    public TableDeck(Deck d) {
        super(d.getName(), d.getColors());
        addToDeck(d.cards());
        addToSideBoard(d.sideboard());
        updateCards();
    }

    private void updateCards() {
        for (Card c : cards) {
            TableCard card = new TableCard(c.getName(), c.getSet(),1);
            if (tableCards.contains(card)) {
                int index = tableCards.indexOf(card);
                tableCards.get(index).incTotal();
            } else {
                tableCards.add(card);
            }
        }
        for (Card c : sideBoard) {
            TableCard card = new TableCard(c.getName(), c.getSet(),1);
            if (tableSideboard.contains(card)) {
                int index = tableSideboard.indexOf(card);
                tableSideboard.get(index).incTotal();
            } else {
                tableSideboard.add(card);
            }
        }
        tableSideboard.forEach(s -> System.out.println(s.toString()));
    }

    public ObservableList<TableCard> getTableCards() {
        return FXCollections.observableArrayList(tableCards);
    }
    public ObservableList<TableCard> getTableSideboard() {
        return FXCollections.observableArrayList(tableSideboard);
    }
}
