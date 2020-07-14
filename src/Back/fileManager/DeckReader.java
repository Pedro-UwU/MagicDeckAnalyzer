package Back.fileManager;

import Back.Card;
import Back.Deck;
import Back.DeckColor;

import java.io.*;
import java.util.ArrayList;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DeckReader {

    private static final Pattern numOfCards = Pattern.compile("^[0-9]+");
    private static final Pattern nameOfCard = Pattern.compile("[0-9]+ (.*) \\(");
    private static final Pattern setName = Pattern.compile("\\((.*)\\)");

    public static final String pathOfDecks = "./decks";


    public static ArrayList<String> readNames() {
      ArrayList<String> names = new ArrayList<>();
      File dir = new File(pathOfDecks);
      File[] files = dir.listFiles();
      if (files != null) {
          for (File f : files) {
              if (f.isFile() && f.getName().endsWith(".txt")) names.add(f.getName().replaceAll(".txt", ""));
          }
      }
      return names;
    }

    public static Deck readDeck(String deckName) {
        File deckFile = new File(pathOfDecks+"/"+deckName+".txt");
        Deck deck = null;
        if (deckFile.isFile()) {
            deck = new Deck(deckName, new DeckColor[] {DeckColor.GREY});
            try {
                BufferedReader reader = new BufferedReader(new FileReader(deckFile));
                reader.readLine(); //Skip first line
                String line = reader.readLine();
                boolean side = false;
                while(Optional.ofNullable(line).isPresent()) {
                    if (!line.equals("") && !line.equals("Sideboard")) {
                        Matcher numMatch = numOfCards.matcher(line);
                        Matcher nameMatch = nameOfCard.matcher(line);
                        Matcher setMatch = setName.matcher(line);

                        int total = 0;
                        String name;
                        String setName;

                        if (numMatch.find()) total = Integer.parseInt(numMatch.group(0));
                        else {
                            System.err.println("COLD NOT READ NUM OF CARDS");
                            return null;
                        }

                        if (nameMatch.find()) name = nameMatch.group(1);
                        else {
                            System.err.println("COLD NOT READ NAME OF CARD");
                            return null;
                        }

                        if (setMatch.find()) setName = setMatch.group(1);
                        else {
                            System.err.println("COLD NOT READ SET OF CARD");
                            return null;
                        }

                        for (int i = 0; i < total; i++) {
                            if (!side) deck.addToDeck(new Card(name, setName));
                            else deck.addToSideBoard(new Card(name, setName));
                        }
                    } else if (line.equals("Sideboard")) side = true;

                    line = reader.readLine();
                }
                return deck;
            } catch (IOException e){
                System.out.println("CAN'T READ DECK");
                deck = null;
            }
        }
        return deck;
    }
}
