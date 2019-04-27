package com.ms.theatre.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ms.theatre.dao.MovieEventDao;
import com.ms.theatre.entities.MovieEvent;

@Service
public class TheatreService {
	@Autowired
	public MovieEventDao movientEventDao;
	
	public boolean block(MovieEvent event, List<String> seats) {
		for (String seat: seats) {
			movientEventDao.screensSeats.get(event.screenName).put(seat, 1);
		}
		return true;
	}
	
	public boolean markSuccess(MovieEvent event, List<String> seats) {
		for (String seat: seats) {
			movientEventDao.screensSeats.get(event.screenName).put(seat, 2);
		}
		return true;
	}
	
	public boolean cancel(MovieEvent event, List<String> seats) {
		for (String seat: seats) {
			movientEventDao.screensSeats.get(event.screenName).put(seat, 0);
		}
		return true;
	}

	public List<MovieEvent> getAllEvents() {
		return movientEventDao.getAllEvents();
	}
	
	
}
