package leizhang9527.club.demo.control;


import leizhang9527.club.demo.mail.MailComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

@RestController
@RequestMapping(value = {"mail",""})
public class MailController {

    private final Logger logger = LoggerFactory.getLogger(MailController.class);

    @Autowired
    private MailComponent mailComponent;

    @RequestMapping(value = "send1",method = RequestMethod.GET)
    public Boolean send1(){
        try {
            mailComponent.sendMailBasic1();
            return true;
        } catch (MessagingException e) {
            logger.error("Send mail exception {}.", e.getMessage());
            return false;
        }
    }

    @RequestMapping(value = "send2",method = RequestMethod.GET)
    public Boolean send2(){
        try {
            mailComponent.sendMailBasic2();
            return true;
        } catch (MessagingException e) {
            logger.error("Send mail exception {}.", e.getMessage());
            return false;
        }
    }

    @RequestMapping(value = "send3",method = RequestMethod.GET)
    public Boolean send3(){
        try {
            mailComponent.sendAttachments();
            return true;
        } catch (MessagingException e) {
            logger.error("Send mail exception {}.", e.getMessage());
            return false;
        }
    }

    @RequestMapping(value = "send4",method = RequestMethod.GET)
    public Boolean send4(){
        try {
            mailComponent.sendInnerResource();
            return true;
        } catch (MessagingException e) {
            logger.error("Send mail exception {}.", e.getMessage());
            return false;
        }
    }
}
