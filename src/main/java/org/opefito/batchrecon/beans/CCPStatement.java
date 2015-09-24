package org.opefito.batchrecon.beans;

public class CCPStatement {

	// Date comptable;Description;Montant de l'opération;Devise;Date
	// valeur;Compte de la contrepartie;Nom de la contrepartie :;Communication 1
	// :;Communication 2 :;Référence d'opération

	String dateComptable;
	String description;
	String montantDeLOperation;
	String devise;
	String operationReference;
	String valueDate;
	String amount;
	String communication1;
	String communication2;
	String counterPartName;
	String counterPartAccount;

	public String getDateComptable() {
		return dateComptable;
	}

	public void setDateComptable(String dateComptable) {
		this.dateComptable = dateComptable;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMontantDeLOperation() {
		return montantDeLOperation;
	}

	public void setMontantDeLOperation(String montantDeLOperation) {
		this.montantDeLOperation = montantDeLOperation;
	}

	public String getDevise() {
		return devise;
	}

	public void setDevise(String devise) {
		this.devise = devise;
	}

	public String getOperationReference() {
		return operationReference;
	}

	public void setOperationReference(String operationReference) {
		this.operationReference = operationReference;
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

	public String getCommunication1() {
		return communication1;
	}

	public void setCommunication1(String communication1) {
		this.communication1 = communication1;
	}

	public String getCommunication2() {
		return communication2;
	}

	public void setCommunication2(String communication2) {
		this.communication2 = communication2;
	}

	public String getCounterPartName() {
		return counterPartName;
	}

	public void setCounterPartName(String counterPartName) {
		this.counterPartName = counterPartName;
	}

	public String getCounterPartAccount() {
		return counterPartAccount;
	}

	public void setCounterPartAccount(String counterPartAccount) {
		this.counterPartAccount = counterPartAccount;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CCPStatement [dateComptable=");
		builder.append(dateComptable);
		builder.append(", description=");
		builder.append(description);
		builder.append(", montantDeLOperation=");
		builder.append(montantDeLOperation);
		builder.append(", devise=");
		builder.append(devise);
		builder.append(", operationReference=");
		builder.append(operationReference);
		builder.append(", valueDate=");
		builder.append(valueDate);
		builder.append(", amount=");
		builder.append(amount);
		builder.append(", communication1=");
		builder.append(communication1);
		builder.append(", communication2=");
		builder.append(communication2);
		builder.append(", counterPartName=");
		builder.append(counterPartName);
		builder.append(", counterPartAccount=");
		builder.append(counterPartAccount);
		builder.append("]");
		return builder.toString();
	}

}
