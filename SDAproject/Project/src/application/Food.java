package application;

public class Food {

	private String name;
	private int quantity;
	private FoodDescription fdes;
	
	public Food(String name, int quantity) {
		this.name= name;
		this.quantity= quantity;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public FoodDescription getFdes() {
		return fdes;
	}
	public void setFdes(FoodDescription fdes) {
		this.fdes = fdes;
	}
	public FoodDescription makeFoodDes(String name, double price, String detaileddes) {
		this.fdes= new FoodDescription(name, price, detaileddes);
		return fdes;
	}
	public FoodDescription setFoodPrice(String name, double price) {
		return this.fdes = new FoodDescription(name,price);
	}
	public void update(int quantity) {
		this.quantity= quantity;
	}
	public double getsubtotal(){
		double subtotal= getFdes().getPrice()*getQuantity();
		return subtotal;
	}
	
}
