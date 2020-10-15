package View_Controller;

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
    TextField machineInput;
    @FXML
    TextField companyNameInput;


    /**
     * Sets machine tag and input visible for In-house view
     * Hides companyName tag and input for In-house view
     */
    public void inHouseView () {
        machineTag.setVisible(true);
        machineInput.setVisible(true);
        companyNameInput.setVisible(false);
        companyNameTag.setVisible(false);
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
    }

    /**
     * Sets InHouse view on initialize of add part pane
     */
    public void initialize(URL url, ResourceBundle resourceBundle) {
        inHouseView();
    }

}
