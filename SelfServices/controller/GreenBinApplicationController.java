package uk.gov.crawley.restapi.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import uk.gov.crawley.restapi.exception.CustomSelfRestAPIException;
import uk.gov.crawley.restapi.helper.RestURIConstants;
import uk.gov.crawley.restapi.model.AccountDetail;
import uk.gov.crawley.restapi.model.AccountDetailsWrapper;
import uk.gov.crawley.restapi.model.AccountMatchScreen;
import uk.gov.crawley.restapi.model.AccountMatchWrapper;
import uk.gov.crawley.restapi.model.Directory;
import uk.gov.crawley.restapi.model.GreenBinApplicationResponse;
import uk.gov.crawley.restapi.model.GreenBinScreen;
import uk.gov.crawley.restapi.service.EFinanceService;
import uk.gov.crawley.selfservices.datamssqlservices.model.GreenBinApplication;
import uk.gov.crawley.selfservices.datamssqlservices.model.SelfServiceWebDirectory;
import uk.gov.crawley.selfservices.datamssqlservices.model.SelfServiceWebDocument;
import uk.gov.crawley.selfservices.dataoracleservices.model.Account;
import uk.gov.crawley.selfservices.dataoracleservices.service.GreenBinService;

/**
 * Controller for {@link SelfServiceWebDirectory} and
 * {@link SelfServiceWebDocument}.
 * 
 * @author parekha
 *
 */
@Controller
@RequestMapping(RestURIConstants.WASTE_AND_RECYCLE)
@Configuration
@PropertySource(value = "file:${catalina.home}/conf/selfservice.properties", ignoreResourceNotFound = false)
public class GreenBinApplicationController {

	private static final Logger logger = Logger.getLogger(GreenBinApplicationController.class);
	
	@Autowired
	private EFinanceService eFinanceService;
	
	@Autowired
	private GreenBinService greenBinService;
	
	/**
	 * Get {@link Directory} details by AccountNumber.
	 * 
	 * @param accountNumber
	 * @return
	 */
	@RequestMapping(value = RestURIConstants.GREEN_BIN_ACCOUNT_INVOICE, method = RequestMethod.GET)
	public @ResponseBody GreenBinScreen getAccountInvoices(@PathVariable("customerReference") String customerReference) {

		if (logger.isDebugEnabled()) {
			logger.debug(
					"GreenBinAccountController.getAccount(customerReference): Start");
		}
		List<Account> accounts = greenBinService.getAccountInvoices(customerReference);

		if (logger.isDebugEnabled()) {
			logger.debug(
					"GreenBinAccountController.getAccount(customerReference): finish");
		}
		
		List<AccountDetail> accountDetailList = new ArrayList<AccountDetail>();
		for (Account account : accounts) {
			AccountDetail accountDetail = new AccountDetail();
			accountDetail.setCurrentBalance(account.getCurrentBalance());
			accountDetail.setNextBillingDate(account.getNextBillingDate());
			accountDetail.setPaymentMethod(account.getPaymentMethod());
			
			accountDetail.setOverdue("yes");
			accountDetail.setNextBillingDate("2016-12-31");
			accountDetail.setCustomerRef(customerReference);
			accountDetail.setBillingSoon("no");
			accountDetail.setLastInvoiceRef("99990000");
			
			accountDetailList.add(accountDetail);
		}
		
		
		return new GreenBinScreen(new AccountDetailsWrapper(accountDetailList));
	}
	
	@RequestMapping(value = RestURIConstants.GREEN_BIN_RELATED_ACCOUNTS, method = RequestMethod.GET)
	public @ResponseBody AccountMatchScreen getMatchingAccounts(@PathVariable("surname") String surname, @PathVariable("uprn") String uprn, @PathVariable("postcode") String postcode) {
		if (logger.isDebugEnabled()) {
			logger.debug(
					"GreenBinAccountController.getMatchingAccounts(String surname, String uprn, String postcode): Start");
		}
		
		List<Account> accounts = greenBinService.getMatchingAccounts(surname, uprn, postcode);
		
		if (logger.isDebugEnabled()) {
			logger.debug(
					"GreenBinAccountController.getMatchingAccounts(String surname, String uprn, String postcode): finiash");
		}
		return new AccountMatchScreen(new AccountMatchWrapper(accounts));
	}
	
	/**
	 * Get {@link Directory} details by AccountNumber.
	 * 
	 * @param accountNumber
	 * @return
	 * @throws CustomSelfRestAPIException 
	 */
	@RequestMapping(value = RestURIConstants.GREEN_BIN_APPLICATION, method = RequestMethod.POST)
	public @ResponseBody GreenBinApplicationResponse greenBinApplication(@RequestBody GreenBinApplication greenBinApplication) throws CustomSelfRestAPIException {

		if (logger.isDebugEnabled()) {
			logger.debug(
					"GreenBinApplicationController.greenBinApplication(greenBinApplication): Start");
		}
		eFinanceService.createGreenBinAccount(greenBinApplication);

		if (logger.isDebugEnabled()) {
			logger.debug(
					"GreenBinAccountController.getAccount(customerReference): finish");
		}

		GreenBinApplicationResponse response = new GreenBinApplicationResponse();
		if(greenBinApplication.getSaStatus().equalsIgnoreCase("ERROR : FILES NOT CREATED")){
			response.setStatus("Finished with partial failures");
		}
		return response;
	}

}
