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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class RestaurantManagerMenuController {
	private FoodOnWheels controller;
	public FoodOnWheels getController() {
		return controller;
	}
	public void setController(FoodOnWheels controller) {
		this.controller = controller;
	}
	@FXML
	private TextField user_name;
	@FXML
	private PasswordField password;
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
	 public void addFood(ActionEvent event) throws IOException {
		 
		 
		 Stage currentStage=(Stage)((Node)event.getSource()).getScene().getWindow();
		 String fxmlDocPath = "src/application/AddFood.fxml";
		 FileInputStream fxmlStream = new FileInputStream(fxmlDocPath);
		 FXMLLoader loader = new FXMLLoader();
		 AnchorPane pane = (AnchorPane) loader.load(fxmlStream);
		 AddFoodController obj= loader.getController();
		 obj.setController(controller);
		 Scene scene = new Scene(pane,600,600);
		 currentStage.setScene(scene);
		 currentStage.show();
		
		
		
		 
	 }
	 
	 public void deleteFood(ActionEvent event) throws IOException {
		 
		 
		 Stage currentStage=(Stage)((Node)event.getSource()).getScene().getWindow();
		 String fxmlDocPath = "src/application/DeleteFood.fxml";
		 FileInputStream fxmlStream = new FileInputStream(fxmlDocPath);
		 FXMLLoader loader = new FXMLLoader();
		 AnchorPane pane = (AnchorPane) loader.load(fxmlStream);
		 DeleteFoodController obj= loader.getController();
		 obj.setController(controller);
		 Scene scene = new Scene(pane,600,600);
		 currentStage.setScene(scene);
		 currentStage.show();
		
	 }
	 
	 public void updateFood(ActionEvent event) throws IOException {
		 Stage currentStage=(Stage)((Node)event.getSource()).getScene().getWindow();
		 String fxmlDocPath = "src/application/UpdateFood.fxml";
		 FileInputStream fxmlStream = new FileInputStream(fxmlDocPath);
		 FXMLLoader loader = new FXMLLoader();
		 AnchorPane pane = (AnchorPane) loader.load(fxmlStream);
		 UpdateFoodController obj= loader.getController();
		 obj.setController(controller);
		 Scene scene = new Scene(pane,600,600);
		 currentStage.setScene(scene);
		 currentStage.show();	
	 }
	 
	 public void logOut(ActionEvent event) throws IOException {
		 Stage currentStage=(Stage)((Node)event.getSource()).getScene().getWindow();
		 String fxmlDocPath = "src/application/ManagerLogin.fxml";
		 FileInputStream fxmlStream = new FileInputStream(fxmlDocPath);
		 FXMLLoader loader = new FXMLLoader();
		 AnchorPane pane = (AnchorPane) loader.load(fxmlStream);
		 ManagerLoginController obj= loader.getController();
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
