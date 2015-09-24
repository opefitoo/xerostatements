package org.opefito.batchrecon.processors;

import org.apache.commons.lang3.StringUtils;
import org.opefito.batchrecon.beans.BceeStatement;
import org.opefito.batchrecon.beans.XeroStatement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import com.google.common.base.CharMatcher;
import com.google.common.base.Splitter;

public class ExpenseItemProcessor implements ItemProcessor<BceeStatement, XeroStatement> {

    private static final Logger log = LoggerFactory.getLogger(ExpenseItemProcessor.class);

//    @Override
//    public Person process(final Person person) throws Exception {
//        final String firstName = person.getFirstName().toUpperCase();
//        final String lastName = person.getLastName().toUpperCase();
//
//        final Person transformedPerson = new Person(firstName, lastName);
//
//        log.info("Converting (" + person + ") into (" + transformedPerson + ")");
//
//        return transformedPerson;
//    }

	public XeroStatement process(BceeStatement bcee) throws Exception {
		XeroStatement xero = new XeroStatement();
		xero.setDescription("BCEE:" + bcee.getDescription());
		String americanizedAmount = bcee.getAmount().replace(".", "");
		americanizedAmount = americanizedAmount.replace(",", ".");
		xero.setAmount( americanizedAmount );
		xero.setDate(bcee.getValueDate());
		xero.setCheckNumber(bcee.getCommunication1());
		if(org.apache.commons.lang3.StringUtils.containsIgnoreCase(bcee.getDescription(), "EN FAVEUR DE" )) {
			xero.setPayee(bcee.getDescription().split("EN FAVEUR DE")[1]);
		} else {
			xero.setPayee( bcee.getDescription());
		}
		
		xero.setReference(bcee.getOperation() + bcee.getCommunication1() + bcee.getCommunication2() + bcee.getCommunication3() +  bcee.getCommunication4());
		return xero;
	}

}
