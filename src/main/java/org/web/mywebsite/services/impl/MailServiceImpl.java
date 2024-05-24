package org.web.mywebsite.services.impl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;
import org.web.mywebsite.dtos.mail.MailConfirmDto;
import org.web.mywebsite.services.interfaces.MailService;

import java.util.Objects;

@Service
public class MailServiceImpl implements MailService {
    @Autowired
    private JavaMailSender sender;

    @Value("${frontend.domain}")
    String domain;

    @Override
    public void sendConfirmationEmail(MailConfirmDto mailConfirmDto) throws MessagingException {
        this.generateMailRequest(mailConfirmDto);
    }

    @Override
    public void sendForgotPasswordEmail(MailConfirmDto mailConfirmDto) throws MessagingException {
        this.generateMailRequest(mailConfirmDto);
    }

    private void generateMailRequest(MailConfirmDto mailConfirmDto) throws MessagingException {
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(mailConfirmDto.getUser().getUsername());
        if (Objects.equals(mailConfirmDto.getType(), "CONFIRM")) {
            helper.setSubject("Confirm you E-Mail");
            helper.setText("<html>" +
                            "<body>" +
                            "<h2>Dear "+ mailConfirmDto.getUser().getUsername() + ",</h2>"
                            + "<br/> We're excited to have you get started. " +
                            "Please click on below link to confirm your account."
                            + "<br/> "  + generateConfirmationLink(mailConfirmDto.getToken(), "CONFIRM") +
                            "<br/> Regards,<br/>" +
                            "Registration team" +
                            "</body>" +
                            "</html>"
                    , true);
        } else if (Objects.equals(mailConfirmDto.getType(), "FORGOT")) {
            helper.setSubject("Forgot password");
            helper.setText("<html>" +
                            "<body>" +
                            "<h2>Dear "+ mailConfirmDto.getUser().getUsername() + ",</h2>" +
                            "Please click the link below to get your account back."
                            + "<br/> "  + generateConfirmationLink(mailConfirmDto.getToken(), "FORGOT") +
                            "<br/> Regards,<br/>" +
                            "Registration team" +
                            "</body>" +
                            "</html>"
                    , true);
        }

    }

    private String generateConfirmationLink(String token, String type) {
        if (Objects.equals(type, "CONFIRM")) {
            return "<a href=" + domain + "/confirm-email?token="+token+">Redirect</a>";
        } else if (Objects.equals(type, "FORGOT")) {
            return "<a href=" + domain + "/forgot-password?token="+token+">Redirect</a>";
        }
        return "";
    }
}
