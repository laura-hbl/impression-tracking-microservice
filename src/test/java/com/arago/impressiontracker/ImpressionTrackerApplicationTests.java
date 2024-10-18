package com.arago.impressiontracker;

import com.arago.impressiontracker.service.AdImpressionServiceImpl;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ImpressionTrackerApplicationTests {

	@Autowired
	AdImpressionServiceImpl adImpressionService;

	@Test
	@Disabled
	void integrationTestRedis() {
		// Created test to manually check object creation in redis server
		adImpressionService.trackAdImpression("30af5efc-1dd8-4992-b7bd-105180728309");
	}
}
