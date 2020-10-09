package com.mauriciodosanjos.apidemo.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.mauriciodosanjos.apidemo.enumeration.TransactionTypeEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Transaction {

	private Long id;
	private String nsu;
	private String autorizationNumber;
	private BigDecimal amount;
	private LocalDateTime transactionDate;
	private TransactionTypeEnum type;

	public Transaction(TransactionTypeEnum type) {
		this.type = type;
	}

}