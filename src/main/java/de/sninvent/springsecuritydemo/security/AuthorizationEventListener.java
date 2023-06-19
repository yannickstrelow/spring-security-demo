package de.sninvent.springsecuritydemo.security;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.event.EventListener;
import org.springframework.security.authorization.event.AuthorizationDeniedEvent;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class AuthorizationEventListener {

    @EventListener
    public void onFailure(AuthorizationDeniedEvent failure) {
        log.info("Authentication has failed for user: " + failure.getAuthentication().get().getPrincipal());
    }
}
