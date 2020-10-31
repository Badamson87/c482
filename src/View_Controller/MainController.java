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

/**
 * this class controls the main screen and functionality found there.
 * It is in charge of switching between parts and products, Displaying those items and keeping the updated list.
 *
 * <P><B>
 *  -RunTime Error
 * <br>
 *      A run time error that I experienced during this project was when the program would attempt to parse a null number.
 *   This would happen in the case of converting a string to number from and input field such as price when no input had been
 *   given. My solution to the problem was first to build a list of checks for null values or empty strings, And then a validation
 *   function. This function would take in a string and then using a try/catch block attempt to parse the string into an integer.
 *   A boolean would be return from the function IsValidInt labeling it true or false.
 *<br>
 *   -Extend the program
 *<br>
 *    I think a great way to extend the program would be to expand the search criteria to look up outsource company's that you
 *   acquire parts from. This functionality would ideally would provide to the user the name and contact information of the company.
 *   As well as a complete parts list of associated parts. This would make the process of ordering parts easy when your inventory
 *   is low or out of stock. You could also create a warning message that automatically notifies the user when a part stock is low.
 *   This reminder would help prevent wait times and improve production
 *
 *  </B></P>
 */
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
    private static Stage modifyProductStage;

    private static Part selectedPart;
    private static Product selectedProduct;
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
        try {
            Stage addPartStage = new Stage();
            this.addPartStage = addPartStage;
            addPartStage.initModality(Modality.APPLICATION_MODAL);
            Parent addScene = FXMLLoader.load(getClass().getResource("addPart.fxml"));
            addPartStage.setScene(new Scene(addScene, 600, 500));
            addPartStage.show();
            partsTable.setItems(Inventory.getAllParts());
            partSearch.setText("");
            } catch (Exception ex) {
            messageModal.display("Something went wrong", "Unable to display Add part");
        }
    }

    /**
     * update parts pane and closes add part
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
     * Closes the modify part stage
     */
    public static void closeModifyProductStage(){
        modifyProductStage.close();
    }


    /**
     * launches the add product pane
     */
    public void displayAddProduct() throws IOException {
        try {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        Parent addScene = FXMLLoader.load(getClass().getResource("addProduct.fxml"));
        window.setScene(new Scene(addScene, 800, 500));
        addProductStage = window;
        window.show();
        productsTable.setItems(Inventory.getAllProducts());
        productSearch.setText("");
        } catch (Exception ex) {
            messageModal.display("Something went wrong", "Unable to display add Product");
        }
    }

    /**
     * launches the modify product pane
     */
    public void displayModifyProduct() throws IOException {
        try {

        this.selectedProduct = productsTable.getSelectionModel().getSelectedItem();
        if (selectedProduct == null){
            messageModal.display("No product Selected", "Please select a product to modify");
        } else {
            Stage window = new Stage();
            window.initModality(Modality.APPLICATION_MODAL);
            Parent addScene = FXMLLoader.load(getClass().getResource("modifyProduct.fxml"));
            window.setScene(new Scene(addScene, 800, 500));
            modifyProductStage = window;
            window.show();
        }
        } catch (Exception ex) {
            messageModal.display("Something went wrong", "Unable to display modify product pane");
        }
    }

    /**
     * Checks that a selected part exists then launches the modify part pane
     */
    public void displayModifyPart() throws IOException {
        try {
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
        } catch (Exception ex) {
            messageModal.display("Something went wrong", "Unable to display modify part");
        }
    }

    /**
     * Calls close in main.java
     */
    public void exit() { View_Controller.Main.closeProgram(); }

    /**
     * deletes selected part
     */
    public void deletePart(){
        if (this.checkPartIsUsed(partsTable.getSelectionModel().getSelectedItem())){
            messageModal.display("Unable to delete part", "Part is used in a product");
        } else {
            boolean confirm = ConfirmationModal.display("Parts", "Delete selected part?");
            if (confirm){
                Part selectedPart = partsTable.getSelectionModel().getSelectedItem();
                Inventory.deletePart(selectedPart);
            }
            partsTable.setItems(Inventory.getAllParts());
        }
    }

    /**
     * deletes selected product
     */
    public void deleteProduct(){
        boolean confirm = ConfirmationModal.display("Product", "Delete selected product?");
        if (confirm){
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
     * @return the selected Product
     */
    public static Product getSelectedProduct() {
       return selectedProduct;
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
        if (filteredList.isEmpty()){
            messageModal.display("Unable to find part", "No parts where found that match provided criteria");
        }
    }

    /**
     *
     * @param str is checked to be Numeric
     * @return returns is numeric
     */
    public boolean isNumeric(String str) {
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
     *
     * @param selectedPart is checked to be used in a product
     * @return boolean true if part is used
     */
    private boolean checkPartIsUsed(Part selectedPart) {
        boolean retVal = false;
        Product[] products = Inventory.getAllProducts().toArray(new Product[0]);
        for (Product product : products) {
            if (product.getAllAssociatedParts().contains(selectedPart)) {
                retVal = true;
            }
        }
        return retVal;
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
        if (filteredList.isEmpty()){
            messageModal.display("Unable to find Product", "No products where found that match provided criteria");
        }
    }

    /**
     * calls displayParts view on initialization of main screen
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        displayParts();
    }
}
