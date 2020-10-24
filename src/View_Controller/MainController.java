package View_Controller;

import Model.Inventory;
import Model.Part;
import Model.Product;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
    @FXML
    private TextField partSearch;
    @FXML
    private TextField productSearch;

    private static Stage addPartStage;
    private static Stage modifyPartStage;
    private static Stage addProductStage;

    private static Part selectedPart;
    public static Integer partCounter = 1;
    public static Integer productCounter = 1;




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
     * Closes the modify part stage
     */
    public static void closeModifyPartStage(){
        modifyPartStage.close();
    }

    /**
     * Closes the modify part stage
     */
    public static void closeAddProductStage(){
        addProductStage.close();
    }

    /**
     * Checks that a selected part exists then launches the modify part pane
     */
    public void displayModifyPart() throws IOException {
        this.selectedPart = partsTable.getSelectionModel().getSelectedItem();
        if(selectedPart == null){
            messageModal.display("No part Selected", "Please select a part to modify");
        } else {
            Stage window = new Stage();
            window.initModality(Modality.APPLICATION_MODAL);
            Parent modifyScene = FXMLLoader.load(getClass().getResource("modifyPart.fxml"));
            window.setScene(new Scene(modifyScene, 600, 500));
            modifyPartStage = window;
            window.show();
        }
    }

    /**
     * launches the add product pane
     */
    public void displayAddProduct() throws IOException {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        Parent addScene = FXMLLoader.load(getClass().getResource("addProduct.fxml"));
        window.setScene(new Scene(addScene, 800, 500));
        addProductStage = window;
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
        partsTable.setItems(Inventory.getAllParts());
    }

    /**
     * deletes selected product
     */
    public void deleteProduct(){
        boolean confirm = ConfirmationModal.display("Product", "Delete selected product?");
        if (confirm){
            // todo this is where we would verify delete product
            Product selectedProduct = productsTable.getSelectionModel().getSelectedItem();
            Inventory.deleteProduct(selectedProduct);
        }
        productsTable.setItems(Inventory.getAllProducts());
    }

    /**
     *
     * @return the selected part
     */
    public static Part getSelectedPart() {
       return selectedPart;
    }

    /**
     * Search's parts and updates viewed list;
     */
    public void searchParts() {
        ObservableList filteredList;
        if(isNumeric(partSearch.getText())){
            filteredList = Inventory.lookUpPart(Integer.parseInt(partSearch.getText()));
        } else {
            filteredList = Inventory.lookUpPart(partSearch.getText());
        }
        partsTable.setItems(filteredList);
    }

    /**
     *
     * @param str is checked to be Numeric
     * @return returns is numeric
     */
    public boolean isNumeric(String str) {
        // null or empty
        if (str == null || str.length() == 0) {
            return false;
        }
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }


    /**
     * Search's products and updates viewed list;
     */
    public void searchProducts() {
        ObservableList filteredList;
        if(isNumeric(productSearch.getText())){
            filteredList = Inventory.lookUpProduct(Integer.parseInt(productSearch.getText()));
        } else {
            filteredList = Inventory.lookUpProduct(productSearch.getText());
        }
        productsTable.setItems(filteredList);
    }

    /**
     * calls displayParts view on initialization of main screen
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        displayParts();
    }
}
