package org.web.mywebsite.services.interfaces;

import jakarta.mail.MessagingException;
import org.web.mywebsite.dtos.mail.MailConfirmDto;

public interface MailService {
    void sendConfirmationEmail(MailConfirmDto mailConfirmDto) throws MessagingException;

    void sendForgotPasswordEmail(MailConfirmDto mailConfirmDto) throws MessagingException;
}
