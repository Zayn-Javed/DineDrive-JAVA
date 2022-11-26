package application;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class addRestaurantController {
	private FoodOnWheels controller;
	public FoodOnWheels getController() {
		return controller;
	}
	public void setController(FoodOnWheels controller) {
		this.controller = controller;
	}
	@FXML
	private TextField user;
	@FXML
	private PasswordField password;
	@FXML
	private TextField name;
	@FXML
	private TextField add;
	@FXML
	private TextField cat;
	@FXML
	private Label nameLabel;
	@FXML
	private Label addLabel;
	@FXML
	private Label catLabel;
	@FXML
	private Label userLabel;
	@FXML
	private Label passLabel;
	@FXML
	private Label error;
	@FXML
	 // The reference of show button will be injected by the FXML loader
	 public Button goBtn;
	// location and resources will be automatically injected by the FXML loader
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
	 @FXML
	 public void addRestaurant(ActionEvent event) throws IOException {
		 boolean isValid= true;
		 String user= this.user.getText();
		 String pass= this.password.getText();
		 String name= this.name.getText();
		 String cat= this.cat.getText();
		 String add= this.add.getText();
		 
		 if(!(name.equals("") || cat.equals("") || add.equals("") || user.equals("") || pass.equals(""))) {
			 if(cat.contains("0")||cat.contains("1")||cat.contains("2")||cat.contains("3")||cat.contains("4")||cat.contains("5")||cat.contains("6")||cat.contains("7")||cat.contains("8")||cat.contains("9")) {
					this.catLabel.setText("Catagory should not contain any number");
					isValid= false;
			 }
			 if(user.contains(" ")) {
				 	userLabel.setText("User Name should not contain any spaces");
					isValid= false;
			 }
			 if(pass.length()<5) {
				 	passLabel.setText("Password should contain more than 4 words");
					isValid= false;
			 }
			 
			 if(isValid) {

				 boolean flag= this.controller.addRestaurant(user, pass, name, add, cat);
				 if(flag) {
					 this.error.setText("Restaurant has been added successfully");
				 }
				 else {
					 this.error.setText("Restaurant could not be added (User Name or Restaurant Name is already taken)");
				 }
			}
		 }
		 else {
			 this.error.setText("Field should not be empty");
			
		 }
		
		
		 
	 }
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
	 
	 @FXML
	 public void toRestaurentLogin(ActionEvent event) throws IOException {
		 	
		 FXMLLoader loader = new FXMLLoader();
			Stage currentStage=(Stage)((Node)event.getSource()).getScene().getWindow();
			String fxmlDocPath = "src/application/ManagerLogin.fxml";
			FileInputStream fxmlStream = new FileInputStream(fxmlDocPath);
			AnchorPane pane = (AnchorPane) loader.load(fxmlStream);
			ManagerLoginController obj= loader.getController();
			obj.setController(controller);
			Scene scene = new Scene(pane,600,600);
			currentStage.setScene(scene);
			currentStage.show();
			
			 
	}

}
