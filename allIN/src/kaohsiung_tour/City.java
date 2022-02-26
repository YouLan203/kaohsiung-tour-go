package kaohsiung_tour;

public class City {
	String id;
	String name;
	String chin_name;
	double x;
	double y;
	
	public City(String id, String chin_name, String name, double x, double y) {
		this.id = id;
		this.chin_name = chin_name;
		this.name = name;
		this.x = x;
		this.y = y;
	}
}