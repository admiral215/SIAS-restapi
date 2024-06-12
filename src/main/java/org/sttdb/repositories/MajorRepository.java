package org.sttdb.repositories;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;
import org.sttdb.entities.Major;

import java.util.List;

@ApplicationScoped
public class MajorRepository implements PanacheRepository<Major> {
    public List<Major> findAllBySearchName(Integer pageNumber, Integer pageSize,String name) {
        PanacheQuery<Major> query;
        if (name == null || name.isEmpty()) {
            query = Major.find("nonActiveDate is null");
        } else {
            query = Major.find("nonActiveDate is null and name like :name",
                    Parameters.with("name", "%" + name + "%"));
        }
        return query.page(pageNumber - 1, pageSize).list();
    }

    public Major findByName(String name) {
        return find("name", name).firstResult();
    }
}
