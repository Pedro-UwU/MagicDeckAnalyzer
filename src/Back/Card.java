package Back;

import javafx.beans.property.SimpleStringProperty;

import java.awt.*;

public class Card {
    private String name;
    private String description;
    private String  set;

    public Card(String name, String set) {
        this.name = name;
        this.set = set;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getSet() {
        return set;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || o.getClass() != this.getClass()) return false;
        Card c = (Card) o;
        if (name.equals(c.getName())) {
            if (set.equals(c.getSet())) return true;
        }
        return false;
    }
}
