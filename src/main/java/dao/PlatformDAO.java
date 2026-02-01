package dao;

import entity.PlatformUsersTable;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@AllArgsConstructor
public class PlatformDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    public PlatformUsersTable findByLogin(String login) {
        return entityManager.createQuery(
                        "FROM PlatformUsersTable WHERE login = :login",
                        PlatformUsersTable.class
                )
                .setParameter("login", login)
                .getResultStream()
                .findFirst()
                .orElse(null);
    }

    @Transactional
    public void registrationUser(PlatformUsersTable user) {
        entityManager.persist(user);
    }

    @Transactional
    public void updateUser(PlatformUsersTable user) {
        entityManager.merge(user);
    }


}
