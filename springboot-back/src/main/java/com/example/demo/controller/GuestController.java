package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Guest;
import com.example.demo.repository.GuestRepository;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class GuestController {

	@Autowired
	private GuestRepository guestRepository;
	
	// get all guest 
	@GetMapping("/guests")
	public List<Guest> getAllEmployees() {
		return guestRepository.findAll();
	}
	
	// create guest rest api
	@PostMapping("/guests")
	public Guest createGuest(@RequestBody Guest guest) {
		return guestRepository.save(guest);
	}
	
	// get guest by id rest api
	@GetMapping("/guests/{id}")
	public ResponseEntity<Guest>  getGuestById(@PathVariable Long id) {
		Guest guest = guestRepository.findById(id)
						.orElseThrow(() -> new ResourceNotFoundException("Guest not exist with id : "+ id));
		return ResponseEntity.ok(guest);
	}
	
	// update guest rest api
	@PutMapping("/guests/{id}")
	public ResponseEntity<Guest> updateGuest(@PathVariable Long id,@RequestBody Guest guestDetails) {
		Guest guest = guestRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Guest not exist with id : "+ id));
		
		guest.setFirstName(guestDetails.getFirstName());
		guest.setLastName(guestDetails.getLastName());
		guest.setEmailId(guestDetails.getEmailId());
		
		Guest updatedGuest = guestRepository.save(guest);
		return ResponseEntity.ok(updatedGuest);
	}
	
	// delete guest rest api
	@DeleteMapping("/guests/{id}")
	public ResponseEntity<Map<String,Boolean>> deleteGuest(@PathVariable Long id) {
		Guest guest = guestRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Guest not exists with id :" + id));
		guestRepository.delete(guest);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}
