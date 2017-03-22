/**
 * Sample Skeleton for 'SpellChecker.fxml' Controller Class
 */

package it.polito.tdp.spellchecker.controller;

import java.net.URL;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.spellchecker.controller.model.Dictionary;
import it.polito.tdp.spellchecker.controller.model.RichWord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class SpellCheckerController {

	private Dictionary ital = new Dictionary();
	private Dictionary engl = new Dictionary();
	private Dictionary d = new Dictionary();
	private String str;
	private LinkedList<String> input= new LinkedList<String>();
	private List<RichWord> wrong = new LinkedList<RichWord>();
	private String[] divide ;
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="cmbLanguage"
    private ComboBox<String> cmbLanguage; // Value injected by FXMLLoader

    @FXML // fx:id="txtInput"
    private TextArea txtInput; // Value injected by FXMLLoader

    @FXML // fx:id="btnSpellCheck"
    private Button btnSpellCheck; // Value injected by FXMLLoader

    @FXML // fx:id="txtWrong"
    private TextArea txtWrong; // Value injected by FXMLLoader

    @FXML // fx:id="txtErrors"
    private Label txtErrors; // Value injected by FXMLLoader

    @FXML // fx:id="btnClearText"
    private Button btnClearText; // Value injected by FXMLLoader

    @FXML // fx:id="txtSeconds"
    private Label txtSeconds; // Value injected by FXMLLoader

    @FXML
    void doClearText(ActionEvent event) {
    	
        txtWrong.setText("");
        txtErrors.setText("");
        txtInput.setText("");
        txtSeconds.setText("");
    }

    @FXML
    void doSpellCheck(ActionEvent event) {
    	
        txtWrong.setText("");
    	
    	str = txtInput.getText();
    	
    	str = str.replaceAll("[\\p{Punct}]","");
    	
    	str.toLowerCase();
    	
    	divide = str.split(" ");
    	
    	for (String i : divide)	{
    		input.add(i);
    		//System.out.println("'"+i+"'");
    	}
    	
    	long t1 = System.nanoTime();
    	
    	wrong = d.spellCheckText(input);
    	
    	long t2 = System.nanoTime();	
    	
    	int count = 0 ;
    	
    	for (int i = 0 ; i < wrong.size() ; i++){
    		
    		if(!wrong.get(i).isCorrect()){
    			count++; 
    	    	txtWrong.appendText(wrong.get(i).getWord()+"\n");

    		}
    		
    	}
    	
    	
    	txtErrors.setText("The text contains "+count+" errors.");
    	txtSeconds.setText("Spell check completed in "+(t2-t1)/1e09+" seconds.");
    	
    	
    	
    	wrong.clear();
    	input.clear();
    	
    }

    @FXML
    void languageChange(ActionEvent event) {
    	
    	switch(cmbLanguage.getValue()){
    	case "English": d=engl;
    		break;
    	case "Italian": d=ital;
    		break;
    	default:break;
    	}
    	
    }
    
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert cmbLanguage != null : "fx:id=\"cmbLanguage\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert txtInput != null : "fx:id=\"txtInput\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert btnSpellCheck != null : "fx:id=\"btnSpellCheck\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert txtWrong != null : "fx:id=\"txtWrong\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert txtErrors != null : "fx:id=\"txtErrors\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert btnClearText != null : "fx:id=\"btnClearText\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert txtSeconds != null : "fx:id=\"txtSeconds\" was not injected: check your FXML file 'SpellChecker.fxml'.";

        cmbLanguage.getItems().addAll("English","Italian");
        //ricordati di dichiararlo
        if(cmbLanguage.getItems().size() > 0)
        	cmbLanguage.setValue(cmbLanguage.getItems().get(0));
        
        d=engl;
        
        txtSeconds.setText("");
        txtErrors.setText("");
        
        ital.loadDictionary("Italian");
        engl.loadDictionary("English");

    }
}




