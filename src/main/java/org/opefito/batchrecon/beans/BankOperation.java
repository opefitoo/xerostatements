package org.opefito.batchrecon.beans;

import java.math.BigDecimal;

public class BankOperation {

	ExpenseType expenseType;
	private String line;
	private String description;
	private BigDecimal professionalQuotePart;
	private BigDecimal total;

	public ExpenseType getExpenseType() {
		return expenseType;
	}

	public void setExpenseType(ExpenseType expenseType) {
		this.expenseType = expenseType;
	}

	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getProfessionalQuotePart() {
		return professionalQuotePart;
	}

	public void setProfessionalQuotePart(BigDecimal professionalQuotePart) {
		this.professionalQuotePart = professionalQuotePart;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Expense [line=");
		builder.append(line);
		builder.append(", description=");
		builder.append(description);
		builder.append(", professionalQuotePart=");
		builder.append(professionalQuotePart);
		builder.append(", total=");
		builder.append(total);
		builder.append("]");
		return builder.toString();
	}

}
