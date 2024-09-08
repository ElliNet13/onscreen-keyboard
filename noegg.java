import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class noegg extends Application {

    @Override
    public void start(Stage primaryStage) {

        Label label;
        TextField tf;
        Button button;
        VBox vbox;
        GridPane keyboard;
        Scene scene;

        tf = new TextField("");
        tf.setMaxWidth(300);

        label = new Label("");
        button = new Button("Clear");

        button.setOnAction(e -> tf.clear());

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

        primaryStage.setTitle("JavaFX On-Screen Keyboard Test (No egg)");
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
    }

    public static void main(String[] args) {
        launch(args);
    }
}
