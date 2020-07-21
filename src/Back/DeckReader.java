package Back;

import Back.Card;
import Back.Deck;
import Back.DeckColor;

import java.io.*;
import java.util.ArrayList;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class DeckReader {

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
        return null;
    }

    public static void ReadDeckLog(String deckName) {
        File deckLog = new File(pathOfDecks+"/"+deckName+".csv");
        try {
            if (deckLog.createNewFile()){
                StringBuilder s = new StringBuilder("COLORS,WIN,LOST\n");
                File deckColorFile = new File("./src/Back/DeckColors.txt");
                BufferedReader reader = new BufferedReader(new FileReader(deckColorFile));
                String line = reader.readLine();
                while (Optional.ofNullable(line).isPresent()) {
                    s.append(line + ",0,0\n");
                    line = reader.readLine();
                }
                FileWriter writer = new FileWriter(pathOfDecks+"/"+deckName+".csv");
                System.out.println(s);
                writer.write(s.toString());
                writer.close();
            }
        } catch (FileNotFoundException e) {
            System.err.println("MISSING: DeckColors.txt");
        } catch (IOException e) {
            System.err.println("Error reading DeckColors.txt");
        }
    }

    public static void createDeck(String name, String body) {
        File deckFile = new File(pathOfDecks+"/"+name+".txt");
        try {
            deckFile.createNewFile();
            FileWriter fileWriter = new FileWriter(deckFile, false);
            fileWriter.write(body);
            fileWriter.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
