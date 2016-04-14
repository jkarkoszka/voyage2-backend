package pl.edu.pja.gdansk.voyage2.sendgrid.config;

import com.sendgrid.SendGrid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SendGridConfiguration {

    @Value("${spring.sendgrid.api-key}")
    private String apiKey;

    @Bean
    public SendGrid sendGrid() {
        SendGrid sendGrid = new SendGrid(apiKey);
        return sendGrid;
    }
}
