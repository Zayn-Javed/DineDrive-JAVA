

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
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
	import javafx.stage.Stage;

	public class DisplayBill {
		private FoodOnWheels controller;
		public FoodOnWheels getController() {
			return controller;
		}
		public void setController(FoodOnWheels controller) {
			this.controller = controller;
		}
		
	

		private ArrayList<String>  foodName= new ArrayList<String>();
		
		@FXML
		ArrayList<TextField> check = new ArrayList<TextField>();
		ArrayList<TextField> quant = new ArrayList<TextField>();
		ArrayList<TextField> Price = new ArrayList<TextField>();
		
		@FXML
		private TextField check1 = new TextField();
		@FXML
		private TextField check2 = new TextField();
		
		@FXML
		private TextField check3 = new TextField();
		
		@FXML
		private TextField check4 = new TextField();
		
		@FXML
		private TextField check5 = new TextField();
		
		@FXML
		private TextField check6 = new TextField();
		
		
		@FXML
		private TextField quant1 = new TextField();
		
		@FXML
		private TextField quant2 = new TextField();

		@FXML
		private TextField quant3 = new TextField();

		@FXML
		private TextField quant4 = new TextField();

		@FXML
		private TextField quant5 = new TextField();

		@FXML
		private TextField  quant6 = new TextField();

		@FXML
		private TextField  price1 = new TextField();
		
		@FXML
		private TextField  price2 = new TextField();
		
		@FXML
		private TextField  price3 = new TextField();
		
		@FXML
		private TextField  price4 = new TextField();
		
		@FXML
		private TextField  price5 = new TextField();
		
		@FXML
		private TextField  price6 = new TextField();
		@FXML
		private TextField  total = new TextField();
		
		@FXML
		private TextField  gst = new TextField();
		
		@FXML
		private TextField  grandTotal = new TextField();
		@FXML
		private Label isNull;
		@FXML
		 // The reference of show button will be injected by the FXML loader
		 public Button goBtn;
		// location and resources will be automatically injected by the FXML loader
		 @FXML
		 public URL location;
		 //=new URL("D:\\SDA Spring 2022\\eclipseWorkplaceForSDA\\DemoApp\\src\\application\\SampleUI.fxml");
		 
		
		 
		 
		 
		 @FXML
		 public ResourceBundle resources;
         @FXML
		private ArrayList<Double> price = new ArrayList<Double>();

		 @FXML
		 public void show(ActionEvent event) throws IOException{
			 Bill bill =this.controller.displayBill();
			 check.add(check1);
			 check.add(check2);
			 check.add(check3);
			 check.add(check4);
			 check.add(check5);
			 check.add(check6);
			 
			 quant.add(quant1);
			 quant.add(quant2);
			 quant.add(quant3);
			 quant.add(quant4);
			 quant.add(quant5);
			 quant.add(quant6);
			 
			 Price.add(price1);
			 Price.add(price2);
			 Price.add(price3);
			 Price.add(price4);
			 Price.add(price5);
			 Price.add(price6);
			 for(int i=0;i<bill.getFoodName().size();i++) {
				
				     check.get(i).setVisible(true);
				     Price.get(i).setVisible(true);
				     
				     Price.get(i).setText(bill.getQuantity().get(i).toString());
				     
				    check.get(i).setText(bill.getFoodName().get(i)); 
				    quant.get(i).setVisible(true);
				    quant.get(i).setText(bill.getSubamount()[i].toString());
		   	 }
			 total.setText(bill.getTotalAmount().toString());
			 gst.setText(bill.calculateGST().toString());
			 grandTotal.setText(bill.getGrandTotal().toString());
		 }

	
		 
		
		 @FXML
		 public void proceed(ActionEvent event) throws IOException {
			
			 
				 FXMLLoader loader = new FXMLLoader();
				 Stage currentStage=(Stage)((Node)event.getSource()).getScene().getWindow();
				 String fxmlDocPath = "src/application/paymentMethod.fxml";
				 FileInputStream fxmlStream = new FileInputStream(fxmlDocPath);
				 AnchorPane pane = (AnchorPane) loader.load(fxmlStream);
				 paymentMethodController obj= loader.getController();
				 obj.setController(controller);
				 Scene scene = new Scene(pane,600,600);
				 currentStage.setScene(scene);
				 currentStage.show();
				obj.set(event);
				 
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
		
		 
	
