package application;

public class FoodDescription {
	private String name;
	private double price;
	private String detaildes;
	
	public FoodDescription(String name, double price, String detaileddes) {
		this.name= name;
		this.price= price;
		this.detaildes= detaileddes;
	}
	public FoodDescription(String name2, double price2) {
		this.name= name2;
		this.price= price2;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getDetaildes() {
		return detaildes;
	}
	public void setDetaildes(String detaildes) {
		this.detaildes = detaildes;
	}
	public void update(double price, String detaileddes) {
		this.price= price;
		this.detaildes= detaileddes;
	}
}
