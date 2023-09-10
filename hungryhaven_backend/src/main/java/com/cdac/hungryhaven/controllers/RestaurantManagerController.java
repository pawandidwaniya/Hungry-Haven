package com.app.controllers;

import java.util.ArrayList;


import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.daos.FoodTypeDao;
import com.app.daos.OrdersDao;
import com.app.daos.RestaurantDao;
import com.app.dtos.Credentials;
import com.app.dtos.CustomerDto;
import com.app.dtos.CustomerSignUpDto;
import com.app.dtos.DaoToEntityConverter;
import com.app.dtos.DeliveryPersonDto;
import com.app.dtos.FoodItemHomePageDto;
import com.app.dtos.FoodTypeDto;
import com.app.dtos.HungryHavenResponse;
import com.app.dtos.OrdersDto;
import com.app.dtos.RestManAndRestSignUpDto;
import com.app.dtos.RestaurantManagerDto;
import com.app.entities.Customer;
import com.app.entities.DeliveryPerson;
import com.app.entities.FoodItem;
import com.app.entities.FoodType;
import com.app.entities.Orders;
import com.app.entities.RestaurantManager;
import com.app.services.DeliveryPersonService;
import com.app.services.FoodItemService;
import com.app.services.FoodTypeService;
import com.app.services.OrdersService;
import com.app.services.RestaurantManagerService;
import com.app.services.RestaurantService;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/api/v1/")
public class RestaurantManagerController {
	
	@Autowired
	private RestaurantManagerService restaurantManagerService;
	
	@Autowired
	private DeliveryPersonService deliveryPersonService;
	
	@Autowired
	private OrdersService ordersService;

	@Autowired
	private FoodItemService foodItemService;
	
	@Autowired
	private FoodTypeService foodTypeService;
	
	@Autowired
	private RestaurantService restaurantService;

	
	@GetMapping("/restaurantmanager/{id}")
	public ResponseEntity<HungryHavenResponse> getRestaurantManagerById(@PathVariable("id") int id)
	{
		Optional<RestaurantManager> r = restaurantManagerService.getRestaurantManagerById(id);
		if(r==null)
			return HungryHavenResponse.error("not found");
		
		return HungryHavenResponse.success(r);

	}
	@PostMapping("/restaurantmanager/signin")
	public ResponseEntity<HungryHavenResponse> signIn(@RequestBody Credentials cred)
	{
		RestaurantManagerDto restauarantManagerDto =restaurantManagerService.findRestaurantManagerByEmailAndPassword(cred);
		if(restauarantManagerDto==null)
			return HungryHavenResponse.error("not found");
		
		return HungryHavenResponse.success(restauarantManagerDto);
		
	}
	



	@GetMapping("/restaurantmanager/availabledeliveryperson/{status}")
	public ResponseEntity<HungryHavenResponse> getDeliveryPersonByAvailable(@PathVariable("status") boolean status)
	{
		List<DeliveryPersonDto> dto = deliveryPersonService.findDeliveryPersonByIsAvailable(status);
		if(dto == null)
			return HungryHavenResponse.error("not available");
		
		return HungryHavenResponse.success(dto);

	}
	

	@PostMapping("/restaurantmanager/arrivedorders/{restaurantId}")
	public ResponseEntity<HungryHavenResponse> getArrivedOrders(@PathVariable("restaurantId") int restaurantId) {
		
		String status = "arrived";
		List<Orders> orders = ordersService.findArrivedOrdersByRestaurantIdAndStatus(restaurantId, status);
		if(orders == null || orders.isEmpty())
			return HungryHavenResponse.error("List Empty!");
		
		// orders is full
		List<OrdersDto> ordersDtoList = DaoToEntityConverter.ordersToOrdersDto(orders);
		
//		System.out.println(ordersDtoList);
		return HungryHavenResponse.success(ordersDtoList);
	}
	
	@PostMapping("/restaurantmanager/allorders/{restaurantId}")
	public ResponseEntity<HungryHavenResponse> getAllOrdersByRestaurant(@PathVariable("restaurantId") int restaurantId) {
		List<Orders> orders = ordersService.findAllOrdersByRestaurantid(restaurantId);
		if(orders == null || orders.isEmpty())
			return HungryHavenResponse.error("List Empty!");
		List<OrdersDto> ordersDtoList = DaoToEntityConverter.ordersToOrdersDto(orders);
		return HungryHavenResponse.success(ordersDtoList);
	}
	

	@PostMapping("/restaurantmanager/addfooditem")
	public ResponseEntity<HungryHavenResponse> addFoodItem(@RequestBody FoodItemHomePageDto foodItemHomePageDto) {

		boolean status = foodItemService.saveFoodItemDto(foodItemHomePageDto);
		if(!status)
			return HungryHavenResponse.error("Couldn't add food item");
		
		return HungryHavenResponse.success("Food item added");
	}

	@GetMapping("/foodtypes")
	public ResponseEntity<HungryHavenResponse> getAllFoodTypes() {
		List<FoodTypeDto> foodTypes = foodTypeService.findAllFoodTypes();
		return HungryHavenResponse.success(foodTypes);
	}
	
	@GetMapping("/foodTypes/edit/{foodItemId}")
	public ResponseEntity<HungryHavenResponse> getFoodItemDetails(@PathVariable("foodItemId") int foodItemId) {
		FoodItemHomePageDto foodItemHomePageDto = foodItemService.getDtoById(foodItemId);
		List<FoodTypeDto> foodTypes = foodTypeService.findAllFoodTypes();
		
		List<Object> resultData = new ArrayList<Object>();
		resultData.add(foodItemHomePageDto);
		resultData.add(foodTypes);
		
		return HungryHavenResponse.success(resultData);
	}
	
	@PostMapping("/foodTypes/edit/{foodItemId}")
	public ResponseEntity<HungryHavenResponse> updateFoodItemDetails(@RequestBody FoodItemHomePageDto foodItemHomePageDto) {
		boolean status = foodItemService.updateFoodItem(foodItemHomePageDto);
		if(!status)
			return HungryHavenResponse.error("Couldn't update food item");
		
		return HungryHavenResponse.success("Food item updated");
	}
	
	@PostMapping("/orders/assign/{orderId}/{deliveryPersonId}")
	public ResponseEntity<HungryHavenResponse> assignDeliveryPersonToOrder
		(@PathVariable("orderId") int orderId, @PathVariable("deliveryPersonId") int deliveryPersonId) {
		boolean status = ordersService.assignDeliveryPersonToOrder(orderId, deliveryPersonId);
		if(status == false)
			HungryHavenResponse.error("Order not assigned");
		
		return HungryHavenResponse.success("Order assigned successfully");
	}
	
	@GetMapping("/fooditem/restaurant/{restaurantId}")
	public ResponseEntity<HungryHavenResponse> getAllFoodItemsByRestaurantId(@PathVariable("restaurantId") int restaurantId) {
		List<FoodItemHomePageDto> foodItemDtos = foodItemService.findAllFoodItemsFromRestaurant(restaurantId);
		if(foodItemDtos == null || foodItemDtos.isEmpty())
			return HungryHavenResponse.error("No food items found, please add food items.");
		return HungryHavenResponse.success(foodItemDtos);
	}
	
	@PostMapping("/restaurantmanager/signup")
	public ResponseEntity<HungryHavenResponse> restManagerAndRestSignUp(@RequestBody RestManAndRestSignUpDto dto) {
		boolean status = restaurantService.restManagerAndRestSignUp(dto);
		if(status)
			return HungryHavenResponse.success("Added Restaurant and Restaurant Manager");
		return HungryHavenResponse.error("Could not add Restaurant and Restaurant Manager");
	}

}
