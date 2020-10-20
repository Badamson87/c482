package View_Controller;

import Model.Part;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

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

    private Part selectedPart;

    public void inHouseView () {
        machineTag.setVisible(true);
        machineInput.setVisible(true);
        companyNameInput.setVisible(false);
        companyNameTag.setVisible(false);
    }

    public void outSourceView () {
        machineTag.setVisible(false);
        machineInput.setVisible(false);
        companyNameInput.setVisible(true);
        companyNameTag.setVisible(true);
    }

    private void setPart(Part selectedPart){
        nameInput.setText(selectedPart.getName());
        invInput.setText(Integer.toString(selectedPart.getStock()));
        minInput.setText(Integer.toString(selectedPart.getMin()));
        maxInput.setText(Integer.toString(selectedPart.getMax()));
        priceInput.setText(Double.toString(selectedPart.getPrice()));

        // todo get type and set type
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        Part selectedPart = MainController.getSelectedPart();
        if (selectedPart == null) {
            // todo message modal no part selected
        }else {
            this.setPart(selectedPart);
            this.selectedPart = selectedPart;
        }
    }

}
