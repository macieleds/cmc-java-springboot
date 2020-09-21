package com.edisonmaciel.cmcjava.services;

import org.springframework.mail.SimpleMailMessage;

import com.edisonmaciel.cmcjava.domain.Pedido;

public interface EmailService {
	
	void sendOrderConfirmationEmail (Pedido obj); 
	
	void sendEmail (SimpleMailMessage msg);

}
