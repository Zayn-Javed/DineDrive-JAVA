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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class UpdateFoodController {
	private FoodOnWheels controller;
	public FoodOnWheels getController() {
		return controller;
	}
	public void setController(FoodOnWheels controller) {
		this.controller = controller;
	}
	@FXML
	private ChoiceBox<String> myChoiceBox = new ChoiceBox<String>();
	@FXML
	private TextField des;
	@FXML
	private TextField price;
	@FXML
	private TextField quant;
	@FXML
	private Label priceLabel;
	@FXML
	private Label quantLabel;
	@FXML
	private Label desLabel;
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
	 public void show(ActionEvent event) throws IOException, SQLException {
		 ArrayList<String> list= controller.showMenu();
		 this.myChoiceBox.getItems().addAll(list);
		 
	 }
	 @FXML
	 public void updateFood(ActionEvent event) throws IOException, SQLException {
		 boolean isValid= true;
		 String name= this.myChoiceBox.getValue();
		 String price= this.price.getText();
		 String qua= this.quant.getText();
		 String des= this.des.getText();
		 
		 if(!( price.equals("") || qua.equals("") || des.equals(""))) {
			 
			 double pri=0;
			 if(price.contains(" ")) {
				this.priceLabel.setText("Price should not contain any spaces");
				isValid= false;
			 }
			 else {
				 try {
					 pri= Double.parseDouble(price);
				 }
				 catch(NumberFormatException e) {
					 priceLabel.setText("Price should not contain any alphabet");
					 isValid= false;
				 }
			 }
				 
				 
			 int quant=0;
			 if(qua.contains(" ")) {
				 this.quantLabel.setText("Quantity should not contain any spaces");
			 	isValid= false;
			 }
			 else {
				 try {
					 quant= Integer.parseInt(qua);
				 }
			 	 catch(NumberFormatException e) {
			 		 priceLabel.setText("Quantity should not contain any alphabet");
			 		 isValid= false;
			 	 }
			  }
				 
			 
			 
			 
			 if(isValid) {
				 if(name!=null) {
					 controller.updateFood(name, pri, quant, des);
					 this.error.setText("Food has been updated successfully");
				 }
				 else {
					 this.error.setText("Please select the food to be updated");
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
			String fxmlDocPath = "src/application/RestaurantManagerMenu.fxml";
			FileInputStream fxmlStream = new FileInputStream(fxmlDocPath);
			 FXMLLoader loader = new FXMLLoader();
			AnchorPane pane = (AnchorPane) loader.load(fxmlStream);
			RestaurantManagerMenuController obj= loader.getController();
			obj.setController(controller);
			Scene scene = new Scene(pane,600,600);
			currentStage.setScene(scene);
			
			currentStage.show();
	 }
	 @FXML
	 public void toAdminLogin(ActionEvent event) throws IOException {
		 
		 FXMLLoader loader = new FXMLLoader();
		Stage currentStage=(Stage)((Node)event.getSource()).getScene().getWindow();
		String fxmlDocPath = "src/application/adminLogin.fxml";
		FileInputStream fxmlStream = new FileInputStream(fxmlDocPath);
		AnchorPane pane = (AnchorPane) loader.load(fxmlStream);
		adminLoginController obj= loader.getController();
		obj.setController(controller);
		Scene scene = new Scene(pane,600,600);
		currentStage.setScene(scene);
		currentStage.show();
		
		
		 
	 }
}
