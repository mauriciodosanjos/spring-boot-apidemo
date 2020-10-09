package com.mauriciodosanjos.apidemo.enumeration;

public enum TransactionTypeEnum {

	CARD("CARD"), MONEY("MONEY");

	private String value;

	private TransactionTypeEnum(String value) {
		this.value = value;

	}

	public String getValue() {
		return value;
	}
}
