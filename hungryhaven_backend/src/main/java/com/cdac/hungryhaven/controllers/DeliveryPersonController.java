package com.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dtos.Credentials;
import com.app.dtos.CustomerDto;
import com.app.dtos.DeliveryPersonDto;
import com.app.dtos.DeliveryPersonHomePageDto;
import com.app.dtos.DeliveryPersonSignUpDto;
import com.app.dtos.HungryHavenResponse;
import com.app.dtos.OrdersDto;
import com.app.entities.DeliveryPerson;
import com.app.services.DeliveryPersonService;
import com.app.services.OrdersService;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/api/v1/")
public class DeliveryPersonController {
	
	@Autowired
	private DeliveryPersonService deliveryPersonService;
	
	@Autowired
	private OrdersService ordersService;
	

	
	@PostMapping("/deliveryperson/signin")
	public ResponseEntity<HungryHavenResponse> signIn(@RequestBody Credentials cred) {
		DeliveryPersonDto deliveryPersonDto = deliveryPersonService.findDeliveryPersonByEmailAndPassword(cred);
		if(deliveryPersonDto == null)
			return HungryHavenResponse.error("Couldn't find Delivery Person with that credentials");
		return HungryHavenResponse.success(deliveryPersonDto);
	}
	
	@GetMapping("/deliverypersonhomepage/{id}")
	public ResponseEntity<HungryHavenResponse> findDeliveryPersonHomePageDetails(@PathVariable("id") int id){
		DeliveryPersonHomePageDto deliveryPersonDto = ordersService.getdeliveryPersonHomePageDtoById(id);
		if(deliveryPersonDto == null)
			return HungryHavenResponse.error("Couldn't find Delivery Person Details with that id");
		return HungryHavenResponse.success(deliveryPersonDto);
	}
	
	@PostMapping("/deliveryperson/{orderId}/{status}")
	public ResponseEntity<HungryHavenResponse> setStatusByOrder(@PathVariable("orderId") int orderId, @PathVariable("status") String status) {
		boolean updateStatus = ordersService.setStatusForOrder(orderId, status);
		if(!updateStatus)
			return HungryHavenResponse.error("Couldn't update status for order");
		return HungryHavenResponse.success("Order status updated");
	}
	
	@GetMapping("/orders/deliveryperson/{id}")
	public ResponseEntity<HungryHavenResponse> getAllOrdersbyCustomerId(@PathVariable("id") int deliveryPersonId) {
		List<DeliveryPersonHomePageDto> dphpDtoList = ordersService.findAllOrdersByDeliveryPerson(deliveryPersonId);
		if(dphpDtoList == null || dphpDtoList.isEmpty())
			return HungryHavenResponse.error("List empty!");
		return HungryHavenResponse.success(dphpDtoList);
	}
	
	@PostMapping("/deliveryperson/arrivedorders/{deliverypersonId}")
	public ResponseEntity<HungryHavenResponse> getArrivedOrders(@PathVariable("deliverypersonId") int deliverypersonId){
		String status = "arrived";
		List<DeliveryPersonHomePageDto> orders = ordersService.findArrivedordersByDeliverypersonIdAndStatus(deliverypersonId,status);
		if(orders == null || orders.isEmpty())
			return HungryHavenResponse.error("No orders assigned");
		
		//List<OrdersDto>ordersDtoList = DaoToEntityConverter.ordersToOrdersDto(orders);
		return HungryHavenResponse.success(orders);
		
	}
	
	@GetMapping("/deliveryperson/{deliverypersonId}/status/{status}")
	public ResponseEntity<HungryHavenResponse> getOrders(@PathVariable("deliverypersonId") int deliverypersonId, @PathVariable("status") String status){
		
		List<DeliveryPersonHomePageDto> orders = ordersService.findArrivedordersByDeliverypersonIdAndStatus(deliverypersonId,status);
		if(orders == null || orders.isEmpty())
			return HungryHavenResponse.error("No orders assigned");
		
		return HungryHavenResponse.success(orders);
	}
	
	@PostMapping("/deliveryperson/signup")
	public ResponseEntity<HungryHavenResponse> deliveryPersonSignUp(@RequestBody DeliveryPersonSignUpDto deliveryPersonSignUpDto) {
		boolean status = deliveryPersonService.addDeliveryPerson(deliveryPersonSignUpDto);
		if(status)
			return HungryHavenResponse.success("Delivery Person Added");
		return HungryHavenResponse.error("Delivery person could not be added");
	}
	
}
