package com.ms.bms.event;

public abstract class AbstractEvent {
 public String name;
 public String Location;
 public Schedule schedule;
 public double price;
 public EventType type;
 
 public static enum EventType {
	 MOVIE,GAME,DRAMA
 }
}
