import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainApp extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Button openNoEggButton = new Button("Open No Egg Window");
        Button openEggButton = new Button("Open Egg Window");
        Button open1UseButton = new Button("Open 1 Use Window");

        Text madeByText = new Text("Made by ElliNet13");

        VBox root = new VBox(10);
        root.getChildren().addAll(openNoEggButton, openEggButton, open1UseButton, madeByText);

        openNoEggButton.setOnAction(e -> openWindow("noegg"));
        openEggButton.setOnAction(e -> openWindow("egg"));
        open1UseButton.setOnAction(e -> openWindow("_1use"));

        Scene scene = new Scene(root, 300, 200);

        primaryStage.setTitle("JavaFX On-Screen Keyboard Test ()");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void openWindow(String windowName) {
        try {
            Class<?> windowClass = Class.forName(windowName);
            Application window = (Application) windowClass.getDeclaredConstructor().newInstance();
            Stage stage = new Stage();
            window.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
