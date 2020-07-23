package Front.Nodes;


import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class DeckInfoTable extends TableView<TableCard> {

    public static final boolean SIDEBOARD = true;
    public static final boolean DECK = false;

    TableColumn<TableCard, Integer> totalCardsCol = new TableColumn<>("Total");
    TableColumn<TableCard, String> nameCol = new TableColumn<>("Name");
    TableColumn<TableCard, String> setCol = new TableColumn<>("Set");

    Boolean sideboard = false;

    public DeckInfoTable(boolean type) {
        super();
        sideboard = type;
        totalCardsCol.setPrefWidth(40);
        nameCol.setPrefWidth(300);
    }

    public void setDeck(TableDeck d) {
        ObservableList<TableCard> cardList;
        if (sideboard) cardList = d.getTableSideboard();
        else cardList = d.getTableCards();
        update(cardList);

    }

    private void update(ObservableList<TableCard> cardList) {
        totalCardsCol.setCellValueFactory(new PropertyValueFactory<TableCard, Integer>("total"));
        nameCol.setCellValueFactory(new PropertyValueFactory<TableCard, String>("name"));
        setCol.setCellValueFactory(new PropertyValueFactory<TableCard, String>("set"));
        this.setItems(cardList);
        this.getColumns().clear();
        this.getColumns().addAll(totalCardsCol,nameCol,setCol);
    }
}
