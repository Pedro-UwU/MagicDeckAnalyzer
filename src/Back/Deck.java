package Back;

import sun.tools.asm.CatchData;

import java.util.ArrayList;

public class Deck {
    private String name;
    private ArrayList<Card> cards;
    private ArrayList<Card> sideBoard;
    private DeckColor[] colors;

    public Deck(String name, DeckColor[] colors) {
        this.name = name;
        this.colors = colors;
        this.cards = new ArrayList<>();
        this.sideBoard = new ArrayList<>();
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

    public void addToSideBoard(Card card) {
        sideBoard.add(card);
    }

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
            System.out.println("AAAAAAA");
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
}