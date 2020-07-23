package Back;

import Back.Card;
import Back.Deck;
import Back.DeckColor;
import javafx.util.Pair;
import org.json.JSONObject;

import javax.swing.*;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class DeckFileManager {

    private static final Pattern numOfCards = Pattern.compile("^[0-9]+");
    private static final Pattern nameOfCard = Pattern.compile("[0-9]+ (.*) \\(");
    private static final Pattern setName = Pattern.compile("\\((.*)\\)");

    public static final String pathOfDecks = "./decks";
    public static final String pathOfStats = "./stats";


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

    public static LinkedHashMap<String, Pair<Integer, Integer>> createHash(String name) {
        File colors = new File("./src/Back/DeckColors.txt");
        LinkedHashMap<String, Pair<Integer, Integer>> map = new LinkedHashMap<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(colors));
            String line;
            while ((line = reader.readLine()) != null) {
                map.put(line, new Pair<>(0, 0));
            }
            writeHash(name, map);
        } catch (FileNotFoundException e) {
            System.err.println("MISSING: DeckColors.txt");
            return null;
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return map;
    }


    private static String fileContent(File f) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(f));
            StringBuilder s = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                s.append(line);
            }
            return s.toString();
        } catch (FileNotFoundException e) {
            System.err.println("ERROR FILE NOT FOUND");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    public static LinkedHashMap<String, Pair<Integer, Integer>> loadHash(String name) {
        File statFile = new File(pathOfStats+"/"+name+".json");
        LinkedHashMap<String, Pair<Integer, Integer>> map = new LinkedHashMap<>();
        if (statFile.exists()) {
            JSONObject statJSON = new JSONObject(fileContent(statFile));
            for (String key : statJSON.keySet()) {
                String value = (String) statJSON.get(key);
                String[] numericValues = value.split("=");
                map.put(key, new Pair<>(Integer.parseInt(numericValues[0]), Integer.parseInt(numericValues[1])));
            }
            return map;
        }
        return createHash(name);
    }

    public static void writeHash(String name, LinkedHashMap<String, Pair<Integer, Integer>> map) {
        LinkedHashMap<String, String> stringMap = new LinkedHashMap<>();
        for (String key : map.keySet()) {
            stringMap.put(key, map.get(key).toString());
        }
        JSONObject json = new JSONObject(stringMap);
        File statFile = new File(pathOfStats+"/"+name+".json");
        try {
            statFile.createNewFile();
            FileWriter writer = new FileWriter(statFile, false);
            writer.write(json.toString());
            writer.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public static ArrayList<String> getColorsArray() {
        ArrayList<String> colorsArray = new ArrayList<>();
        File colors = new File("./src/Back/DeckColors.txt");
        try {
            BufferedReader reader = new BufferedReader(new FileReader(colors));
            String line;
            while ((line = reader.readLine()) != null) {
                colorsArray.add(line);
            }
        } catch (FileNotFoundException e) {
            System.err.println("MISSING: DeckColors.txt");
            return null;
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return colorsArray;
    }
}
