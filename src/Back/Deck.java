package Back;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;

public class Deck {
    protected String name;
    protected ArrayList<Card> cards;
    protected ArrayList<Card> sideBoard;
    protected DeckColor[] colors;
    private LinkedHashMap<String, Pair<Integer, Integer>> deckStats;

    public Deck(String name, DeckColor[] colors) {
        this.name = name;
        this.colors = colors;
        this.cards = new ArrayList<>();
        this.sideBoard = new ArrayList<>();
        this.deckStats = DeckFileManager.loadHash(name);
    }

    public DeckColor[] getColors() {
        return colors;
    }

    public String getName() {
        return name;
    }

    public void addToDeck(Card card) {
        cards.add(card);
    }

    public void addToDeck(ArrayList<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public void addToSideBoard(Card card) {
        sideBoard.add(card);
    }

    public void addToSideBoard(ArrayList<Card> sideBoard) {this.sideBoard = new ArrayList<>(sideBoard);}

    public void removeFromDeck(Card card) {
        cards.remove(card);
    }

    public void removeFromSideBoard(Card card) {
        sideBoard.remove(card);
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("Deck: " + name + "\n");
        for (Card c : cards) {
            s.append("NAME: " + c.getName() + " --- SET: " + c.getSet()+"\n");
        }
        return s.toString();
    }

    public String toStringWithRepeated() {
        StringBuilder s = new StringBuilder("Deck: " + name + "\n");
        int repetitions = 1;
        Card prev = null;
        for (Card c : cards) {
            if (prev != null) {
                if (c.equals(prev)) repetitions++;
                else {
                    s.append(repetitions + " " + prev.getName() + " (" + prev.getSet() + ")\n");
                    repetitions = 1;
                }
            }
            prev = c;
        }
        s.append(repetitions + " " + prev.getName() + " (" + prev.getSet() + ")\n");

        if (sideBoard.size() > 0) {
            prev = null;
            s.append("\nSideboard\n");
            for (Card c : sideBoard) {
                if (prev != null) {
                    if (c.equals(prev)) repetitions++;
                    else {
                        s.append(repetitions + " " + prev.getName() + " (" + prev.getSet() + ")\n");
                        repetitions = 1;
                    }
                }
                prev = c;
            }
            s.append(repetitions + " " + prev.getName() + " (" + prev.getSet() + ")\n");
        }
        return s.toString();
    }

    public Card getFirstCard() {
        return this.cards.get(0);
    }

    public ArrayList<Card> cards() {
        return new ArrayList<>(this.cards);
    }

    public ArrayList<Card> sideboard() {
        return  new ArrayList<>(this.sideBoard);
    }

    public boolean hasSideboard() { return sideBoard.size() > 0;}

    public String statsToString() {
        StringBuilder s = new StringBuilder("Stats:\n");
        for (String key : deckStats.keySet()) {
            Pair<Integer, Integer> stat = deckStats.get(key);
            if (stat != null) {
                s.append("COLORS: " + key + ">>>WINS: " + stat.getKey() + " -- LOSES: " + stat.getValue() + "\n");
            } else {
                return "NULL ELEMENT";
            }
        }
        return s.toString();
    }

    public Integer getWins(String colors) {
        return deckStats.get(colors).getKey();
    }

    public Integer getLoses(String colors) {
        return deckStats.get(colors).getValue();
    }

    public void incWin(String colors) {
        Pair<Integer, Integer> currentStats = deckStats.get(colors);
        if (currentStats != null) {
            deckStats.put(colors, new Pair<Integer, Integer>(currentStats.getKey() + 1, currentStats.getValue()));
        }
        System.out.println(deckStats.get(colors));
    }

    public void incLoses(String colors) {
        Pair<Integer, Integer> currentStats = deckStats.get(colors);
        if (currentStats != null) {
            deckStats.put(colors, new Pair<Integer, Integer>(currentStats.getKey(), currentStats.getValue()+1));
        }
        System.out.println(deckStats.get(colors));
    }

    public void saveStats() {
        DeckFileManager.writeHash(name, deckStats);
    }

    public String getStats() {
        StringBuilder s = new StringBuilder();
        for (String key : DeckFileManager.getColorsArray()) {
            Pair<Integer, Integer> statValue = deckStats.get(key);
            if (statValue.getKey()>0 || statValue.getValue()>0) {
                s.append("Color: " + key + ", Wins: " + statValue.getKey() + ", Loses: " + statValue.getValue() + "\n");
            }
        }
        return s.toString();
    }
}