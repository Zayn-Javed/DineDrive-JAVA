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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class RiderMenuController {

	
	private FoodOnWheels controller;
	private FoodOnWheels controller1;
	public FoodOnWheels getController() {
		return controller;
	}
	public void setController(FoodOnWheels controller) {
		this.controller = controller;
	}
	
	
	public void PickOrder(ActionEvent event) throws IOException {

		 Stage currentStage=(Stage)((Node)event.getSource()).getScene().getWindow();
		 String fxmlDocPath = "src/application/PickOrder.fxml";
		 FileInputStream fxmlStream = new FileInputStream(fxmlDocPath);
		 FXMLLoader loader = new FXMLLoader();
		 AnchorPane pane = (AnchorPane) loader.load(fxmlStream);
		 PickOrderController obj= loader.getController();
		 obj.setController(controller); 
		 Scene scene = new Scene(pane,600,600);
		 currentStage.setScene(scene);
		 currentStage.show();
		
		
		
		 
	 }
	
	
	@FXML
	public void DeliverOrder(ActionEvent event) throws IOException {
		
		Stage currentStage=(Stage)((Node)event.getSource()).getScene().getWindow();
		 String fxmlDocPath = "src/application/DeliverOrder.fxml";
		 FileInputStream fxmlStream = new FileInputStream(fxmlDocPath);
		 FXMLLoader loader = new FXMLLoader();
		 AnchorPane pane = (AnchorPane) loader.load(fxmlStream);
		 DeliverOrderController obj= loader.getController();
		 obj.setController(controller); 
		 Scene scene = new Scene(pane,600,600);
		 currentStage.setScene(scene);
		 currentStage.show();
		
	}
	
	
	@FXML
	
public void report(ActionEvent event) throws IOException, SQLException {
		
		Stage currentStage=(Stage)((Node)event.getSource()).getScene().getWindow();
		 String fxmlDocPath = "src/application/ReportCustomer.fxml";
		 FileInputStream fxmlStream = new FileInputStream(fxmlDocPath);
		 FXMLLoader loader = new FXMLLoader();
		 AnchorPane pane = (AnchorPane) loader.load(fxmlStream);
		ReportCustomerController   obj= loader.getController();
		 obj.setController(controller); 
		 Scene scene = new Scene(pane,600,600);
		 currentStage.setScene(scene);
		 currentStage.show();
		 obj.show(event);
		
	}
	
	@FXML
	
	public void feedback(ActionEvent event) throws IOException, SQLException {
			
			Stage currentStage=(Stage)((Node)event.getSource()).getScene().getWindow();
			 String fxmlDocPath = "src/application/showFeedback.fxml";
			 FileInputStream fxmlStream = new FileInputStream(fxmlDocPath);
			 FXMLLoader loader = new FXMLLoader();
			 AnchorPane pane = (AnchorPane) loader.load(fxmlStream);
			feedbackController   obj= loader.getController();
			 obj.setController(controller); 
			 Scene scene = new Scene(pane,600,600);
			 currentStage.setScene(scene);
			 currentStage.show();
			 obj.show1(event);
			
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
	 
	 
	 public void CustLogin(ActionEvent event) throws IOException {
		 	
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
	
	
	
}
