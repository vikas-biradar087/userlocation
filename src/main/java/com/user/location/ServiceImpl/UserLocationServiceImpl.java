package com.user.location.ServiceImpl;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import com.user.location.Entity.UserLocation;
import com.user.location.Repositories.UserLocationRepository;
import com.user.location.Service.UserLocationService;

@Service
public class UserLocationServiceImpl implements UserLocationService {

	private final UserLocationRepository userLocationRepository;

  
    public UserLocationServiceImpl(UserLocationRepository userLocationRepository) {
		super();
		this.userLocationRepository = userLocationRepository;
	}



	@Override
    public UserLocation saveUserLocation(UserLocation userLocation) {
        return userLocationRepository.save(userLocation);
    }

    @Override
    public UserLocation updateUserLocation(Long id, UserLocation userLocation) {
        UserLocation existingLocation = userLocationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("UserLocation not found with id: " + id));

        existingLocation.setName(userLocation.getName());
        existingLocation.setLatitude(userLocation.getLatitude());
        existingLocation.setLongitude(userLocation.getLongitude());
        existingLocation.setExcluded(userLocation.isExcluded());

        return userLocationRepository.save(existingLocation);
    }

    @Override
    public List<UserLocation> getNearestUsers(int count) {
        return userLocationRepository.findByExcludedFalse()
                .stream()
                .sorted(Comparator.comparingDouble(location ->
                        Math.sqrt(location.getLatitude() * location.getLatitude() +
                                location.getLongitude() * location.getLongitude())))
                .limit(count)
                .collect(Collectors.toList());
    }

}
