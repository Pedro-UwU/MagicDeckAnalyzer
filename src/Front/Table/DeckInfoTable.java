package Front.Table;


import Back.Card;
import Back.Deck;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class DeckInfoTable extends TableView<TableCard> {

    TableColumn<TableCard, Integer> totalCardsCol = new TableColumn<>("Total");
    TableColumn<TableCard, String> nameCol = new TableColumn<>("Name");
    TableColumn<TableCard, String> setCol = new TableColumn<>("Set");

    public DeckInfoTable() {
        super();
        totalCardsCol.setPrefWidth(40);
        nameCol.setPrefWidth(300);
    }

    public void setDeck(TableDeck d) {
        ObservableList<TableCard> cardList = d.getTableCards();
        totalCardsCol.setCellValueFactory(new PropertyValueFactory<TableCard, Integer>("total"));
        nameCol.setCellValueFactory(new PropertyValueFactory<TableCard, String>("name"));
        setCol.setCellValueFactory(new PropertyValueFactory<TableCard, String>("set"));
        this.setItems(cardList);
        this.getColumns().clear();
        this.getColumns().addAll(totalCardsCol,nameCol,setCol);
    }
}
