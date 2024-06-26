package org.sttdb.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import org.sttdb.entities.User;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class UserRepository implements PanacheRepositoryBase<User,String> {
    public List<User> findByRole(String role) {
        return find("role", role).list();
    }

    public User findByUsername(String username) {
        return find("username", username).firstResult();
    }

    public Optional<User> findOptionalByUsername(String username) {
        return find("username", username).firstResultOptional();
    }
}
