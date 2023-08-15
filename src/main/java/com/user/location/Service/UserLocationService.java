package com.user.location.Service;

import java.util.List;

import com.user.location.Entity.UserLocation;

public interface UserLocationService {
	
	UserLocation saveUserLocation(UserLocation userLocation);
    UserLocation updateUserLocation(Long id, UserLocation userLocation);
    List<UserLocation> getNearestUsers(int count);

}
