package it.polito.tdp.poweroutages;

import java.net.URL;

import java.util.ResourceBundle;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import it.polito.tdp.poweroutages.model.Model;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	private Model model;
	
	public void setModel(Model m) {
    	this.model = m;
    	this.choiceBoxNERC.getItems().addAll(m.getListaNerc());
    //	imgCartina = new ImageView("");
	} 
	
	@FXML
    private ImageView imgCartina;
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ChoiceBox<String> choiceBoxNERC;

    @FXML
    private TextField txtMaxYears;

    @FXML
    private TextField txtMaxHours;

    @FXML
    private Button btnWorstCase;

    @FXML
    private TextArea txtRisultato;

    @FXML
    void initialize() {
    	assert imgCartina != null : "fx:id=\"imgCartina\" was not injected: check your FXML file 'Scene.fxml'.";
    	assert choiceBoxNERC != null : "fx:id=\"choiceBoxNERC\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtMaxYears != null : "fx:id=\"txtMaxYears\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtMaxHours != null : "fx:id=\"txtMaxHours\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnWorstCase != null : "fx:id=\"btnWorstCase\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'Scene.fxml'.";

    }
}
