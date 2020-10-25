package View_Controller;

import Model.Inventory;
import Model.Part;
import Model.Product;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class AddProduct implements Initializable {
    @FXML
    TextField invInput;
    @FXML
    TextField priceInput;
    @FXML
    TextField nameInput;
    @FXML
    TextField maxInput;
    @FXML
    TextField minInput;
    @FXML
    TextField partSearch;
    @FXML
    TableView<Part> partsTable;
    @FXML
    TableView<Part> productsTable;
    @FXML
    private TableColumn<Part, Integer> partIdCol;
    @FXML
    private TableColumn<Part, String> partNameCol;
    @FXML
    private TableColumn<Part, Integer> partInvCol;
    @FXML
    private TableColumn<Part, Double> partPriceCol;
    @FXML
    private TableColumn<Part, Integer> prodIdCol;
    @FXML
    private TableColumn<Part, String> prodNameCol;
    @FXML
    private TableColumn<Part, Integer> prodInvCol;
    @FXML
    private TableColumn<Part, Double> prodPriceCol;
    private ObservableList<Part> selectedParts = FXCollections.observableArrayList();

    /**
     * searches and populates all parts with and updated filtered list
     */
    public void searchParts(){
        ObservableList filteredList;
        if(isNumeric(partSearch.getText())){
            filteredList = Inventory.lookUpPart(Integer.parseInt(partSearch.getText()));
        } else {
            filteredList = Inventory.lookUpPart(partSearch.getText());
        }
        partsTable.setItems(filteredList);
    }

    /**
     * adds the selected part to associated product
     */
    public void addSelectedPart(){
        this.selectedParts.add(partsTable.getSelectionModel().getSelectedItem());
    }

    /**
     * searches and populates all parts with and updated filtered list
     */
    public void removeSelectedPart(){
        this.selectedParts.remove(partsTable.getSelectionModel().getSelectedItem());
    }

    /**
     * gathers input values and saves the product
     */
    public void save(){
        // todo check that  min is less than max
        String name = nameInput.getText();
        Integer stock = Integer.parseInt(invInput.getText());
        Double price = Double.parseDouble(priceInput.getText());
        Integer max = Integer.parseInt(maxInput.getText());
        Integer min = Integer.parseInt(minInput.getText());
        Product newProduct = new Product(MainController.productCounter, name, price, stock, min, max);
        Inventory.addProduct(newProduct);
        close();
        addAssociatedParts(newProduct);
        MainController.productCounter++;
    }

    /**
     * closes the addProduct pane
     */
    public void close(){
        MainController.closeAddProductStage();
    }

    /**
     * saves all associated parts onto selected product
     */
    private void addAssociatedParts(Product product) {
        for (Part part : this.selectedParts) {
            product.addAssociatedPart(part);
;        }
    }


    /**
     * gets and sets a list of all available parts
     */
    private void getAllParts() {
        partIdCol.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        partInvCol.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getStock()).asObject());
        partNameCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        partPriceCol.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getPrice()).asObject());
        partsTable.setItems(Inventory.getAllParts());
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
     * Initialize add products by calling get all parts;
     */    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getAllParts();
        prodIdCol.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        prodInvCol.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getStock()).asObject());
        prodNameCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        prodPriceCol.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getPrice()).asObject());
        productsTable.setItems(this.selectedParts);
     }
}


