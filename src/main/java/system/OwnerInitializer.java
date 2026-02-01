package system;

import entity.PlatformUsers;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

//@Component
//public class OwnerInitializer {
//
//    @Value("${owner.login}")
//    private String login;
//    @Value("${owner.password}")
//    private String password;
//
//    @EventListener(ApplicationReadyEvent.class)
//    public void initOwner() {
//        PlatformUsers existingUser = platformDAO.findByLogin(login);
//    }
//}
