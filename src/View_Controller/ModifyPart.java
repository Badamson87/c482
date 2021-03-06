package View_Controller;

import Model.InHouse;
import Model.Inventory;
import Model.Outsourced;
import Model.Part;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * This class helps with the modification of an existing part
 */
public class ModifyPart implements Initializable {
    @FXML
    Label machineTag;
    @FXML
    Label companyNameTag;
    @FXML
    TextField machineInput;
    @FXML
    TextField companyNameInput;
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
    @FXML
    private RadioButton inHouseRadio;
    @FXML
    private RadioButton outSourceRadio;
    @FXML
    private TextField idInput;

    private Part selectedPart;
    private Boolean inHouseView;

    /**
     * shows inHouse input and tags, hides the outsource input and tag
     */
    public void inHouseView () {
        machineTag.setVisible(true);
        machineInput.setVisible(true);
        companyNameInput.setVisible(false);
        companyNameTag.setVisible(false);
        inHouseRadio.setSelected(true);
        inHouseView =  true;
    }

    /**
     * shows outsource input and tag, Hides the inHouse input and tag
     */
    public void outSourceView () {
        machineTag.setVisible(false);
        machineInput.setVisible(false);
        companyNameInput.setVisible(true);
        companyNameTag.setVisible(true);
        outSourceRadio.setSelected(true);
        inHouseView = false;
    }

    /**
     * @param selectedPart is set and determines the instance of part
     */
    private void setPart(Part selectedPart){
        if (selectedPart instanceof InHouse){
            inHouseView();
            machineInput.setText(Integer.toString(((InHouse) selectedPart).getMachineId()));
        } else {
            outSourceView();
            companyNameInput.setText(((Outsourced) selectedPart).getCompanyName());
        }
        nameInput.setText(selectedPart.getName());
        invInput.setText(Integer.toString(selectedPart.getStock()));
        minInput.setText(Integer.toString(selectedPart.getMin()));
        maxInput.setText(Integer.toString(selectedPart.getMax()));
        priceInput.setText(Double.toString(selectedPart.getPrice()));
        idInput.setText(Integer.toString(selectedPart.getId()));
    }

    /**
     * Saves updated product after checking that max is greater than min
     */
    public void save(){
        if (Integer.parseInt(maxInput.getText()) > Integer.parseInt(minInput.getText())){
            String name = nameInput.getText();
            Integer stock = Integer.parseInt(invInput.getText());
            Double price = Double.parseDouble(priceInput.getText());
            Integer max = Integer.parseInt(maxInput.getText());
            Integer min = Integer.parseInt(minInput.getText());
            if (inHouseView == true){
                Integer machineId = Integer.parseInt(machineInput.getText());
                InHouse update = new InHouse(selectedPart.getId(), name, price, stock, min, max, machineId);
                Inventory.updatePart(update, selectedPart);
            } else {
                String companyName = companyNameInput.getText();
                Outsourced update = new Outsourced(selectedPart.getId(), name, price, stock, min, max, companyName);
                Inventory.updatePart(update, selectedPart);
            }
            close();
        } else {
            messageModal.display("Unable to update", "Max value must be greater than min");
        }


    }

    /**
     * close modify without update
     */
    public void close(){
        MainController.closeModifyPartStage();
    }

    /**
     * Calls to set part
     */
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Part selectedPart = MainController.getSelectedPart();
            this.setPart(selectedPart);
            this.selectedPart = selectedPart;
    }

}
