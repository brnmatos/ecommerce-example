package br.com.dpaulla.model;

import lombok.Data;

public @Data class CheckoutBoletoPost {
	
	String paymentMode;
	String paymentMethod	=	"boleto";
	String receiverEmail;
	String currency;
	String extraAmount;
	int itemId1;
	String itemDescription1;
	String itemAmount1;
	int itemQuantity1;
	String notificationURL;
	String reference;
	String senderName;
	String senderCPF;
	String senderAreaCode;
	String senderPhone;
	String senderEmail;
	String senderHash;
	String shippingAddressRequired;
	String shippingAddressStreet;
	String shippingAddressNumber;
	String shippingAddressComplement;
	String shippingAddressDistrict;
	String shippingAddressPostalCode;
	String shippingAddressCity;
	String shippingAddressState;
	String shippingAddressCountry;
	String shippingType;
	String shippingCost;
}
