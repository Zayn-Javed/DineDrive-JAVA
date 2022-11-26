package application;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class OrderDetailController {
	private FoodOnWheels controller;

	public void setController(FoodOnWheels controller) {
	this.controller=controller;
		
	}
private ArrayList<String> foodname = new ArrayList<String>();
private ArrayList<Integer> quantity = new ArrayList<Integer>();

private ArrayList<Double> Price = new ArrayList<Double>();
	public FoodOnWheels getController() {
		return controller;
	}

	public void foodList(ArrayList<String> foodName, ArrayList<Integer> Quantity,ArrayList<Double> price) {
		foodname.addAll(foodName);
		quantity.addAll(Quantity);
		Price.addAll(price);
	}
	
	@FXML
	private TextArea Desc;
	@FXML
	private TextField Address;
	
	@FXML
	private Label label;
	
	@FXML
	
	public void proceed(ActionEvent event) throws IOException, SQLException{
		String desc=Desc.getText();
		String address=Address.getText();
		Boolean flag=false;
		
		if(address!=null) {
		flag=	this.controller.PlaceOrder(this.controller.custlogedin,address,desc, foodname, quantity,Price);
		}
		else {
			this.label.setText("Address cannot be null");
		}
		
	if(flag) {
	
		FXMLLoader loader = new FXMLLoader();
		Stage currentStage=(Stage)((Node)event.getSource()).getScene().getWindow();
		String fxmlDocPath = "src/application/displayBill.fxml";
		FileInputStream fxmlStream = new FileInputStream(fxmlDocPath);
		AnchorPane pane = (AnchorPane) loader.load(fxmlStream);
		DisplayBill obj= loader.getController();
		obj.setController(controller);
		Scene scene = new Scene(pane,600,600);
		currentStage.setScene(scene);
		currentStage.show();
		obj.show(event);
		
	}else {
		this.label.setText("oops!your order is not placed");
	}

	
	}
	
	 @FXML
		public void back(ActionEvent event) throws IOException {
	 	
	 FXMLLoader loader = new FXMLLoader();
		Stage currentStage=(Stage)((Node)event.getSource()).getScene().getWindow();
		String fxmlDocPath = "src/application/showFoodList.fxml";
		FileInputStream fxmlStream = new FileInputStream(fxmlDocPath);
		AnchorPane pane = (AnchorPane) loader.load(fxmlStream);
		ShowFoodList obj= loader.getController();
		obj.setController(controller);
		Scene scene = new Scene(pane,600,600);
		currentStage.setScene(scene);
		currentStage.show();
		obj.show(event);
		 
}
	
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
	 public void toRiderLogin(ActionEvent event) throws IOException {
		 
		 FXMLLoader loader = new FXMLLoader();
		Stage currentStage=(Stage)((Node)event.getSource()).getScene().getWindow();
		String fxmlDocPath = "src/application/riderLogin.fxml";
		FileInputStream fxmlStream = new FileInputStream(fxmlDocPath);
		AnchorPane pane = (AnchorPane) loader.load(fxmlStream);
		RiderLoginController obj= loader.getController();
		obj.setController(controller);
		Scene scene = new Scene(pane,600,600);
		currentStage.setScene(scene);
		currentStage.show();
		
		
		 
	 }
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

	
	
}
