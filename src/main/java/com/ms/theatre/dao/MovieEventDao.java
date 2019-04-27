package com.ms.theatre.dao;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

import com.ms.theatre.entities.MovieEvent;
import com.ms.theatre.entities.Schedule;

@Repository
public class MovieEventDao {
	public ConcurrentHashMap<String,Map<String,Integer>> screensSeats = new ConcurrentHashMap<>();

public List<MovieEvent> getAllEvents() {
		
		ArrayList<MovieEvent> list= new ArrayList<>();
		for (int i=0;i<10;i++) {
			MovieEvent me= new MovieEvent();
			me.Location="BLR";
			me.name="Avengers";
			me.price=500.00;
			me.profileId=1;
			me.schedule=new Schedule(ZonedDateTime.of(2019, 4, 21, 10, 00, 00, 00, ZoneId.systemDefault()), ZonedDateTime.of(2019, 4, 21, 13, 00, 00, 00, ZoneId.systemDefault()));
			me.screenName="Screen"+i;
			me.seats=screensSeats.computeIfAbsent("Screen"+i, (k)->getSeats());;
			list.add(me);
		}
		
		return list;
	}

	private static Map<String, Integer> getSeats() {
		Map<String,Integer> seatMap=new ConcurrentHashMap<>();
		seatMap.put("A1", 0);
		seatMap.put("A2", 0);
		seatMap.put("A3", 0);
		seatMap.put("A4", 0);
		seatMap.put("A5", 0);
		seatMap.put("A6", 0);
		seatMap.put("A7", 0);
		return seatMap;
	}
}
