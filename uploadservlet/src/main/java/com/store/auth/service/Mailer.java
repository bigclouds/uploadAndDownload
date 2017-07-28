package com.store.auth.service;

import java.io.StringWriter;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import java.util.List;
import com.store.auth.model.Mail;

public class Mailer {
 private MailSender mailSender;
 private VelocityEngine velocityEngine;

 public void setMailSender(MailSender mailSender) {
  this.mailSender = mailSender;
 }

 public void setVelocityEngine(VelocityEngine velocityEngine) {
  this.velocityEngine = velocityEngine;
 }

 public void sendMails(List<Mail> mails) {
	for (Mail m : mails) {
		sendMail(m);
	}
 }

 public void sendMail(Mail mail) {
  SimpleMailMessage message = new SimpleMailMessage();
  
  message.setFrom(mail.getMailFrom());
  //message.setFrom(mailSender.getUsername());
  //message.setFrom("noreply@store.com");
  message.setTo(mail.getMailTo());
  message.setSubject(mail.getMailSubject());

  /*
  Template template = velocityEngine.getTemplate("./templates/" + mail.getTemplateName());
  VelocityContext velocityContext = new VelocityContext();
  velocityContext.put("name", "Yashwant");
  velocityContext.put("url", "Pune");
  StringWriter stringWriter = new StringWriter();
  template.merge(velocityContext, stringWriter);
  message.setText(stringWriter.toString());
  */
  message.setText(mail.getMailContent());
  
  mailSender.send(message);
 }
}
