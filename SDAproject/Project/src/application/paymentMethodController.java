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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class paymentMethodController {
	private FoodOnWheels controller;

	public void setController(FoodOnWheels controller) {
	this.controller=controller;
		
	}


private ArrayList<Double> Price = new ArrayList<Double>();
	public FoodOnWheels getController() {
		return controller;
	}

	
	@FXML
	private ChoiceBox<String> paymentMethod;
	@FXML
	private TextField card;
	
	@FXML
	private Label label;
	
	@FXML
	Boolean flag=false;
	
	public void set(ActionEvent event) throws IOException {
		ArrayList<String> s = this.controller.getPaymentMethod();
		paymentMethod.getItems().add(s.get(0));
		paymentMethod.getItems().add(s.get(1));
		card.setVisible(false);
	}
	
	public void confirm(ActionEvent event) throws IOException, SQLException {
		
		if(paymentMethod.getValue().equalsIgnoreCase("Pay by Cash")) {
		this.controller.Payment();
		}
		else {
			
		card.setVisible(true);
		
		
		}
	}
	public void proceed(ActionEvent event) throws IOException, NumberFormatException, SQLException{

		if(paymentMethod.getValue().equalsIgnoreCase("Pay by Cash")) {
			label.setText("Congratulations!Your Order is placed");
		}
		else {
		String Card=card.getText();
		
	
		flag=this.controller.Payment(Integer.parseInt(Card));
		
       
	       if(flag) {
	    	 
	    	 label.setText("Congratulations!Your Order is placed");	
	     }
	
	
	     }
		
	}	
	 @FXML
		public void back(ActionEvent event) throws IOException {
	 	
	    FXMLLoader loader = new FXMLLoader();
		Stage currentStage=(Stage)((Node)event.getSource()).getScene().getWindow();
		String fxmlDocPath = "src/application/DisplayBill.fxml";
		FileInputStream fxmlStream = new FileInputStream(fxmlDocPath);
		AnchorPane pane = (AnchorPane) loader.load(fxmlStream);
		DisplayBill obj= loader.getController();
		obj.setController(controller);
		Scene scene = new Scene(pane,600,600);
		currentStage.setScene(scene);
		currentStage.show();
		obj.show(event);
		 
}
	 
	 public void Login(ActionEvent event) throws IOException {
		 	
		 FXMLLoader loader = new FXMLLoader();
			Stage currentStage=(Stage)((Node)event.getSource()).getScene().getWindow();
			String fxmlDocPath = "src/application/CustomerLogin.fxml";
			FileInputStream fxmlStream = new FileInputStream(fxmlDocPath);
			AnchorPane pane = (AnchorPane) loader.load(fxmlStream);
			CustomerLoginController obj= loader.getController();
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
	
	
}
