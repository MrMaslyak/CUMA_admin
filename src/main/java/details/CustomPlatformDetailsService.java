package details;



import dao.PlatformDAO;
import entity.PlatformUsersTable;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomPlatformDetailsService implements UserDetailsService {

    private PlatformDAO platformDAO;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        PlatformUsersTable user = platformDAO.findByLogin(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        System.out.println("Loaded user: " + user.getLogin() + ", password: " + user.getPassword());
        return new CustomPlatformDetails(user);
    }


}
