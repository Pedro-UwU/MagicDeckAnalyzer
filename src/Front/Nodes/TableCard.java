package Front.Nodes;

import Back.Card;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class TableCard extends Card {
    private SimpleIntegerProperty total;
    private SimpleStringProperty nameProperty;
    private SimpleStringProperty setProperty;

    public TableCard(String name, String set, int total) {
        super(name, set);
        this.total = new SimpleIntegerProperty(total);
        this.nameProperty = new SimpleStringProperty(getName());
        this.setProperty = new SimpleStringProperty(getSet());
    }

    public int getTotal() {
        return total.get();
    }

    public String getNameProperty() {
        return nameProperty.get();
    }

    public String getSetProperty() {
        return setProperty.get();
    }

    public void incTotal() {
        total = new SimpleIntegerProperty(total.get()+1);
    }
}
