package org.sttdb.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.sttdb.entities.Thesis;
import org.sttdb.entities.ThesisStatusEnum;

import java.util.List;

@ApplicationScoped
public class ThesisRepository implements PanacheRepository<Thesis> {
    public List<Thesis> findAllByStatus(String status, Integer pageNumber, Integer pageSize) {
        if (status == null){
            return findAll().page(pageNumber - 1, pageSize).list();
        }
        return find("SELECT t FROM thesis t JOIN t.thesisStatuses ts WHERE ts.status = ?1",
                ThesisStatusEnum.valueOf(status.toUpperCase()))
                .page(pageNumber - 1,pageSize)
                .list();
    }
}
