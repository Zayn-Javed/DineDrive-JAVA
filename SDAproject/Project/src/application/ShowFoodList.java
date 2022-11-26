

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

	public class ShowFoodList {
		private FoodOnWheels controller;
		public FoodOnWheels getController() {
			return controller;
		}
		public void setController(FoodOnWheels controller) {
			this.controller = controller;
		}
		
	

		private ArrayList<String>  foodName= new ArrayList<String>();
		
		@FXML
		ArrayList<CheckBox> check = new ArrayList<CheckBox>();
		ArrayList<TextField> quant = new ArrayList<TextField>();
		ArrayList<TextField> Price = new ArrayList<TextField>();
		
		@FXML
		private CheckBox check1 = new CheckBox();
		@FXML
		private CheckBox check2 = new CheckBox();
		
		@FXML
		private CheckBox check3 = new CheckBox();
		
		@FXML
		private CheckBox check4 = new CheckBox();
		
		@FXML
		private CheckBox check5 = new CheckBox();
		
		@FXML
		private CheckBox check6 = new CheckBox();
		
		
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
		private Label isNull;
		@FXML
		 // The reference of show button will be injected by the FXML loader
		 public Button goBtn;
		// location and resources will be automatically injected by the FXML loader
		 @FXML
		 public URL location;
		 //=new URL("D:\\SDA Spring 2022\\eclipseWorkplaceForSDA\\DemoApp\\src\\application\\SampleUI.fxml");
		 
		
		 
		 boolean flag=true;
		 
		 @FXML
		 public ResourceBundle resources;
         @FXML
		private ArrayList<Double> price = new ArrayList<Double>();
         @FXML
         Restaurant resta=null;
         public void sendResataurant(Restaurant restaurant) {
 			resta=restaurant;
 			
 		}
		 @FXML
		 public void show(ActionEvent event) throws IOException{
			 
			 ArrayList<Food> list= controller.showfoodList(resta);
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
			
		
			 for(int i=0;i<list.size();i++) {
				
				     check.get(i).setVisible(true);
				     Price.get(i).setVisible(true);
				     
				     Price.get(i).setText(list.get(i).getFdes().getPrice().toString());
				     price.add(list.get(i).getFdes().getPrice());
				check.get(i).setText(list.get(i).getName()); 	 
		   	 }
		 }

		 
		 @FXML
		 
		 public void quantity(ActionEvent event) throws IOException{
			 for(int i=0;i<check.size();i++) {
				 
					if(check.get(i).isSelected()) {
						foodName.add(check.get(i).getText());
						
						quant.get(i).setVisible(true);
		
					}
				}
		 }
		 @FXML
		 public void getFoodName(ActionEvent event) throws IOException {
			
			 ArrayList<Integer> quantity = new ArrayList<Integer>();
			 if(check!=null && flag) {
				 for(int i=0;i<check.size();i++) {
					 
						if(check.get(i).isSelected()) {
							int q = Integer.parseInt( quant.get(i).getText());
							quantity.add(q);
						}
					}
				
				 FXMLLoader loader = new FXMLLoader();
				 Stage currentStage=(Stage)((Node)event.getSource()).getScene().getWindow();
				 String fxmlDocPath = "src/application/Orderdetails.fxml";
				 FileInputStream fxmlStream = new FileInputStream(fxmlDocPath);
				 AnchorPane pane = (AnchorPane) loader.load(fxmlStream);
				 OrderDetailController obj= loader.getController();
				 obj.setController(controller);
				 Scene scene = new Scene(pane,600,600);
				 currentStage.setScene(scene);
				 currentStage.show();
				 obj.foodList(foodName,quantity,price);
				 
			 }
			
		 }
		 @FXML
				public void back(ActionEvent event) throws IOException {
			 	
			 FXMLLoader loader = new FXMLLoader();
				Stage currentStage=(Stage)((Node)event.getSource()).getScene().getWindow();
				String fxmlDocPath = "src/application/ShowRestaurants.fxml";
				FileInputStream fxmlStream = new FileInputStream(fxmlDocPath);
				AnchorPane pane = (AnchorPane) loader.load(fxmlStream);
				showRestautrantController obj= loader.getController();
				obj.setController(controller);
				Scene scene = new Scene(pane,600,600);
				currentStage.setScene(scene);
				currentStage.show();
				
				 
		}
		
		 
	}
