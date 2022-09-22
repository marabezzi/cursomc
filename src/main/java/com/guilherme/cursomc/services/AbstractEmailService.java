package com.guilherme.cursomc.services;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.guilherme.cursomc.domain.Cliente;
import com.guilherme.cursomc.domain.Pedido;

public abstract class AbstractEmailService implements EmailService {

 	@Value("${default.sender}")
 	private String sender;

 	@Override
 	public void sendOrderConfirmationEmail(Pedido obj) {
 		SimpleMailMessage sm = prepareSimpleMailMessageFromPedido(obj);
 		sendEmail(sm);
 	}

 	protected SimpleMailMessage prepareSimpleMailMessageFromPedido(Pedido obj) {
 		SimpleMailMessage sm = new SimpleMailMessage();
 		sm.setTo(obj.getCliente().getEmail());
 		sm.setFrom(sender);
 		sm.setSubject("Pedido confirmado! Código: " + obj.getId());
 		sm.setSentDate(new Date(System.currentTimeMillis()));
 		sm.setText(obj.toString());
 		return sm;
 	}
 }
