package Front.Windows;

import Back.DeckFileManager;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ImportDeckWindow {
    public void display() {
        Stage window = new Stage();
        window.setTitle("Import Deck");
        window.setResizable(false);
        window.initModality(Modality.APPLICATION_MODAL);

        TextField deckNameField = new TextField();
        deckNameField.setPromptText("Enter the name of the deck");
        deckNameField.setPrefWidth(400-15);
        TextArea deckBodyField = new TextArea();
        deckBodyField.setPromptText("Paste MTGA's deck");
        deckBodyField.setPrefHeight(400-35);
        deckBodyField.setPrefWidth(400-15);
        Button createDeckButton = new Button("Import");
        createDeckButton.setMinWidth(100);
        createDeckButton.setOnAction(event -> {
            String name = deckNameField.getText();
            String body = deckBodyField.getText();
            if (!name.isEmpty() && !body.isEmpty()) {
                DeckFileManager.createDeck(name, body);
                window.close();
            }
        });

        HBox layout = new HBox(createDeckButton, new VBox(5, deckNameField, deckBodyField));
        layout.setPrefWidth(500);
        layout.setPrefHeight(400);
        layout.setSpacing(5);
        layout.setAlignment(Pos.TOP_CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }
}
