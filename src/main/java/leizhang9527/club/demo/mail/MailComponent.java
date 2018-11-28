package leizhang9527.club.demo.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


@Component
public class MailComponent {
    
    @Autowired
    private JavaMailSender mailSender;
    
    //简单邮件
    public void sendMailBasic1() throws MessagingException {
        
        MimeMessage message = mailSender.createMimeMessage();
        message.addRecipient(
                Message.RecipientType.TO,
                new InternetAddress("2892466887@qq.com")
        );
        message.addRecipient(
                Message.RecipientType.CC,
                new InternetAddress("1534678971@qq.com")
        );
        
        message.setFrom(new InternetAddress("1169566754@qq.com"));
        message.setSubject("这是一个测试");
        message.setText("Hello Spring Boot");
        
        mailSender.send(message);
        
    }
    
    //简单邮件
    public void sendMailBasic2() throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message);
        messageHelper.addTo("2892466887@qq.com");//收件人
        messageHelper.addBcc("1534678971@qq.com");//密送收件人
        messageHelper.setFrom("1169566754@qq.com");//发件人
        messageHelper.setSubject("这是第二封测试邮件");
        messageHelper.setText("Hello Spring Boot 2");
        mailSender.send(message);
    }


    //复杂邮件-带附件
    public void sendAttachments() throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
        messageHelper.addTo("2892466887@qq.com");//收件人
        messageHelper.addBcc("1534678971@qq.com");//密送收件人
        messageHelper.setFrom("1169566754@qq.com");//发件人
        messageHelper.setSubject("带附件邮件");
        messageHelper.setText("这是带附件的邮件");

        ClassPathResource resource = new ClassPathResource("banner.txt");
        //FileSystemResource resource = new FileSystemResource("D:\\abc.txt");
        //1.File , InputStreamSource, DataSource
        messageHelper.addAttachment("banner.txt", resource);

        mailSender.send(message);

    }

    //复杂邮件发送-带图片内联资源
    public void sendInnerResource() throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
        messageHelper.addTo("2892466887@qq.com");//收件人
        messageHelper.addBcc("1534678971@qq.com");//密送收件人
        messageHelper.setFrom("1169566754@qq.com");;//发件人
        messageHelper.setSubject("邮件内容中有图片");

        StringBuilder content = new StringBuilder();
        content.append("<html>")
                .append("<body>")
                .append("<h1>").append("欢迎使用GitHub").append("</h1>")
                .append("<img src=").append("'cid:img1'").append("</>")
                .append("</body>")
                .append("</html>");
        //C:\Users\Deleteorus\Pictures\github.png
        messageHelper.setText(content.toString(), true);
        messageHelper.addInline("img1",
                new FileSystemResource("C:\\Users\\Decadence\\Desktop\\计算机网络脑图.png"));

        mailSender.send(message);
    }
    
}
