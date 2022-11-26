package application;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class DeleteRiderController {

	private FoodOnWheels controller;
	public FoodOnWheels getController() {
		return controller;
	}
	public void setController(FoodOnWheels controller) {
		this.controller = controller;
	}
	
	//text fields
	@FXML
	private TextField riderusername;
	
	@FXML
	private TextArea Displayriderarea;

	//labels
	@FXML
	private Label usernameLabel,errorLabel;
	
	//button
	@FXML
	public Button Display,Delete;

	
	// display order method	
	@FXML
	public void DisplayRider(ActionEvent event) throws IOException, SQLException {
		
		ArrayList<Rider> list = this.controller.DisplayRiders();
		
		for( int i=0; i<list.size(); i++ ) {
			
			this.Displayriderarea.setText( "Name: "+list.get(i).getName()+", Username: "+list.get(i).getUsername()+", Contact: "+list.get(i).getContact()+"\n" );
			
		}
		
		
		
	}
	
	
	// delete rider method	
		@FXML
		public void RemoveRider(ActionEvent event) throws IOException, SQLException {
		 boolean isValid= true;
		 String username= this.riderusername.getText();
		 
		 
		 if(! riderusername.equals("") ) {
			 // for valid case
			 
			 if(isValid) {
				 boolean flag= this.controller.RemoveRiders(username);
				 if(flag) {
					 this.errorLabel.setText("rider has been removed successfully");
				 }
				 else {
					 this.errorLabel.setText("Rider could not be removed");
				 }
				}
		 }
		 else {
			 this.errorLabel.setText("Field should not be empty");
			
		 }
		 
	 } // end of method		 
		
		@FXML
		 public void back(ActionEvent event) throws IOException {
			 	Stage currentStage=(Stage)((Node)event.getSource()).getScene().getWindow();
				String fxmlDocPath = "src/application/adminMenu.fxml";
				FileInputStream fxmlStream = new FileInputStream(fxmlDocPath);
				 FXMLLoader loader = new FXMLLoader();
				AnchorPane pane = (AnchorPane) loader.load(fxmlStream);
				adminMenuController obj= loader.getController();
				obj.setController(controller);
				Scene scene = new Scene(pane,600,600);
				currentStage.setScene(scene);
				
				currentStage.show();
		 }	
		
}
