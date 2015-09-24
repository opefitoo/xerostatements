package org.opefito.batchrecon.processors;

import org.opefito.batchrecon.beans.CCPStatement;
import org.opefito.batchrecon.beans.XeroStatement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class CCPStatementItemProcessor implements ItemProcessor<CCPStatement, XeroStatement> {

    private static final Logger log = LoggerFactory.getLogger(CCPStatementItemProcessor.class);

	public XeroStatement process(CCPStatement ccp) throws Exception {

		XeroStatement xero = new XeroStatement();
		xero.setDescription("CCP:" + ccp.getDescription());
		String americanizedAmount = ccp.getAmount().replace(".", "");
		americanizedAmount = americanizedAmount.replace(",", ".");
		xero.setAmount( americanizedAmount );
		xero.setDate(ccp.getValueDate());
		xero.setCheckNumber(ccp.getCommunication1());
		if(ccp.getCounterPartName() != null) {
			xero.setPayee(ccp.getCounterPartName());
		} else {
			xero.setPayee( ccp.getCounterPartAccount());
		}
		xero.setReference(ccp.getOperationReference());
		return xero;
	}

}
