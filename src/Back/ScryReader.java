package Back;

import Back.Card;
import com.oracle.javafx.jmx.json.JSONReader;
import org.json.JSONObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class ScryReader {
    private static String baseURL = "https://api.scryfall.com/";

    public static String getJSON(String url) {
        try {
            URL cardURL = new URL(url);
            BufferedReader reader = new BufferedReader(new InputStreamReader(cardURL.openStream()));

            StringBuilder s = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                s.append(line);
            }
            return s.toString();
        } catch (MalformedURLException e) {
            System.err.println("INVALID URL");
        } catch (IOException e) {
            System.err.println("ERROR can't read url");
        }
        return null;
    }

    public static String getURLbyCard(Card card) {
        String name = card.getName().toLowerCase();
        String set = card.getSet();
        name = name.replaceAll(" ", "+");
        set = set.toLowerCase();

        //dominaria set code error
        if (set.equals("dar")) set = "dom";
        String request = "/cards/named?exact=" + name;
        if (!set.equals("jmp")) request += "&set=" + set;


        return baseURL + request;
    }

    public static String getImageFromCard(Card c) {
        String s = getJSON(getURLbyCard(c));
        if (s != null) {
            JSONObject json = new JSONObject(s);
            JSONObject imageUrls = json.getJSONObject("image_uris");
            return imageUrls.getString("normal");
        }
        System.err.println("CANNOT GET IMAGE");
        return null;
    }

}
