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

public class CustomerSignUpController{
		
		private FoodOnWheels controller;
		public FoodOnWheels getController() {
			return controller;
		}
		public void setController(FoodOnWheels controller) {
			this.controller = controller;
		}
		
		//text fields
		
		@FXML
		private TextField name;
		@FXML
		private TextField username;
		@FXML
		private TextField Address;
		@FXML
		private PasswordField password;
		@FXML
		private TextField phoneNo;
		
		private Button btn;
		
		 @FXML
		 public URL location;
		 //=new URL("D:\\SDA Spring 2022\\eclipseWorkplaceForSDA\\DemoApp\\src\\application\\SampleUI.fxml");
	     
		 @FXML
		 public ResourceBundle resources;

		// Add a public no-args constructor
		 public void BOXUIController()
		 {
		 }
		 @FXML
		 public void initialize()
		 {
		 }
		//labels
		
		@FXML
		private Label nameLabel;
		@FXML
		private Label phoneNolabel;
		@FXML
		private Label usernameLabel;
		@FXML
		private Label passwordlabel ;
		
		@FXML
		private Label errorLabel;
		

		@FXML
		public void signup(ActionEvent event) throws IOException {
		 boolean isValid= true;
		 String name= this.name.getText();
		 String Address= this.Address.getText();
		 String phoneNo= this.phoneNo.getText();
		 String username= this.username.getText();
		 String password = this.password.getText();
		 
		 if(!(name.equals("") || username.equals("") || phoneNo.equals("") || password.equals("") || Address.equals(""))) {
			 if(name.contains("0")||name.contains("1")||name.contains("2")||name.contains("3")||name.contains("4")||name.contains("5")||name.contains("6")||name.contains("7")||name.contains("8")||name.contains("9")) {
					this.nameLabel.setText("Name should not contain any number");
					isValid= false;
			 }
			 int phone=0;
			 if(phoneNo.contains(" ")) {
				 this.phoneNolabel.setText("Phone Number should not contain any spaces");
			 	isValid= false;
			 }
			 else {
				 try {
					  phone= Integer.parseInt(phoneNo);
				 }
			 	 catch(NumberFormatException e) {
			 		 phoneNolabel.setText("Phone Number should not contain any alphabet");
			 		 isValid= false;
			 	 }
				 
			 }
			 
			 if(username.contains(" ")) {
					this.usernameLabel.setText("Username should not contain any spaces");
					isValid= false;
			 }
			 if(password.length()<5) {
				 	passwordlabel.setText("Password should contain more than 4 words");
					isValid= false;
			 }
			 
			 
			 
			 // for valid case
			 
			 if(isValid) {

				 boolean flag= this.controller.AddCustomer(name,Address,phoneNo,username,password);
				 if(flag) {
					 
					 this.errorLabel.setText("You have been Registered successfully");
					 Login(event);
					 
				 }
				 else {
					 this.errorLabel.setText("Sorry!Username or phone Number is already taken");
				 }
				 
				}
		 }
		 else {
			 this.errorLabel.setText("Field should not be empty");
			
		 }

	   }  
		 
		@FXML
		 
		 public void toSignUp(ActionEvent event) throws IOException {
			 	
			 FXMLLoader loader = new FXMLLoader();
				Stage currentStage=(Stage)((Node)event.getSource()).getScene().getWindow();
				String fxmlDocPath = "src/application/CustomerSignUp.fxml";
				FileInputStream fxmlStream = new FileInputStream(fxmlDocPath);
				AnchorPane pane = (AnchorPane) loader.load(fxmlStream);
				CustomerSignUpController obj= loader.getController();
				obj.setController(controller);
				Scene scene = new Scene(pane,600,600);
				currentStage.setScene(scene);                                      
				currentStage.show();
				
				 
		}
		
		
		public void Login(ActionEvent event) throws IOException {
		 	
			 FXMLLoader loader = new FXMLLoader();
				Stage currentStage=(Stage)((Node)event.getSource()).getScene().getWindow();
				String fxmlDocPath = "src/application/CustomerLogin.fxml";
				FileInputStream fxmlStream = new FileInputStream(fxmlDocPath);
				AnchorPane pane = (AnchorPane) loader.load(fxmlStream);
				CustomerLoginController obj= loader.getController();
				obj.setController(controller);
				Scene scene = new Scene(pane,710,468);
				currentStage.setScene(scene);                                      
				currentStage.show();
				
				 
		}
		
		
		
		     

		     

		
	}


