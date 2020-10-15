package View_Controller;

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

    public void initialize(URL url, ResourceBundle resourceBundle) {
        inHouseView();
    }

}
