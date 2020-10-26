/**
 * 
 */
package com.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.notification.bean.NotificationBean;
import com.notification.utils.DataConverter;

import lombok.extern.slf4j.Slf4j;

/**
 * @author nsanda
 *
 */
@RestController
@CrossOrigin(allowedHeaders="*")
@RequestMapping("/api")
@Slf4j
public class NotificationController {

	@Autowired
	private MailSender mailSender;
	
	@PostMapping("/mail")
	public ResponseEntity<? extends Object> sendEmail(@RequestBody NotificationBean noteBean){
		
		try {
			log.info("The Nofication data is:\t"+DataConverter.convertToJson(noteBean));
			
			String bodyTemplate = null;
			if(noteBean.getMailType().equals("VerificationEmail")) {
				bodyTemplate = "Congratulations Registration Successfull. Please click on the below verification link to activate your account. "
						+ "http://localhost:3000/user-reg/"+noteBean.getMailTo();
				
				noteBean.setMailBody(bodyTemplate);
			}
			
			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom(noteBean.getMailFrom());
			message.setTo(noteBean.getMailTo());
			message.setSubject(noteBean.getMailSubject());
			message.setText(noteBean.getMailBody());
			
			mailSender.send(message);
			
			
		}catch (Exception e) {
			log.error(e.getMessage());
		}
		
		return new ResponseEntity(noteBean, HttpStatus.OK);
	}
}
