package util;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.AddressException;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
public class MailUtil{
	public static void sendMail(String to,String token){
		Properties pro = new Properties();
		pro.setProperty("mail.smtp.host", "smtp.sina.cn");
		pro.setProperty("mail.smtp.auth", "true");
		Authenticator auth = new Authenticator() {
				//验证用户名、密码
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("15882032402", "wanghu110119");
				}
			};
			Session session = Session.getInstance(pro,auth);
			MimeMessage msg = new MimeMessage(session);
			try {
				msg.setFrom(new InternetAddress("15882032402@sina.cn"));
				msg.setRecipients(RecipientType.TO, to);
				msg.setSubject("激活邮件--51Job邮箱验证");
				msg.setContent("<a href=\"http://localhost:8080/mavenJob/emailattestation.do?id="+token+"\">点击激活:http://localhost:8080/mavenJob/emailattestation.do?token="+token+"</a>","text/html;charset=utf-8");
				Transport.send(msg);
			} catch (AddressException e) {
				e.printStackTrace();
			} catch (NoSuchProviderException e) {
				e.printStackTrace();
			} catch (MessagingException e) {
				e.printStackTrace();
			}
	}
}