package ca.sheridancollege.beans;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class Car implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	private int id;
	private String make;
	private String model;
	private String colour;
	private double price;
	private long vin;
	private String dealership;
	
	private String[] dealerList = {"Dealership One", "Dealership Two", "Dealership Three"};

	public Car(String make, String model, String colour, double price, long vin) {

		this.make = make;
		this.model = model;
		this.colour = colour;
		this.price = price;
		this.vin = vin;

	}
	
	
	
	
}
