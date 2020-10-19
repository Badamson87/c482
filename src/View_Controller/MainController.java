package View_Controller;

import Model.Inventory;
import Model.Part;
import Model.Product;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
    @FXML
    private TableColumn<Part, Integer> partId;
    @FXML
    private TableColumn<Part, String> partName;
    @FXML
    private TableColumn<Part, Double> partPrice;
    @FXML
    private TableColumn<Part, Integer> partStock;
    @FXML
    private TableView<Part> partsTable;
    @FXML
    private TableView<Product> productsTable;
    @FXML
    private TableColumn<Product, Integer> productId;
    @FXML
    private TableColumn<Product, String> productName;
    @FXML
    private TableColumn<Product, Double> productPrice;
    @FXML
    private TableColumn<Product, Integer> productStock;
    private static Stage addPartStage;




    /**
     * sets up the products pane
     * hides the parts pane
     * sets the cell data for product table can calls to get all products
     */
    public void displayProducts() {
        productId.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        productStock.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getStock()).asObject());
        productName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        productPrice.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getPrice()).asObject());
        productsTable.setItems(Inventory.getAllProducts());
        productsPane.setVisible(true);
        partsPane.setVisible(false);
    }

    /**
     * sets up Parts pane
     * hides the products pane
     * sets the cell data for parts table and calls to get all parts
     */
    public void displayParts() {
        partId.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        partStock.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getStock()).asObject());
        partName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        partPrice.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getPrice()).asObject());
        partsTable.setItems(Inventory.getAllParts());
        productsPane.setVisible(false);
        partsPane.setVisible(true);
    }

    /**
     * launches the add part pane
     */
    public void displayAddPart() throws IOException {
        Stage addPartStage = new Stage();
        this.addPartStage = addPartStage;
        addPartStage.initModality(Modality.APPLICATION_MODAL);
        Parent addScene = FXMLLoader.load(getClass().getResource("addPart.fxml"));
        addPartStage.setScene(new Scene(addScene, 600, 500));
        addPartStage.show();
    }

    /**
     * Closes the add part stage
     */
    public static void closeAddPartStage(){
        addPartStage.close();
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
     * deletes selected part
     */
    public void deletePart(){
        boolean confirm = ConfirmationModal.display("Parts", "Delete selected part?");
        if (confirm){
            // todo this is where we would verify delete product
            Part selectedPart = partsTable.getSelectionModel().getSelectedItem();
            Inventory.deletePart(selectedPart);
        }

        System.out.println(confirm);
    }

    /**
     * deletes selected product
     */
    public void deleteProduct(){
        // todo
    }

    /**
     * calls displayParts view on initialization of main screen
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        displayParts();
    }
}
