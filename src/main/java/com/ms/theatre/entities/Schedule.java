package com.ms.theatre.entities;

import java.time.Duration;
import java.time.ZonedDateTime;

public class Schedule {
	public final ZonedDateTime startTime;
	public final ZonedDateTime endTime;
	public Duration duration;
	public Schedule(ZonedDateTime startTime, ZonedDateTime endTime) {
		super();
		this.startTime = startTime;
		this.endTime = endTime;
		this.duration = Duration.between(startTime, endTime);
	}
}
