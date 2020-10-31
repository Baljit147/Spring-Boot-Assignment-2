package ca.sheridancollege.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import ca.sheridancollege.beans.Car;
import ca.sheridancollege.database.DatabaseAccess;

@Controller
public class HomeController {
	@Autowired
	private DatabaseAccess da;
	
//	Home Page
	@GetMapping("/")
	public String goHome() {
		
		return "index.html";
	}
	
//	Load the add car page
	@GetMapping("/add")
	public String add(Model model, @ModelAttribute Car car) {
		model.addAttribute("car", new Car("make", "model", "colour", 0.0, 0));
		return "add.html";
	}

//	Mapping for the submit button, on the  add car page. Add to database.
	@GetMapping("submit")
	public String submit(Model model, @ModelAttribute Car car) {
		da.addCar(car);

		return "add.html";
	}
	
//	View car inventory
	@GetMapping("view")
	public String viewCars(Model model,@ModelAttribute Car car) {
//		model.addAttribute("carList0", da.getCarsbyDealership("Dealership One"));
//		model.addAttribute("carList1", da.getCarsbyDealership("Dealership Two"));
//		model.addAttribute("carList2", da.getCarsbyDealership("Dealership Three"));

		model.addAttribute("carList0", da.getCars("Dealership One"));
		model.addAttribute("carList1", da.getCars("Dealership Two"));
		model.addAttribute("carList2", da.getCars("Dealership Three"));
		return "view.html";
	}
	
	@GetMapping("/edit/{id}")
	public String editCar(Model model, @PathVariable int id) {
		Car car = da.getCarById(id);
		model.addAttribute("car", car);
		return "edit.html";
	}
	
	@GetMapping("/modify")
	public String modify(Model model, @ModelAttribute Car car) {
		da.editCar(car);
		return "redirect:/view";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteDrink(Model model, @PathVariable int id) {
		da.deleteCar(id);
		return "redirect:/view";
	}
	
//	The search page, 
	@GetMapping("/searchPage")
	public String searchPage(Model model, @ModelAttribute Car car) {
//		da.addCar(car);  This line appears to be useless. not sure why I added it.
		model.addAttribute("car", new Car());
		
		return "searchPage.html";
	}
	
	@GetMapping("/searchById")
	public String searchById(Model model, @ModelAttribute Car car) {
//		Testing car.getId(), no longer needed.
//		System.out.println(car.getId());
		model.addAttribute("carList", da.searchCarByID(car));
		return "searchResultsID.html";
	}

	@GetMapping("/searchByMake")
	public String searchByMake(Model model, @ModelAttribute Car car) {
		
		model.addAttribute("carList", da.searchCarByMake(car));
		return "searchByMake.html";
	}
	
	@GetMapping("/searchByModel")
	public String searchCarByModel(Model model, @ModelAttribute Car car) {
		
		model.addAttribute("carList", da.searchCarByModel(car));
		return "searchByModel.html";
	}

	@GetMapping("/searchByPrice")
	public String searchByPrice(Model model, @RequestParam double lowerNumber, @RequestParam double higherNumber) {
		
		model.addAttribute("carList", da.searchCarByPrice(lowerNumber, higherNumber));
		
		return "searchResultsPrice.html";
	}
	
	@GetMapping("/purchase/{id}")
	public String purchase(Model model, @PathVariable int id) {
		Car car = da.getCarById(id);
		
		double priceAndTax = car.getPrice() * 1.13;
		double priceRounded = Math.round(priceAndTax);
		model.addAttribute("carList", car);
		model.addAttribute("tax", priceRounded);

//		TODO Remove the car from inventory
		da.deleteCar(id);
		return "purchase.html";
	}
	
	
}









