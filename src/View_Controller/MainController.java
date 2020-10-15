package View_Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    Pane productsPane;
    @FXML
    Pane partsPane;

    /**
     * sets the products pane to visible
     * hides the parts pane
     */
    public void displayProducts() {
        productsPane.setVisible(true);
        partsPane.setVisible(false);
    }

    /**
     * sets Parts pane to visible
     * hides the products pane
     */
    public void displayParts() {
        productsPane.setVisible(false);
        partsPane.setVisible(true);
    }

    /**
     * launches the add part pane
     */
    public void displayAddPart() throws IOException {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        Parent addScene = FXMLLoader.load(getClass().getResource("addPart.fxml"));
        window.setScene(new Scene(addScene, 600, 500));
        window.show();
    }

    /**
     * launches the modify part pane
     */
    public void displayModifyPart() throws IOException {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        Parent addScene = FXMLLoader.load(getClass().getResource("modifyPart.fxml"));
        window.setScene(new Scene(addScene, 600, 500));
        window.show();
    }

    /**
     * launches the add product pane
     */
    public void displayAddProduct() throws IOException {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        Parent addScene = FXMLLoader.load(getClass().getResource("addProduct.fxml"));
        window.setScene(new Scene(addScene, 800, 500));
        window.show();
    }

    /**
     * launches the modify product pane
     */
    public void displayModifyProduct() throws IOException {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        Parent addScene = FXMLLoader.load(getClass().getResource("modifyProduct.fxml"));
        window.setScene(new Scene(addScene, 800, 500));
        window.show();
    }

    /**
     * Calls close in main.java
     */
    public void exit() { View_Controller.Main.closeProgram(); }

    /**
     * calls displayParts view on initialization of main screen
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        displayParts();
    }
}
