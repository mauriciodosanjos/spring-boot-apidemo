package com.mauriciodosanjos.apidemo.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mauriciodosanjos.apidemo.model.Statistic;
import com.mauriciodosanjos.apidemo.model.Transaction;

@Service
public class StatisticService {

	public Statistic create(List<Transaction> transactions) {

		var statistics = new Statistic();
		statistics.setCount(transactions.stream().count());
		statistics.setAvg(BigDecimal
				.valueOf(transactions.stream().mapToDouble(t -> t.getAmount().doubleValue()).average().orElse(0.0))
				.setScale(2, RoundingMode.HALF_UP));
		statistics.setMin(BigDecimal
				.valueOf(transactions.stream().mapToDouble(t -> t.getAmount().doubleValue()).min().orElse(0.0))
				.setScale(2, RoundingMode.HALF_UP));
		statistics.setMax(BigDecimal
				.valueOf(transactions.stream().mapToDouble(t -> t.getAmount().doubleValue()).max().orElse(0.0))
				.setScale(2, RoundingMode.HALF_UP));
		statistics.setSum(BigDecimal.valueOf(transactions.stream().mapToDouble(t -> t.getAmount().doubleValue()).sum())
				.setScale(2, RoundingMode.HALF_UP));

		return statistics;
	}
}
