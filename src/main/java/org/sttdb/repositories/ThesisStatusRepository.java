package org.sttdb.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.sttdb.entities.ThesisStatus;

@ApplicationScoped
public class ThesisStatusRepository implements PanacheRepository<ThesisStatus> {
}
