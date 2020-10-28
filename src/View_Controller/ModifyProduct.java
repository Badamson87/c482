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

public class ModifyProduct implements Initializable {
    @FXML
    TextField idInput;
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
    private Product selectedProduct;

    /**
     * adds the selected part to associated product
     */
    public void addSelectedPart(){
        selectedProduct.addAssociatedPart(partsTable.getSelectionModel().getSelectedItem());
    }

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
     * gathers input values and saves the product
     */
    public void save(){
        String errorMessage = this.validatePart(nameInput.getText(), invInput.getText(), priceInput.getText(), minInput.getText(), maxInput.getText());
        if (errorMessage.length() > 0){
            messageModal.display("Unable to save Product", errorMessage);
        } else {
            String name = nameInput.getText();
            Integer stock = Integer.parseInt(invInput.getText());
            Double price = Double.parseDouble(priceInput.getText());
            Integer max = Integer.parseInt(maxInput.getText());
            Integer min = Integer.parseInt(minInput.getText());
            Integer id = Integer.parseInt(idInput.getText());
            Product newProduct = new Product(id, name, price, stock, min, max);
            Inventory.updateProduct(newProduct, selectedProduct);
            close();
            addAssociatedParts(newProduct);
            MainController.productCounter++;
        }
    }

    /**
     *
     * @return if part state is valid
     */
    private String validatePart(String name, String inv, String price, String min, String max){
        if (name == null || name.length() == 0){
            return "Name field can not be empty";
        }
        if (inv == null || inv.length() == 0) {
            return "Inv field can not be empty";
        }
        if (price == null || price.length() == 0){
            return "Price field can not be empty";
        }
        if (min == null || min.length() == 0){
            return "Min field can not be empty";
        }
        if (max == null || max.length() == 0) {
            return "Max field can not be empty";
        }
        if (validateIsDouble(price) == false) {
            return "Price field must be a number";
        }
        if (validateIsInt(inv) == false) {
            return "Inv field must be a number";
        }
        if (validateIsInt(min) == false){
            return "Min field must be a number";
        }
        if (validateIsInt(max) == false) {
            return "Max field must be a number";
        }
        if (Integer.parseInt(min) > Integer.parseInt(max) || Integer.parseInt(min) == Integer.parseInt(max)) {
            return "Min Must be less than Max";
        }
        return "";
    }

    /**
     *Determine if string can be converted to int
     */
    static boolean validateIsInt(String string){
        try {
            Integer.parseInt(string);
            return true;
        }
        catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     *Determine if string can be converted to Double
     */
    static boolean validateIsDouble(String string) {
        try {
            Double.parseDouble(string);
            return true;
        }
        catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * closes the addProduct pane
     */
    public void close(){
        MainController.closeModifyProductStage();
    }

    /**
     * saves all associated parts onto selected product
     */
    private void addAssociatedParts(Product product) {
        for (Part part : this.selectedParts) {
            product.addAssociatedPart(part);
        }
    }

    /**
     * searches and populates all parts with and updated filtered list
     */
    public void removeSelectedPart(){
        if (ConfirmationModal.display("Remove Part", "Remove selected part from product list?")) {
            Boolean res = this.selectedProduct.deleteAssociatedPart(productsTable.getSelectionModel().getSelectedItem());
            if (res == false) {
                messageModal.display("Something went wrong", "Unable to remove selected part");
            }
        }
    }

    /**
     * @param selectedProduct is set as the selected product and all input fields are populated
     */
    private void setProduct(Product selectedProduct) {
        idInput.setText(Integer.toString(selectedProduct.getId()));
        nameInput.setText(selectedProduct.getName());
        invInput.setText(Integer.toString(selectedProduct.getStock()));
        minInput.setText(Integer.toString(selectedProduct.getMin()));
        maxInput.setText(Integer.toString(selectedProduct.getMax()));
        priceInput.setText(Double.toString(selectedProduct.getPrice()));
        getAllParts();
        getAllProductParts(selectedProduct);
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
     * gets and sets all associated parts to selected product
     */
    private void getAllProductParts(Product selectedProduct){
        prodIdCol.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        prodInvCol.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getStock()).asObject());
        prodNameCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        prodPriceCol.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getPrice()).asObject());
        productsTable.setItems(selectedProduct.getAllAssociatedParts());
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
     * Calls to set product
     */
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Product selectedProduct = MainController.getSelectedProduct();
        this.setProduct(selectedProduct);
        this.selectedProduct = selectedProduct;
    }
}
