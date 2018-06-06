/**
 * Sample Skeleton for 'Untitled' Controller Class
 */

package it.polito.tdp.rivers;

import java.net.URL;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.rivers.model.Flow;
import it.polito.tdp.rivers.model.Model;
import it.polito.tdp.rivers.model.River;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class RiversController {
	Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="boxRiver"
    private ComboBox<River> boxRiver; // Value injected by FXMLLoader

    @FXML // fx:id="txtStartDate"
    private TextField txtStartDate; // Value injected by FXMLLoader

    @FXML // fx:id="txtEndDate"
    private TextField txtEndDate; // Value injected by FXMLLoader

    @FXML // fx:id="txtNumMeasurements"
    private TextField txtNumMeasurements; // Value injected by FXMLLoader

    @FXML // fx:id="txtFMed"
    private TextField txtFMed; // Value injected by FXMLLoader

    @FXML // fx:id="txtK"
    private TextField txtK; // Value injected by FXMLLoader

    @FXML // fx:id="btnSimula"
    private Button btnSimula; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void handleSelectionRiver(ActionEvent event) {

    	if(boxRiver.getValue()==null) {
    		this.txtResult.setText("Selezionare un river");
    	}else {
    		
    		River selected = boxRiver.getValue();
    		
    	List<Flow>flows =model.getListOfFlowByRiver(selected);
    		flows.sort(new Comparator<Flow> () {

				@Override
				public int compare(Flow f1, Flow f2) {
					
					return f1.getDay().compareTo(f2.getDay());
				}
    			
    		});
    		
    		if(flows.isEmpty()) {
    			txtResult.setText("Nessuna misurazione effettuata per quel fiume");
    			return;
    		}
    	
    	txtStartDate.setText(flows.get(0).getDay().toString());
    	txtEndDate.setText(flows.get(flows.size()-1).getDay().toString());
    	txtNumMeasurements.setText(""+flows.size());
    	txtFMed.setText(""+model.calcolaMedia(flows));
    	}

    }

    @FXML
    void handleSimula(ActionEvent event) {
    	try{
    		float f_med =Float.parseFloat(this.txtFMed.getText());
    		int k = Integer.parseInt(this.txtK.getText());
    		model.creaSimulazione(f_med,k);
    		
    	}catch(NumberFormatException nfe) {
    		this.txtResult.setText("Calcolo f_med non effettuato");
    	}

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert boxRiver != null : "fx:id=\"boxRiver\" was not injected: check your FXML file 'Untitled'.";
        assert txtStartDate != null : "fx:id=\"txtStartDate\" was not injected: check your FXML file 'Untitled'.";
        assert txtEndDate != null : "fx:id=\"txtEndDate\" was not injected: check your FXML file 'Untitled'.";
        assert txtNumMeasurements != null : "fx:id=\"txtNumMeasurements\" was not injected: check your FXML file 'Untitled'.";
        assert txtFMed != null : "fx:id=\"txtFMed\" was not injected: check your FXML file 'Untitled'.";
        assert txtK != null : "fx:id=\"txtK\" was not injected: check your FXML file 'Untitled'.";
        assert btnSimula != null : "fx:id=\"btnSimula\" was not injected: check your FXML file 'Untitled'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Untitled'.";

    }
    
    public void setModel(Model model) {
    	this.model= model;
    	this.boxRiver.getItems().addAll(model.getAllRivers());
    	
    	
    }
}

