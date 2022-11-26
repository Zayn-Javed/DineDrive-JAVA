package application;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class AddRiderController {
	
	private FoodOnWheels controller;
	public FoodOnWheels getController() {
		return controller;
	}
	public void setController(FoodOnWheels controller) {
		this.controller = controller;
	}
	
	//text fields
	
	@FXML
	private TextField ridername;
	@FXML
	private TextField riderusername;
	@FXML
	private TextField rideremail;
	@FXML
	private TextField ridercontact;
	
	//labels
	
	@FXML
	private Label nameLabel;
	@FXML
	private Label contactLabel;
	@FXML
	private Label usernameLabel;
	@FXML
	private Label emailLabel;
	@FXML
	private Label errorLabel;
	
// add rider method	
	@FXML
	public void AddRider(ActionEvent event) throws IOException {
	 boolean isValid= true;
	 String name= this.ridername.getText();
	 String username= this.riderusername.getText();
	 String email= this.rideremail.getText();
	 String contact= this.ridercontact.getText();
	 
	 if(!(name.equals("") || username.equals("") || email.equals("") || contact.equals(""))) {
		 if(name.contains("0")||name.contains("1")||name.contains("2")||name.contains("3")||name.contains("4")||name.contains("5")||name.contains("6")||name.contains("7")||name.contains("8")||name.contains("9")) {
				this.nameLabel.setText("Name should not contain any number");
				isValid= false;
		 }
		 
		 if(email.contains(" ")) {
				this.emailLabel.setText("Email should not contain any spaces");
				isValid= false;
		 }
		 if(contact.contains(" ")) {
				this.contactLabel.setText("Contact should not contain any spaces");
				isValid= false;
		 }
		 
		 
		 // for valid case
		 
		 if(isValid) {

			 boolean flag= this.controller.AddRider(username, email, contact, name);
			 if(flag) {
				 this.errorLabel.setText("rider has been added successfully");
			 }
			 else {
				 this.errorLabel.setText("Rider could not be added (Username or Email is already taken)");
			 }
			 
			}
	 }
	 else {
		 this.errorLabel.setText("Field should not be empty");
		
	 }

   }  // end of addrider method
	 
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
