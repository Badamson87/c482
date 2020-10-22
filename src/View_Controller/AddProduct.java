package View_Controller;

import Model.Inventory;
import Model.Part;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
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

    public void searchParts(){
    // todo
    }

    public void addSelectedPart(){
        //todo
    }

    public void removeSelectedPart(){
        //todo
    }

    public void save(){
        //todo
    }

    public void cancel(){
        //todo
    }

    private void getAllParts() {
        partIdCol.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        partInvCol.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getStock()).asObject());
        partNameCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        partPriceCol.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getPrice()).asObject());
        partsTable.setItems(Inventory.getAllParts());
    }

    /**
     * Initialize add products by calling get all parts;
     */    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getAllParts();
    }
}


