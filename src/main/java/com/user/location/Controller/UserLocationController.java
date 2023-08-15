package com.user.location.Controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.location.Entity.UserLocation;
import com.user.location.Service.UserLocationService;

@RestController
@RequestMapping("/Api")
public class UserLocationController {

	private final UserLocationService userLocationService;

    
    public UserLocationController(UserLocationService userLocationService) {
		super();
		this.userLocationService = userLocationService;
	}


    //localhost:8080/Api/createData//
	@PostMapping("/createData")
    public ResponseEntity<UserLocation> createUserLocation(@RequestBody UserLocation userLocation) {
        UserLocation savedLocation = userLocationService.saveUserLocation(userLocation);
        return ResponseEntity.ok(savedLocation);
    }

	
	
	//localhost:8080/Api/updateData/{id}//
    @PatchMapping("/updateData/{id}")
    public ResponseEntity<UserLocation> updateUserLocation(
            @PathVariable Long id, @RequestBody UserLocation userLocation) {
        UserLocation updatedLocation = userLocationService.updateUserLocation(id, userLocation);
        return ResponseEntity.ok(updatedLocation);
    }

    
  //localhost:8080/Api/getUsers/{count}.
    @GetMapping("/getUsers/{count}")
    public ResponseEntity<List<UserLocation>> getNearestUsers(@PathVariable int count) {
        List<UserLocation> nearestUsers = userLocationService.getNearestUsers(count);
        return ResponseEntity.ok(nearestUsers);
    }

}
