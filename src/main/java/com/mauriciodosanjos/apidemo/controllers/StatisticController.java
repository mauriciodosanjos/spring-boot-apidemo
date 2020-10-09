package com.mauriciodosanjos.apidemo.controllers;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mauriciodosanjos.apidemo.model.Statistic;
import com.mauriciodosanjos.apidemo.model.Transaction;
import com.mauriciodosanjos.apidemo.service.StatisticService;
import com.mauriciodosanjos.apidemo.service.TransactionService;


@RestController
@RequestMapping("/financial/v1/statistics")
public class StatisticController {
	
	private static final Logger logger = Logger.getLogger(StatisticController.class);
	
	@Autowired
	private TransactionService transactionService;
	
	@Autowired
	private StatisticService statisticsService;
	
	@GetMapping(produces = { "application/json" })
	public ResponseEntity<Statistic> getStatistics() {
		
		List<Transaction> transactions = transactionService.find();
		Statistic statistics = statisticsService.create(transactions);
		
		logger.info(statistics);
		
		return ResponseEntity.ok(statistics);
	}

}