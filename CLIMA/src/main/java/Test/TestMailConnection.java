package Test;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@Component
public class TestMailConnection implements CommandLineRunner {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    public TestMailConnection(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void run(String... args) throws Exception {
        if (args.length > 0 && args[0].equals("--test-mail")) {
            System.out.println("\nEnviando mail de prueba...");
            try {
                SimpleMailMessage message = new SimpleMailMessage();
                message.setFrom(fromEmail);
                message.setTo("facundozonis7@gmail.com");
                message.setSubject("Test CLIMALERT");
                message.setText("Si recibes este mail, la configuración es correcta!");

                mailSender.send(message);
                System.out.println("Mail de prueba enviado exitosamente!");
                System.exit(0);
            } catch (Exception e) {
                System.out.println("Error al enviar mail:");
                e.printStackTrace();
                System.exit(1);
            }
        }
    }
}
