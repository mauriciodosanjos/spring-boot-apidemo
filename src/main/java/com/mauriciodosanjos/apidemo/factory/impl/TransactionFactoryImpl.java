package com.mauriciodosanjos.apidemo.factory.impl;

import com.mauriciodosanjos.apidemo.enumeration.TransactionTypeEnum;
import com.mauriciodosanjos.apidemo.factory.TransactionFactory;
import com.mauriciodosanjos.apidemo.model.Transaction;

public class TransactionFactoryImpl implements TransactionFactory {

	@Override
	public Transaction createTransaction(String type) {

		if (TransactionTypeEnum.MONEY.getValue().equals(type)) {
			return new Transaction(TransactionTypeEnum.MONEY);
		}
		
		return new Transaction(TransactionTypeEnum.CARD);
	}

}