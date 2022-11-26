package application;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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

public class AddFoodController {
	private FoodOnWheels controller;
	public FoodOnWheels getController() {
		return controller;
	}
	public void setController(FoodOnWheels controller) {
		this.controller = controller;
	}
	@FXML
	private TextField des;
	@FXML
	private TextField name;
	@FXML
	private TextField price;
	@FXML
	private TextField quant;
	@FXML
	private Label nameLabel;
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
	 public void addFood(ActionEvent event) throws IOException, SQLException {
		 boolean isValid= true;
		 String name= this.name.getText();
		 String price= this.price.getText();
		 String qua= this.quant.getText();
		 String des= this.des.getText();
		 System.out.println(name);
		 if(!(name.equals("") || price.equals("") || qua.equals("") || des.equals(""))) {
			 if(name.contains("0")||name.contains("1")||name.contains("2")||name.contains("3")||name.contains("4")||name.contains("5")||name.contains("6")||name.contains("7")||name.contains("8")||name.contains("9")) {
					this.nameLabel.setText("Name should not contain any number");
					isValid= false;
			 }
			 
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
				boolean flag= this.controller.addFood(name, pri, quant, des);
				if(flag) {
					this.error.setText("Food has been added successfully");
				}
				else {
					this.error.setText("Food could not be added (A food has already been added of this Name!)");
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
