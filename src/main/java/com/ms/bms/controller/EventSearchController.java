package com.ms.bms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ms.bms.dao.BMSMovieEventDao;
import com.ms.bms.event.AbstractEvent;
import com.ms.bms.event.AbstractEvent.EventType;

@Component
public class EventSearchController {
	 	 
	@Autowired
	public BMSMovieEventDao movieEventDao;
	 
	 public List<AbstractEvent> getEventsByName(String name) {
		 //if (EventType.MOVIE==type)
			 return movieEventDao.eventNameMap.get(name);
		 //return null;
	 }
	
}
