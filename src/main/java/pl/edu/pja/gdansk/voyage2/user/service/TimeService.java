package pl.edu.pja.gdansk.voyage2.user.service;

import org.springframework.stereotype.Component;

@Component
public class TimeService {

    public Long getCurrentTimestamp() {
        return System.currentTimeMillis() / 1000L;
    }
}
