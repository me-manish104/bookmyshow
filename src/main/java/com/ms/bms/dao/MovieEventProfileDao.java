package com.ms.bms.dao;

import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.ms.bms.event.profile.MovieEventProfile;

@Repository
public class MovieEventProfileDao {
	public ConcurrentHashMap<Long,MovieEventProfile> profileMap = new ConcurrentHashMap<>();
	
	@PostConstruct
	public void init() {
		profileMap.put(1l, new MovieEventProfile(1));
	}
	public MovieEventProfile fetch(long profileId) {
		return profileMap.get(profileId);
	}

}
