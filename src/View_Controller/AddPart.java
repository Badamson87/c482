package View_Controller;

import Model.InHouse;
import Model.Inventory;
import Model.Outsourced;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class AddPart implements Initializable {
    @FXML
    Label machineTag;
    @FXML
    Label companyNameTag;
    @FXML
    private TextField machineInput;
    @FXML
    private TextField companyNameInput;
    @FXML
    private TextField nameInput;
    @FXML
    private TextField invInput;
    @FXML
    private TextField priceInput;
    @FXML
    private TextField maxInput;
    @FXML
    private TextField minInput;
    private Boolean inHouse;



    /**
     * Sets machine tag and input visible for In-house view
     * Hides companyName tag and input for In-house view
     */
    public void inHouseView () {
        machineTag.setVisible(true);
        machineInput.setVisible(true);
        companyNameInput.setVisible(false);
        companyNameTag.setVisible(false);
        this.inHouse = true;
    }

    /**
     * Sets company Name tag and input visible for Outsource view
     * Hides machine tag and input for Outsource view
     */
    public void outSourceView () {
        machineTag.setVisible(false);
        machineInput.setVisible(false);
        companyNameInput.setVisible(true);
        companyNameTag.setVisible(true);
        this.inHouse = false;
    }

    /**
     * determines if the new product is inHouse or outSourced
     */
    public void save(){
        String name = nameInput.getText();
        Integer stock = Integer.parseInt(invInput.getText());
        Double price = Double.parseDouble(priceInput.getText());
        Integer max = Integer.parseInt(maxInput.getText());
        Integer min = Integer.parseInt(minInput.getText());
        if (this.inHouse) {
            createNewInHousePart(name, stock, price, max, min);
        } else {
            createNewOutsourcePart(name, stock, price, max, min);
        }
    }

    /**
     * Creates a new InHouse part
     */
    public void createNewInHousePart(String name, Integer stock, Double price, Integer max, Integer min){
        Integer machineId = Integer.parseInt(machineInput.getText());
        InHouse inhouse = new InHouse(MainController.partCounter, name, price, stock, min, max, machineId);
        Inventory.addPart(inhouse);
        MainController.partCounter++;
        close();
    }

    /**
     * creates a new out sourced part
     */
    public void createNewOutsourcePart(String name, Integer stock, Double price, Integer max, Integer min){
        String companyName = companyNameInput.getText();
        Outsourced outsourced = new Outsourced(MainController.partCounter, name, price, stock, min, max, companyName);
        Inventory.addPart(outsourced);
        MainController.partCounter++;
        close();
    }

    public void close(){
        View_Controller.MainController.closeAddPartStage();
    }

    /**
     * Sets InHouse view on initialize of add part pane
     */
    public void initialize(URL url, ResourceBundle resourceBundle) {
        inHouseView();
    }

}
