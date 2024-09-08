import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class _1use extends Application {

    private GridPane keyboard;
    private List<Button> hiddenButtons;

    @Override
    public void start(Stage primaryStage) {

        Label label;
        TextField tf;
        Button button;
        VBox vbox;
        Scene scene;

        tf = new TextField("");
        tf.setMaxWidth(300);

        label = new Label("");
        button = new Button("Clear");

        // Initialize hiddenButtons list
        hiddenButtons = new ArrayList<>();

        // Pass keyboard as an argument
        button.setOnAction(e -> handleClearButtonClick(tf));

        // On-screen keyboard
        keyboard = new GridPane();
        keyboard.setHgap(5);
        keyboard.setVgap(5);

        String[][] keyLayout = {
                {"Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P"},
                {"A", "S", "D", "F", "G", "H", "J", "K", "L"},
                {"Z", "X", "C", "V", "B", "N", "M"},
                {"SPACE", "BACKSPACE"}
        };

        for (int row = 0; row < keyLayout.length; row++) {
            for (int col = 0; col < keyLayout[row].length; col++) {
                Button keyButton = createKeyboardButton(keyLayout[row][col], tf);
                keyboard.add(keyButton, col, row);
            }
        }

        vbox = new VBox(label, tf, button, keyboard);

        // Added "Made by ElliNet13" at the bottom
        vbox.getChildren().add(new Label("Made by ElliNet13"));

        vbox.setSpacing(10);
        vbox.setAlignment(Pos.CENTER);

        scene = new Scene(vbox, 400, 300);

        primaryStage.setTitle("JavaFX On-Screen Keyboard Test (1 use)");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Button createKeyboardButton(String text, TextField textField) {
        Button button = new Button(text);
        button.setMinSize(30, 30);
        button.setMaxSize(50, 50);
        button.setOnAction(e -> handleButtonClick(text, textField));
        return button;
    }

    private void handleButtonClick(String text, TextField textField) {
        switch (text) {
            case "SPACE":
                textField.appendText(" ");
                break;
            case "BACKSPACE":
                String currentText = textField.getText();
                if (!currentText.isEmpty()) {
                    textField.setText(currentText.substring(0, currentText.length() - 1));
                }
                break;
            default:
                textField.appendText(text);
                break;
        }

        // Check if the typed character matches any button's text and hide it
        keyboard.getChildren().stream()
                .filter(node -> node instanceof Button)
                .map(node -> (Button) node)
                .filter(button -> button.getText().equals(text))
                .findFirst()
                .ifPresent(button -> {
                    button.setVisible(false);
                    hiddenButtons.add(button);
                });
    }

    private void handleClearButtonClick(TextField textField) {
        // Make hidden buttons visible again
        hiddenButtons.forEach(button -> button.setVisible(true));
        hiddenButtons.clear();

        // Remove the text box, backspace, space, and clear button
        keyboard.getChildren().removeAll(textField, getButtonByText("BACKSPACE"), getButtonByText("SPACE"), getButtonByText("Clear"));

        textField.clear();
    }

    private Button getButtonByText(String buttonText) {
        return keyboard.getChildren().stream()
                .filter(node -> node instanceof Button)
                .map(node -> (Button) node)
                .filter(button -> button.getText().equals(buttonText))
                .findFirst()
                .orElse(null);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
