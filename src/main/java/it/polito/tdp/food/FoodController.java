/**
 * Sample Skeleton for 'Food.fxml' Controller Class
 */

package it.polito.tdp.food;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;

import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.food.model.Model;
import it.polito.tdp.food.model.Portion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FoodController {
	
	private Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtCalorie"
    private TextField txtCalorie; // Value injected by FXMLLoader

    @FXML // fx:id="txtPassi"
    private TextField txtPassi; // Value injected by FXMLLoader

    @FXML // fx:id="btnAnalisi"
    private Button btnAnalisi; // Value injected by FXMLLoader

    @FXML // fx:id="btnCorrelate"
    private Button btnCorrelate; // Value injected by FXMLLoader

    @FXML // fx:id="btnCammino"
    private Button btnCammino; // Value injected by FXMLLoader

    @FXML // fx:id="boxPorzioni"
    private ComboBox<Portion> boxPorzioni; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doCammino(ActionEvent event) {
    	txtResult.clear();
    	txtResult.appendText("Cerco cammino peso massimo...");
    }
    
    SimpleWeightedGraph<Portion, DefaultWeightedEdge> grafo;

    @FXML
    void doCorrelate(ActionEvent event) {
    	txtResult.clear();
    	txtResult.appendText("Cerco porzioni correlate...\n");
    	Portion porzione=this.boxPorzioni.getValue();
    	Portion porz;
    	for (DefaultWeightedEdge arco :this.model.cercaVicini(porzione)) {
    		if(!grafo.getEdgeSource(arco).equals(porzione)) {
    			porz=grafo.getEdgeSource(arco);
    		}
    		else
    			porz=grafo.getEdgeTarget(arco);
    		this.txtResult.appendText(porz+" con peso "+grafo.getEdgeWeight(arco)+"\n");
    	}
    	this.txtResult.appendText("FINE!\n");
    }

    @FXML
    void doCreaGrafo(ActionEvent event) {
    	txtResult.clear();
    	this.boxPorzioni.getItems().clear();
    	txtResult.appendText("Creazione grafo...");
    	if(!this.isNumeric(this.txtCalorie.getText())) {
    		this.txtResult.setText("DEVI INSERIRE UN NUMERO INTERO!");
    		return;
    	}
    	int calorie=Integer.parseInt(this.txtCalorie.getText());
    	this.boxPorzioni.getItems().addAll(model.creaGrafo(calorie));
    	grafo=model.getGrafo();
    	this.txtResult.setText("Creato grafo con "+grafo.vertexSet().size()+" vertici e "+grafo.edgeSet().size()+" archi" );
    	
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtCalorie != null : "fx:id=\"txtCalorie\" was not injected: check your FXML file 'Food.fxml'.";
        assert txtPassi != null : "fx:id=\"txtPassi\" was not injected: check your FXML file 'Food.fxml'.";
        assert btnAnalisi != null : "fx:id=\"btnAnalisi\" was not injected: check your FXML file 'Food.fxml'.";
        assert btnCorrelate != null : "fx:id=\"btnCorrelate\" was not injected: check your FXML file 'Food.fxml'.";
        assert btnCammino != null : "fx:id=\"btnCammino\" was not injected: check your FXML file 'Food.fxml'.";
        assert boxPorzioni != null : "fx:id=\"boxPorzioni\" was not injected: check your FXML file 'Food.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Food.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    }
    
    public static boolean isNumeric(String str) { 
  	  try {  
  	    Integer.parseInt(str);  
  	    return true;
  	  } catch(NumberFormatException e){  
  	    return false;  
  	  }  
  	}
}
