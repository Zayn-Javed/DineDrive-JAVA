package application;

import java.util.ArrayList;

public class FoodCatalog {

	private ArrayList<FoodDescription> fdeslist= new ArrayList<FoodDescription>();
	private ArrayList<FoodDescription> remdeslist= new ArrayList<FoodDescription>();
	public ArrayList<FoodDescription> getFdeslist() {
		return fdeslist;
	}
	public void setFdeslist(ArrayList<FoodDescription> fdeslist) {
		this.fdeslist = fdeslist;
	}
	public void add(FoodDescription fdes) {
		this.fdeslist.add(fdes);
	}
	public void removeFoodDes(String name) {
		FoodDescription fdes= null;
		for(int i=0; i<this.fdeslist.size(); i++) {
			if(name.equals(this.fdeslist.get(i).getName())) {
				fdes= this.fdeslist.get(i);
				this.fdeslist.remove(i);
				break;
			}
		}
		this.remdeslist.add(fdes);
	}
	public ArrayList<String> getMenu() {
		ArrayList<String> menu= new ArrayList<String>();
		for(int i=0; i<this.fdeslist.size(); i++) {
			menu.add(this.fdeslist.get(i).getName());
		}
		return menu;
	}
	public void updateFoodDes(String name, double price, String detaileddes) {
		FoodDescription fdes= null;
		for(int i=0; i<this.fdeslist.size(); i++) {
			if(name.equals(this.fdeslist.get(i).getName())) {
				fdes= this.fdeslist.get(i);
				break;
			}
		}
		fdes.update(price, detaileddes);
	}
}
