/**
 * Sample Skeleton for 'Bike.fxml' Controller Class
 */

package it.polito.tdp.bike;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ResourceBundle;

import it.polito.tdp.bike.bean.Model;
import it.polito.tdp.bike.bean.Station;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class BikeController {
	Model model;
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="boxLandmark"
    private ComboBox<String> boxLandmark; // Value injected by FXMLLoader

    @FXML // fx:id="btnElenca"
    private Button btnElenca; // Value injected by FXMLLoader

    @FXML // fx:id="txtSimulationDate"
    private TextField txtSimulationDate; // Value injected by FXMLLoader

    @FXML // fx:id="btnSimula"
    private Button btnSimula; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doElencoStazioni(ActionEvent event) {
    	String landmark = this.boxLandmark.getValue();
   	 	if(landmark==null){
   		 txtResult.appendText("Errore, selezionare una citta'.");    		 
   		 return;
   	 	}
   	 	for(Station s : model.getElencoStazioni(landmark)){
   	 	 txtResult.appendText(s+" "+s.getTotDurata()+"\n");
   	 	}

    }

    @FXML
    void doSimula(ActionEvent event) {
    	txtResult.clear();
    	LocalDate d = null;
    	try{
    		d = LocalDate.parse(txtSimulationDate.getText());
    	}catch(DateTimeParseException dtpe){
    		txtResult.appendText("Errore. Formato data errato.");
    		return;
    	}
    	txtResult.appendText(model.simula(d));
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert boxLandmark != null : "fx:id=\"boxLandmark\" was not injected: check your FXML file 'Bike.fxml'.";
        assert btnElenca != null : "fx:id=\"btnElenca\" was not injected: check your FXML file 'Bike.fxml'.";
        assert txtSimulationDate != null : "fx:id=\"txtSimulationDate\" was not injected: check your FXML file 'Bike.fxml'.";
        assert btnSimula != null : "fx:id=\"btnSimula\" was not injected: check your FXML file 'Bike.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Bike.fxml'.";

    }

	public void setModel(Model model) {
		this.model = model;
		this.boxLandmark.getItems().clear();
		boxLandmark.getItems().addAll(model.getLandmarks());
		
	}
}
