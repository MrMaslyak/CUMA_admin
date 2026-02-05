package system;

import dao.PlatformDAO;
import entity.PlatformUsersTable;
import enums.SystemRole;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class OwnerInitializer {

  private final PasswordEncoder passwordEncoder;
    private final PlatformDAO platformDAO;

    public OwnerInitializer(PasswordEncoder passwordEncoder, PlatformDAO platformDAO) {
        this.passwordEncoder = passwordEncoder;
        this.platformDAO = platformDAO;
    }

    @Value("${owner.login}")
    private String login;
    @Value("${owner.password}")
    private String password;

    @EventListener(ApplicationReadyEvent.class)
    @Transactional
    public void initOwner() {
        PlatformUsersTable existingUser = platformDAO.findByLogin(login);

        if (existingUser == null){
            platformDAO.registrationUser(buildOwner(login, password));
            System.out.println("OWNER created");
        }else {
            boolean needsUpdate = false;

            if (!passwordEncoder.matches(password, existingUser.getPassword())) {
                existingUser.setPassword(passwordEncoder.encode(password));
                needsUpdate = true;
            }
            if (!password.equals(existingUser.getPassword())){
                existingUser.setPassword(password);
                needsUpdate = true;
            }
            if (existingUser.getSystem_role() != SystemRole.OWNER) {
                existingUser.setSystem_role(SystemRole.OWNER);
                needsUpdate = true;
            }

            if (needsUpdate) {
                platformDAO.updateUser(existingUser);
                System.out.println("OWNER updated");
            } else {
                System.out.println("OWNER already up-to-date");
            }
        }
    }

    private PlatformUsersTable buildOwner(String login, String password){
        Date now = new Date();
        PlatformUsersTable user = new PlatformUsersTable();
        user.setLogin(login);
        user.setPassword(passwordEncoder.encode(password));
        user.setEmail("ogorodov@gmail.com");
        user.setSystem_role(SystemRole.OWNER);
        user.setCreated_at(now);
        return user;
    }
}
