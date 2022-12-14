package com.remittancemiddleware.remittancemiddleware;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.remittancemiddleware.remittancemiddleware.dao.RemittanceTransactionDAO;
import com.remittancemiddleware.remittancemiddleware.dataclass.remittance.financenow.FinanceNowData;
import com.remittancemiddleware.remittancemiddleware.dataclass.remittance.paymentgo.PaymentGoData;
import com.remittancemiddleware.remittancemiddleware.entity.transaction.RemittanceTransaction;
import com.remittancemiddleware.remittancemiddleware.service.SandboxAPIService;
import com.remittancemiddleware.remittancemiddleware.service.mapper.SSOTToFinanceNowMapper;
import com.remittancemiddleware.remittancemiddleware.service.mapper.SSOTToPaymentGoMapper;
import okhttp3.OkHttpClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;


import java.text.SimpleDateFormat;


//https://www.javaguides.net/2018/11/spring-data-jpa-query-creation-from-method-names.html
//spring data jpa reference
@SpringBootApplication
public class RemittancemiddlewareApplication {

	public static void main(String[] args) {
		SpringApplication.run(RemittancemiddlewareApplication.class, args);

	}

	//injecting http client for API calls
	@Bean
	public OkHttpClient okHttpClient() {
		return new OkHttpClient();
	}

	//injecting simpleDateFormat
	@Bean
	SimpleDateFormat simpleDateFormat(){
		return new SimpleDateFormat("dd-MM-yyyy");
	}



	//ALLOWS YOU TO TEST SERVICES BY RUNNING THE APP -> REMEMBER TO COMMENT IT OUT BEFORE PUSHING IT
//	@Bean
//	@Transactional
//	public CommandLineRunner demoData(SSOTToPaymentGoMapper mapper , RemittanceTransactionDAO rtDAO, ObjectMapper objectMapper, SandboxAPIService sandService){
//		return args -> {
//
//			RemittanceTransaction rt = rtDAO.findById(4).get();
//
//			PaymentGoData result = mapper.MapSSOT(rt);
//
//			System.out.println(result.toString());
//
//			System.out.println(sandService.sendTransactionToSandbox(result,"paymentgo"));
//
//		};
//	}






/*
	@Bean
	public CommandLineRunner demoData(CompanyDAO companyDAO , RemittanceMapDAO remittanceMapDAO, UserDAO userDAO, RemittanceTransactionDAO remittanceTransactionDAO) {
		return args -> {

			Company company = new Company();
			User user1 = new User("user1@gmail.com", "abc12345", "Anne", "Tan");
			User user2 = new User("user2@gmail.com","abc12345","Jimmy","Lee");

			RemittanceMap remittanceMap = new RemittanceMap();
			ReceiverMap receiverMap = new ReceiverMap();
			SenderMap senderMap = new SenderMap();
			AddressMap addressMapR = new AddressMap();
			AddressMap addressMapS = new AddressMap();
			BankAccountMap bankAccountMapR = new BankAccountMap();
			BankAccountMap bankAccountMapS = new BankAccountMap();
			IdentificationMap identificationMapR = new IdentificationMap();
			IdentificationMap identificationMapS = new IdentificationMap();



			Sender sender = new Sender();
			Identification identificationS = new Identification();
			BankAccount bankAccountS = new BankAccount();
			Address addressS = new Address();

			Receiver receiver = new Receiver();
			Identification identificationR = new Identification();
			BankAccount bankAccountR = new BankAccount();
			Address addressR = new Address();

			RemittanceTransaction remittanceTransaction1 = new RemittanceTransaction();
			RemittanceTransaction remittanceTransaction2 = new RemittanceTransaction();
			RemittanceTransaction remittanceTransaction3 = new RemittanceTransaction();

			RemittanceTransaction remittanceTransactionE1 = new RemittanceTransaction();
			RemittanceTransaction remittanceTransactionE2 = new RemittanceTransaction();
			RemittanceTransaction remittanceTransactionE3 = new RemittanceTransaction();

			RemittanceTransaction remittanceTransactionF1 = new RemittanceTransaction();
			RemittanceTransaction remittanceTransactionF2 = new RemittanceTransaction();
			RemittanceTransaction remittanceTransactionF3 = new RemittanceTransaction();


//			user1.setEmail("user1@gmail.com");
//			user1.setPassword("abc12345");
//			user1.setFirstName("Anne");
//			user1.setLastName("Tan");
			user1.setCompany(company);

//			user2.setEmail("user2@gmail.com");
//			user2.setPassword("abc12345");
//			user2.setFirstName("Jimmy");
//			user2.setLastName("Lee");
			user2.setCompany(company);

			company.setCompanyName("Sunshine PTE LTD");
			company.addUser(user1);
			company.addUser(user2);
			// remittance trans

			identificationS.setIdNumber("9578660");
			identificationS.setParty(sender);
			identificationS.setIdType(IdType.NATIONAL_ID);
			identificationS.setIssuingCountry("Singapore");

			bankAccountS.setBankName("OCBC");
			bankAccountS.setBranchName("Bedok North");
			bankAccountS.setAccountNumber("627819930");
			bankAccountS.setParty(sender);

			addressS.setAddressLine("Block 80 Bedok North");
			addressS.setCity("Singapore");
			addressS.setCountry("Singapore");
			addressS.setState("Singapore");
			addressS.setZipCode(460080);
			addressS.setParty(sender);

			sender.setFirstName("Tammy");
			sender.setLastName("Low");
			sender.setNationality("Singaporean");
			sender.setCurrency("SGD");
			sender.setDateOfBirth(new GregorianCalendar(1995, Calendar.DECEMBER, 30).getTime());
			sender.setMobileNumber("81234567");
			sender.setIdentification(identificationS);
			sender.setBankAccount(bankAccountS);
			sender.setAddress(addressS);
			sender.setSourceOfFunds(SourceOfFunds.SALARY);
			sender.setSenderCurrency("SGD");
			sender.setBeneficiaryRelationship(BeneficiaryRelationship.FAMILY);

			identificationR.setIdNumber("45890082");
			identificationR.setParty(receiver);
			identificationR.setIdType(IdType.NATIONAL_ID);
			identificationR.setIssuingCountry("Philippines");

			bankAccountR.setBankName("Bank of the Philippine Islands");
			bankAccountR.setBranchName("Manila");
			bankAccountR.setAccountNumber("78294291");
			bankAccountR.setParty(receiver);

			addressR.setAddressLine("No. 224 Pairaso St");
			addressR.setCity("Makati");
			addressR.setCountry("Philippines");
			addressR.setState("Manila");
			addressR.setZipCode(1103);
			addressR.setParty(receiver);

			receiver.setFirstName("Mikayla");
			receiver.setLastName("Lim");
			receiver.setNationality("Filipino");
			receiver.setCurrency("PHP");
			receiver.setDateOfBirth(new GregorianCalendar(1955, Calendar.JANUARY, 24).getTime());
			receiver.setMobileNumber("822489290");
			receiver.setIdentification(identificationR);
			receiver.setBankAccount(bankAccountR);
			receiver.setAddress(addressR);
			receiver.setReceiverType("bank_account");
			receiver.setPayoutCurrency("PHP");

			senderMap.setFirstName("First Name");
			senderMap.setLastName("Last Name");
			senderMap.setNationality("Nationality");
			senderMap.setCurrency("Receiver Currency");
			senderMap.setDateOfBirth("Birthday");
			senderMap.setMobileNumber("Phone Number");
			senderMap.setIdentificationMap(identificationMapS);
			senderMap.setBankAccountMap(bankAccountMapS);
			senderMap.setAddressMap(addressMapS);
			senderMap.setSourceOfFunds("Source of Funds");
			senderMap.setSenderCurrency("Sender Currency");
			senderMap.setBeneficiaryRelationship("Relation");
			senderMap.setRemittanceMap(remittanceMap);

			receiverMap.setFirstName("First Name");
			receiverMap.setLastName("Last Name");
			receiverMap.setNationality("Nationality");
			receiverMap.setCurrency("Receiver Currency");
			receiverMap.setDateOfBirth("Birthday");
			receiverMap.setMobileNumber("Phone Number");
			receiverMap.setIdentificationMap(identificationMapR);
			receiverMap.setBankAccountMap(bankAccountMapR);
			receiverMap.setAddressMap(addressMapR);
//			receiverMap.setType("");
			receiverMap.setPayoutCurrency("Receive Currency");
			receiverMap.setRemittanceMap(remittanceMap);

			addressMapR.setAddressLine("Address");
//			addressMapR.setCity("City");
			addressMapR.setCountry("Country");
//			addressMapR.setState("State");
			addressMapR.setZipCode("Postal Code");
			addressMapR.setPartyMap(receiverMap);

			addressMapS.setAddressLine("Address");
			addressMapS.setCity("City");
			addressMapS.setCountry("Country");
			addressMapS.setState("State");
			addressMapS.setZipCode("Postal Code");
			addressMapS.setPartyMap(senderMap);

			bankAccountMapR.setBankName("Bank");
			bankAccountMapR.setBranchName("Branch");
			bankAccountMapR.setAccountNumber("Account No");
			bankAccountMapR.setPartyMap(receiverMap);

			bankAccountMapS.setBankName("Bank");
			bankAccountMapS.setBranchName("Branch");
			bankAccountMapS.setAccountNumber("Account No");
			bankAccountMapS.setPartyMap(senderMap);

			identificationMapR.setIdType("Identity Type");
			identificationMapR.setIdNumber("Identity Number");
			identificationMapR.setIssuingCountry("Country of Issue");
			identificationMapR.setPartyMap(receiverMap);

			identificationMapS.setIdType("Identity Type");
			identificationMapS.setIdNumber("Identity Number");
			identificationMapS.setIssuingCountry("Country of Issue");
			identificationMapS.setPartyMap(senderMap);

			remittanceMap.setPurpose("Remittance Purpose");
			remittanceMap.setAmount("Remittance Amount");
			remittanceMap.setPaymentMode("Mode of Payment");
			remittanceMap.setRemittanceCompany("Remit Company");
			remittanceMap.setSourceType("Source of Funds");
//			remittanceMap.setSegment("");
			remittanceMap.setSenderMap(senderMap);
			remittanceMap.setReceiverMap(receiverMap);
			remittanceMap.setCompany(company);


			remittanceTransaction1.setPurpose(RemittancePurpose.EDUCATION);
			remittanceTransaction1.setAmount(8250);
			remittanceTransaction1.setRemittanceCompany(RemittanceCompany.PAYMENT_GO);
			remittanceTransaction1.setSourceType("Loan");
			remittanceTransaction1.setSegment("");
			remittanceTransaction1.setPaymentMode("Cheque");
			remittanceTransaction1.setTransactionStatus(TransactionStatus.PENDING_AML);
			remittanceTransaction1.setSender(sender);
			remittanceTransaction1.setReceiver(receiver);
			remittanceTransaction1.setCompany(company);

			remittanceTransaction2.setPurpose(RemittancePurpose.FAMILY_EXPENSES);
			remittanceTransaction2.setAmount(6500);
			remittanceTransaction2.setRemittanceCompany(RemittanceCompany.PAYMENT_GO);
			remittanceTransaction2.setSourceType("Allowance");
			remittanceTransaction2.setSegment("");
			remittanceTransaction2.setPaymentMode("Cash");
			remittanceTransaction2.setTransactionStatus(TransactionStatus.SUCCESSFUL);
			remittanceTransaction2.setSender(sender);
			remittanceTransaction2.setReceiver(receiver);
			remittanceTransaction2.setCompany(company);

			remittanceTransaction3.setPurpose(RemittancePurpose.TRAVEL_EXPENSES);
			remittanceTransaction3.setAmount(6200);
			remittanceTransaction3.setRemittanceCompany(RemittanceCompany.PAYMENT_GO);
			remittanceTransaction3.setSourceType("Savings");
			remittanceTransaction3.setSegment("");
			remittanceTransaction3.setPaymentMode("Bank Transfer");
			remittanceTransaction3.setTransactionStatus(TransactionStatus.PENDING_COMPLIANCE_CHECKS);
			remittanceTransaction3.setSender(sender);
			remittanceTransaction3.setReceiver(receiver);
			remittanceTransaction3.setCompany(company);

			remittanceTransactionE1.setPurpose(RemittancePurpose.INVESTMENT);
			remittanceTransactionE1.setAmount(3870);
			remittanceTransactionE1.setRemittanceCompany(RemittanceCompany.EVERYWHERE_REMIT);
			remittanceTransactionE1.setSourceType("Savings");
			remittanceTransactionE1.setSegment("");
			remittanceTransactionE1.setPaymentMode("Bank Transfer");
			remittanceTransactionE1.setTransactionStatus(TransactionStatus.PENDING_COMPLIANCE_CHECKS);
			remittanceTransactionE1.setSender(sender);
			remittanceTransactionE1.setReceiver(receiver);
			remittanceTransactionE1.setCompany(company);

			remittanceTransactionE2.setPurpose(RemittancePurpose.PERSONAL_ASSET_ALLOCATION);
			remittanceTransactionE2.setAmount(5545);
			remittanceTransactionE2.setRemittanceCompany(RemittanceCompany.EVERYWHERE_REMIT);
			remittanceTransactionE2.setSourceType("Salary");
			remittanceTransactionE2.setSegment("");
			remittanceTransactionE2.setPaymentMode("Bank Transfer");
			remittanceTransactionE2.setTransactionStatus(TransactionStatus.PENDING_AML);
			remittanceTransactionE2.setSender(sender);
			remittanceTransactionE2.setReceiver(receiver);
			remittanceTransactionE2.setCompany(company);

			remittanceTransactionE3.setPurpose(RemittancePurpose.FAMILY_EXPENSES);
			remittanceTransactionE3.setAmount(3750);
			remittanceTransactionE3.setRemittanceCompany(RemittanceCompany.EVERYWHERE_REMIT);
			remittanceTransactionE3.setSourceType("Salary");
			remittanceTransactionE3.setSegment("");
			remittanceTransactionE3.setPaymentMode("Cash");
			remittanceTransactionE3.setTransactionStatus(TransactionStatus.SUCCESSFUL);
			remittanceTransactionE3.setSender(sender);
			remittanceTransactionE3.setReceiver(receiver);
			remittanceTransactionE3.setCompany(company);

			remittanceTransactionF1.setPurpose(RemittancePurpose.CHARITY_DONATION);
			remittanceTransactionF1.setAmount(250);
			remittanceTransactionF1.setRemittanceCompany(RemittanceCompany.FINANCE_NOW);
			remittanceTransactionF1.setSourceType("Savings");
			remittanceTransactionF1.setSegment("");
			remittanceTransactionF1.setPaymentMode("Cash");
			remittanceTransactionF1.setTransactionStatus(TransactionStatus.SUCCESSFUL);
			remittanceTransactionF1.setSender(sender);
			remittanceTransactionF1.setReceiver(receiver);
			remittanceTransactionF1.setCompany(company);

			remittanceTransactionF2.setPurpose(RemittancePurpose.PAYMENT_FOR_GOODS);
			remittanceTransactionF2.setAmount(999);
			remittanceTransactionF2.setRemittanceCompany(RemittanceCompany.FINANCE_NOW);
			remittanceTransactionF2.setSourceType("Salary");
			remittanceTransactionF2.setSegment("");
			remittanceTransactionF2.setPaymentMode("Bank Transfer");
			remittanceTransactionF2.setTransactionStatus(TransactionStatus.PENDING_COMPLIANCE_CHECKS);
			remittanceTransactionF2.setSender(sender);
			remittanceTransactionF2.setReceiver(receiver);
			remittanceTransactionF2.setCompany(company);

			remittanceTransactionF3.setPurpose(RemittancePurpose.PAYMENT_FOR_SERVICES);
			remittanceTransactionF3.setAmount(3500);
			remittanceTransactionF3.setRemittanceCompany(RemittanceCompany.FINANCE_NOW);
			remittanceTransactionF3.setSourceType("Salary");
			remittanceTransactionF3.setSegment("");
			remittanceTransactionF3.setPaymentMode("Bank Transfer");
			remittanceTransactionF3.setTransactionStatus(TransactionStatus.REJECTED);
			remittanceTransactionF3.setSender(sender);
			remittanceTransactionF3.setReceiver(receiver);
			remittanceTransactionF3.setCompany(company);

			company.addRemittanceTransaction(remittanceTransaction1);
			company.addRemittanceTransaction(remittanceTransaction2);
			company.addRemittanceTransaction(remittanceTransaction3);

			company.addRemittanceTransaction(remittanceTransactionE1);
			company.addRemittanceTransaction(remittanceTransactionE2);
			company.addRemittanceTransaction(remittanceTransactionE3);

			company.addRemittanceTransaction(remittanceTransactionF1);
			company.addRemittanceTransaction(remittanceTransactionF2);
			company.addRemittanceTransaction(remittanceTransactionF3);

			company.setRemittanceMap(remittanceMap);
			companyDAO.save(company);

			userDAO.save(user1);
			userDAO.save(user2);

			remittanceMapDAO.save(remittanceMap);

			remittanceTransactionDAO.save(remittanceTransaction1);
			remittanceTransactionDAO.save(remittanceTransaction2);
			remittanceTransactionDAO.save(remittanceTransaction3);

			remittanceTransactionDAO.save(remittanceTransactionE1);
			remittanceTransactionDAO.save(remittanceTransactionE2);
			remittanceTransactionDAO.save(remittanceTransactionE3);

			remittanceTransactionDAO.save(remittanceTransactionF1);
			remittanceTransactionDAO.save(remittanceTransactionF2);
			remittanceTransactionDAO.save(remittanceTransactionF3);

		};
	}

 */

	//	@Bean
	//	public mapperService mapperService(){
	//		return new mapperServiceImpl();
	//	}

}
