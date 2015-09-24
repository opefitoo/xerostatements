package org.opefito.batchrecon.beans;

public class BceeStatement {

	private String transactionDate;
	private String description;
	private String valueDate;
	private String amount;
	private String statement;
	private String operation;
	private String balance;
	private String communication1;
	private String Communication2;
	private String Communication3;
	private String Communication4;

	public String getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getValueDate() {
		return valueDate;
	}

	public void setValueDate(String valueDate) {
		this.valueDate = valueDate;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getStatement() {
		return statement;
	}

	public void setStatement(String statement) {
		this.statement = statement;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getCommunication1() {
		return communication1;
	}

	public void setCommunication1(String communication1) {
		this.communication1 = communication1;
	}

	public String getCommunication2() {
		return Communication2;
	}

	public void setCommunication2(String communication2) {
		Communication2 = communication2;
	}

	public String getCommunication3() {
		return Communication3;
	}

	public void setCommunication3(String communication3) {
		Communication3 = communication3;
	}

	public String getCommunication4() {
		return Communication4;
	}

	public void setCommunication4(String communication4) {
		Communication4 = communication4;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BceeExpense [transactionDate=");
		builder.append(transactionDate);
		builder.append(", description=");
		builder.append(description);
		builder.append(", valueDate=");
		builder.append(valueDate);
		builder.append(", amount=");
		builder.append(amount);
		builder.append(", statement=");
		builder.append(statement);
		builder.append(", operation=");
		builder.append(operation);
		builder.append(", balance=");
		builder.append(balance);
		builder.append(", communication1=");
		builder.append(communication1);
		builder.append(", Communication2=");
		builder.append(Communication2);
		builder.append(", Communication3=");
		builder.append(Communication3);
		builder.append(", Communication4=");
		builder.append(Communication4);
		builder.append("]");
		return builder.toString();
	}
	
	

}
