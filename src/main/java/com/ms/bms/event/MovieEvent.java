package com.ms.bms.event;

import java.util.Map;

import com.ms.bms.event.profile.MovieEventProfile;

public class MovieEvent extends AbstractEvent {
public String screenName;
public Map<String,Integer> seats;
public MovieEventProfile profile;

}
