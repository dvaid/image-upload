package com.coswafe.odyssey.service;

import org.springframework.stereotype.Service;

import com.coswafe.odyssey.constants.AppConstants;
import com.coswafe.odyssey.model.PaymentModel;
import com.paygate.ag.common.utils.PayGateCryptoUtils;

import java.util.Random;

@Service
public class PaymentService {

	public PaymentModel openPaymentUrl() {
		
		return getPaymentModel();
	}

	private PaymentModel getPaymentModel() {
		PaymentModel model = new PaymentModel();
		Random random = new Random();

		String txn_details = AppConstants.AGGREGATOR_ID + '|' + AppConstants.MERCHENT_ID + '|' + random.nextInt(99999) + '|'
				+ "15" + '|' + "IND" + '|' + "INR" + '|' + "SALE" + '|' + AppConstants.SUCCESS_URL + '|'
				+ AppConstants.FAILURE_URL + '|' + "WEB";
		String me_id = AppConstants.MERCHENT_ID;

		String pg_details = "" + '|' + "" + '|' + "" + '|' + "";
		String card_details = "" + '|' + "" + '|' + "" + '|' + "" + '|' + "";
		String cust_details = "" + '|' + "" + '|' + "" + '|' + "" + '|' + "";
		String bill_details = "" + '|' + "" + '|' + "" + '|' + "" + '|' + "";
		String ship_details = "" + '|' + "" + '|' + "" + '|' + "" + '|' + "" + '|' + "" + '|' + "";
		String item_details = "" + '|' + "" + '|' + "";
		String other_details = "" + '|' + "" + '|' + "" + '|' + "" + '|' + "";
		String merchant_key = AppConstants.MERCHENT_KEY;

		System.out.println("txn_details" + txn_details);
		System.out.println("pg_details" + pg_details);
		System.out.println("card_details" + card_details);
		System.out.println("cust_details" + cust_details);
		System.out.println("bill_details" + bill_details);
		System.out.println("ship_details" + ship_details);
		System.out.println("item_details" + item_details);
		System.out.println("other_details" + other_details);

		String Encrypted_txn_details = PayGateCryptoUtils.encrypt(txn_details, merchant_key);
		String Encrypted_pg_details = PayGateCryptoUtils.encrypt(pg_details, merchant_key);
		String Encrypted_card_details = PayGateCryptoUtils.encrypt(card_details, merchant_key);
		String Encrypted_cust_details = PayGateCryptoUtils.encrypt(cust_details, merchant_key);
		String Encrypted_bill_details = PayGateCryptoUtils.encrypt(bill_details, merchant_key);
		String Encrypted_ship_details = PayGateCryptoUtils.encrypt(ship_details, merchant_key);
		String Encrypted_item_details = PayGateCryptoUtils.encrypt(item_details, merchant_key);
		String Encrypted_other_details = PayGateCryptoUtils.encrypt(other_details, merchant_key);

		System.out.println("Encrypted_txn_details" + Encrypted_txn_details);
		System.out.println("Encrypted_pg_details" + Encrypted_pg_details);
		System.out.println("Encrypted_card_details" + Encrypted_card_details);
		System.out.println("Encrypted_cust_details" + Encrypted_cust_details);
		System.out.println("Encrypted_bill_details" + Encrypted_bill_details);
		System.out.println("Encrypted_ship_details" + Encrypted_ship_details);
		System.out.println("Encrypted_item_details" + Encrypted_item_details);
		System.out.println("Encrypted_other_details" + Encrypted_other_details);

		model.setMe_id(me_id);
		model.setTxn_details(Encrypted_txn_details);
		model.setPg_details(Encrypted_pg_details);
		model.setCard_details(Encrypted_card_details);
		model.setCust_details(Encrypted_cust_details);
		model.setBill_details(Encrypted_bill_details);
		model.setShip_details(Encrypted_ship_details);
		model.setItem_details(Encrypted_item_details);
		model.setOther_details(Encrypted_other_details);

		return model;
	}

}
