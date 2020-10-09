package com.mauriciodosanjos.apidemo.factory;

import com.mauriciodosanjos.apidemo.model.Transaction;

public interface TransactionFactory {

	Transaction createTransaction(String type);
	
}