package com.global.book.service;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;


@Component
public class PriceSchedule {
	
	Logger log = LoggerFactory.getLogger(PriceSchedule.class);
	
	
	//@Scheduled(fixedRate = 2000)
	//@Scheduled(fixedRateString = "PT02S")
	//@Scheduled(fixedRateString = "${price.interval}")
	@Scheduled(cron="${interval-in-cron}")
	@Async
	@SchedulerLock(name="bookComputePrice")
	public void computePrice() throws InterruptedException {
		Thread.sleep(4000);
		log.info("Compute Price -> "+ LocalDateTime.now());
	}
	
	
	@Scheduled(fixedRate = 2000)
	@Async
	@SchedulerLock(name="bookComputeDiscount")
	public void computeDiscount() throws InterruptedException {
		Thread.sleep(4000);
		log.info("Compute Discount -> "+ LocalDateTime.now());
	}
}
