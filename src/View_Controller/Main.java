
package View_Controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    private static Stage stage;
    @Override

    /**
     * Starts the application and set initial stage size
     */
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        primaryStage.setTitle("Software1 C-482");
        stage = primaryStage;
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    /**
     * Exits the application
     */
    public static void closeProgram() {
        stage.close();
    }

    /**
     * calls Main
     */
    public static void main(String[] args) {
        launch(args);
    }

}