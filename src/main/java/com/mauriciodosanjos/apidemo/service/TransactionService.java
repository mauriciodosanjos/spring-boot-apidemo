package com.mauriciodosanjos.apidemo.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mauriciodosanjos.apidemo.enumeration.TransactionTypeEnum;
import com.mauriciodosanjos.apidemo.factory.TransactionFactory;
import com.mauriciodosanjos.apidemo.factory.impl.TransactionFactoryImpl;
import com.mauriciodosanjos.apidemo.model.Transaction;

@Service
public class TransactionService {

	private TransactionFactory factory;
	private List<Transaction> transactions;
	

	public void createFactory() {
		if(factory == null) {
			factory = new TransactionFactoryImpl();
		}
	}
	

	public void createTransactionList() {
		if(transactions == null) {
			transactions = new ArrayList<>();
		}
	}
	

	public boolean isJSONValid(String jsonInString) {
	    try {
	       return new ObjectMapper().readTree(jsonInString) != null;
	    } catch (IOException e) {
	       return false;
	    }
	}
	

	private long parseId(JSONObject transaction) {
		return Long.valueOf((int) transaction.get("id"));
	}
	

	private BigDecimal parseAmount(JSONObject transaction) {
		return new BigDecimal((String) transaction.get("amount"));
	}
	

	private LocalDateTime parseTransactionDate(JSONObject transaction) {
		var transactionDate = (String) transaction.get("transactionDate");
		return ZonedDateTime.parse(transactionDate).toLocalDateTime();
	}
	

	public boolean isTransactionInFuture(Transaction transaction) {
		return transaction.getTransactionDate().isAfter(LocalDateTime.now());
	}
	

	private void setTransactionValues(JSONObject jsonTransaction, Transaction transaction) {
		
		String autorizationNumber = (String) jsonTransaction.get("autorizationNumber");
		String nsu = (String) jsonTransaction.get("nsu");
		
		transaction.setAmount(jsonTransaction.get("amount") != null ? parseAmount(jsonTransaction) : transaction.getAmount());
		transaction.setTransactionDate(jsonTransaction.get("transactionDate") != null ? 
				parseTransactionDate(jsonTransaction) : transaction.getTransactionDate());
		transaction.setAutorizationNumber(TransactionTypeEnum.CARD.getValue().equals(transaction.getType().getValue()) ? autorizationNumber : null);
		transaction.setNsu(nsu != null ? nsu : transaction.getNsu());
	}
	

	public Transaction create(JSONObject jsonTransaction) {
		
		createFactory();
		
		Transaction transaction = factory.createTransaction((String) jsonTransaction.get("type"));
		transaction.setId(parseId(jsonTransaction));
		setTransactionValues(jsonTransaction, transaction);
		
		return transaction;
	}
	

	public Transaction update(Transaction transaction, JSONObject jsonTransaction) {
		
		setTransactionValues(jsonTransaction, transaction);
		return transaction;
	}


	public void add(Transaction transaction) {
		createTransactionList();
		transactions.add(transaction);
	}


	public List<Transaction> find() {
		createTransactionList();
		return transactions;
	}
	

	public Transaction findById(long id) {
		return transactions.stream().filter(t -> id == t.getId()).collect(Collectors.toList()).get(0);
	}
	

	public void delete() {
		transactions.clear();
	}
	

	public void clearObjects() {
		transactions = null;
		factory = null;
	}
	
}
