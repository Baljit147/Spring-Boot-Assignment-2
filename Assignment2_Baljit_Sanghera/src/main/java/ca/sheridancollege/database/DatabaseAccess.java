package ca.sheridancollege.database;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.beans.Car;

@Repository
public class DatabaseAccess {
	
	@Autowired
	protected NamedParameterJdbcTemplate jdbc;

	public void addCar(Car car) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "INSERT INTO cars (make, model, colour, price, vin, dealership) VALUES (:make, :model, :colour, :price, :vin, :dealership)";
		
		parameters.addValue("make", car.getMake());
		parameters.addValue("model", car.getModel());
		parameters.addValue("colour", car.getColour());
		parameters.addValue("price", car.getPrice());
		parameters.addValue("vin", car.getVin());
		parameters.addValue("dealership", car.getDealership());
		
		jdbc.update(query, parameters);
	}
	
	public Car getCarsbyDealership(String dealership) {
		ArrayList<Car> cars = new ArrayList<Car>();
		String query = "SELECT * FROM cars WHERE dealership=:dealership";
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("dealership", dealership);
		
		List<Map<String, Object>> rows = jdbc.queryForList(query, parameters);
		
		for(Map<String,Object>row: rows) {
			Car c = new Car();
			c.setId((Integer)row.get("id"));
			c.setMake((String)row.get("make"));
			c.setModel((String)row.get("model"));
			c.setColour((String)row.get("colour"));
			c.setPrice((double)row.get("price"));
			c.setVin((int)row.get("vin"));
			c.setDealership((String)row.get("dealership"));
			cars.add(c);
		}
		System.out.println(rows.size());
		
		if (cars.size() > 0)
			return cars.get(0);
		return null;
	}
	
	public Car getCarById(int id) {
		ArrayList<Car> cars = new ArrayList<Car>();
		String query = "SELECT * FROM cars WHERE id=:id";
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("id", id);
		
		List<Map<String, Object>> rows = jdbc.queryForList(query, parameters);
		
		for(Map<String,Object>row: rows) {
			Car c = new Car();
			c.setId((Integer)row.get("id"));
			c.setMake((String)row.get("make"));
			c.setModel((String)row.get("model"));
			c.setColour((String)row.get("colour"));
			c.setPrice((double)row.get("price"));
			c.setVin((int)row.get("vin"));
			c.setDealership((String)row.get("dealership"));
			cars.add(c);
		}
		if (cars.size() > 0)
			return cars.get(0);
		return null;
	}
	
	public void editCar(Car car) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "UPDATE cars set make=:make, model=:model, colour=:colour, price=:price, vin=:vin, dealership=:dealership WHERE id=:id";
		
		parameters.addValue("id", car.getId());
		parameters.addValue("make", car.getMake());
		parameters.addValue("model", car.getModel());
		parameters.addValue("colour", car.getColour());
		parameters.addValue("price", car.getPrice());
		parameters.addValue("vin", car.getVin());
		parameters.addValue("dealership", car.getDealership());
		
		jdbc.update(query, parameters);
	}
	
	public void deleteCar(int id) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "DELETE FROM cars WHERE id=:id";
		parameters.addValue("id", id);
		
		jdbc.update(query, parameters);
	}
	
	public ArrayList<Car> getCars(String dealership) {
		String query = "SELECT * FROM cars WHERE dealership='" + dealership + "'";
		ArrayList<Car> cars = (ArrayList<Car>) jdbc.query(query, new 
				BeanPropertyRowMapper<Car>(Car.class));
		
		return cars;
	}
	
	public ArrayList<Car> searchCarByID(Car car) {
		int idNumber = car.getId();
		System.out.println(car.getId());
		String id = String.valueOf(idNumber);
		
		String query = "SELECT * FROM cars WHERE id='" + id + "'";
		ArrayList<Car> cars = (ArrayList<Car>) jdbc.query(query, new 
				BeanPropertyRowMapper<Car>(Car.class));
		
		return cars;
	}
	
	public ArrayList<Car> searchCarByMake(Car car) {
		String searchMake = car.getMake();
		
		String query = "SELECT * FROM cars WHERE make='" + searchMake + "'";
		ArrayList<Car> cars = (ArrayList<Car>) jdbc.query(query, new 
				BeanPropertyRowMapper<Car>(Car.class));
		
		return cars;
	}
	
	public ArrayList<Car> searchCarByModel(Car car) {
	String searchModel = car.getModel();
	
	String query = "SELECT * FROM cars WHERE model='" + searchModel + "'";
	ArrayList<Car> cars = (ArrayList<Car>) jdbc.query(query, new 
			BeanPropertyRowMapper<Car>(Car.class));
	
	return cars;
	}
	
	public ArrayList<Car> searchCarByPrice(Double lowerNumber, Double higherNumber) {
		
		ArrayList<Car> cars = new ArrayList<Car>();
		String query = "SELECT * FROM cars WHERE price BETWEEN :lowerNumber AND :higherNumber";
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("lowerNumber", lowerNumber);
		parameters.addValue("higherNumber", higherNumber);
		
		List<Map<String, Object>> rows = jdbc.queryForList(query, parameters);
		
		for(Map<String,Object>row: rows) {
			Car c = new Car();
			c.setId((Integer)row.get("id"));
			c.setMake((String)row.get("make"));
			c.setModel((String)row.get("model"));
			c.setColour((String)row.get("colour"));
			c.setPrice((double)row.get("price"));
			c.setVin((int)row.get("vin"));
			c.setDealership((String)row.get("dealership"));
			cars.add(c);
		}
		System.out.println(rows.size());
		
		if (cars.size() > 0)
			return cars;
		return null;
	}
	
//	public Car purchaseCar(int id) {
//		ArrayList<Car> cars = new ArrayList<Car>();
//		String query = "Select * FROM cars WHERE id=:id";
//		MapSqlParameterSource parameters = new MapSqlParameterSource();
//		parameters.addValue("id", id);
//		
//		List<Map<String, Object>> rows = jdbc.queryForList(query, parameters);
//		
//		for(Map<String,Object>row: rows) {
//			Car c = new Car();
//			c.setId((Integer)row.get("id"));
//			c.setMake((String)row.get("make"));
//			c.setModel((String)row.get("model"));
//			c.setColour((String)row.get("colour"));
//			c.setPrice((double)row.get("price"));
//			c.setVin((int)row.get("vin"));
//			c.setDealership((String)row.get("dealership"));
//			cars.add(c);
//		}
//		if (cars.size() > 0)
//			return cars.get(0);
//		return null;
//	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
