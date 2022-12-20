package com.example.WebMeetingPlanner.Service;

import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
public class DatesService {
	public long calculateDateInterval(Date startDate, Date endDate) {
		return ChronoUnit.MINUTES.between(startDate.toInstant(), endDate.toInstant());
	}
}
