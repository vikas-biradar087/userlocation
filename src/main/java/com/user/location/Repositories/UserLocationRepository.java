package com.user.location.Repositories;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.user.location.Entity.UserLocation;

public interface UserLocationRepository extends JpaRepository<UserLocation, Long> {

	 List<UserLocation> findByExcludedFalse();


}
