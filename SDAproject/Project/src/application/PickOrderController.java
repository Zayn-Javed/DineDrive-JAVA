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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class PickOrderController {

	private FoodOnWheels controller;
	public FoodOnWheels getController() {
		return controller;
	}
	public void setController(FoodOnWheels controller) {
		this.controller = controller;
	}
	
	//button
		@FXML
		private Button Display,Delete;
		
		
		//text field
		@FXML
		private TextField orderid;
		
		//text area
		@FXML
		private TextArea Displayorderarea;
		
		//labels
		@FXML
		private Label idLabel,errorLabel;
		
		// display order method	
		@FXML
		public void DisplayOrder(ActionEvent event) throws IOException {
			
			ArrayList<Order> list = this.controller.DisplayOrders();
			
			for( int i=0; i<list.size(); i++ ) {
				
				this.Displayorderarea.setText( Displayorderarea.getText()+ "\norderId: "+list.get(i).getOrderid()+" ,   Location: "+list.get(i).getOrder_Address()+"\n" );
				
			}
			
		}
	
		@FXML
		public void SelectOrder(ActionEvent event) throws IOException {
			
			boolean isValid= true;
			 
			 if(!( this.orderid.getText().equals("") )) {
				 // for valid case
				 int id= Integer.parseInt(this.orderid.getText());
				 
				 if(isValid) {
					 boolean flag= this.controller.selectorder(id);
					 if(flag) {
						 this.errorLabel.setText("order has been selected successfully");
					 }
					 else {
						 this.errorLabel.setText("order has not been selected");
					 }
					}
			 }
			 else {
				 this.idLabel.setText("Field should not be empty");
				
			 }
				
		}
		
		
		
		
		@FXML
		 public void back(ActionEvent event) throws IOException {
			 	Stage currentStage=(Stage)((Node)event.getSource()).getScene().getWindow();
				String fxmlDocPath = "src/application/RiderMenu.fxml";
				FileInputStream fxmlStream = new FileInputStream(fxmlDocPath);
				 FXMLLoader loader = new FXMLLoader();
				AnchorPane pane = (AnchorPane) loader.load(fxmlStream);
				RiderMenuController obj= loader.getController();
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
