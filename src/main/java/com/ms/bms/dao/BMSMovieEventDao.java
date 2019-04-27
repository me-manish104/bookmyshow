package com.ms.bms.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ms.bms.event.AbstractEvent;
import com.ms.bms.event.Schedule;
import com.ms.bms.transaction.MovieTransaction;
import com.ms.theatre.entities.MovieEvent;
import com.ms.theatre.services.TheatreService;

@Repository
public class BMSMovieEventDao {
	 public ConcurrentHashMap<String, List<AbstractEvent>> eventNameMap = new ConcurrentHashMap<>();

	@Autowired
	public TheatreService thService;
	@Autowired
	public MovieEventProfileDao dao;
	
	@PostConstruct
	 public void init() {
		 List<MovieEvent> events = thService.getAllEvents();
		 //Adding movie events
		 for (MovieEvent event : events) {
			 eventNameMap.compute(event.name,(key, value) -> {
				  if (value == null) {
					  value=new ArrayList<>();
				  }
				  value.add(getMovieEvent(event));
				  return value;
		 });
		 }
	 }
	
	private com.ms.bms.event.MovieEvent getMovieEvent(MovieEvent event) {
		com.ms.bms.event.MovieEvent movieEvent = new com.ms.bms.event.MovieEvent();
		 movieEvent.Location=event.Location;
		 movieEvent.name=event.name;
		 movieEvent.price=event.price;
		 movieEvent.profile=dao.fetch(event.profileId);
		 movieEvent.schedule=new Schedule(event.schedule.startTime, event.schedule.endTime);
		 movieEvent.screenName=event.screenName;
		 movieEvent.seats=event.seats;
		 return movieEvent;
	}

	public void block(MovieTransaction transaction) {
		thService.block(getTheatreServieEvent(transaction),transaction.seatLocation);		
	}

	public void markSuccess(MovieTransaction transaction) {
		thService.markSuccess(getTheatreServieEvent(transaction), transaction.seatLocation);
	}

	private MovieEvent getTheatreServieEvent(MovieTransaction transaction) {
		 MovieEvent movieEvent = new MovieEvent();
		 movieEvent.screenName=transaction.screenName;
		 return movieEvent;
	}

	public void cancel(MovieTransaction transaction) {
		thService.cancel(getTheatreServieEvent(transaction), transaction.seatLocation);
	}
}
