package org.sttdb.repositories;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;
import org.sttdb.entities.Lecturer;

import java.util.List;

@ApplicationScoped
public class LecturerRepository implements PanacheRepositoryBase<Lecturer,String>{
    PanacheQuery<Lecturer> lecturers = Lecturer.find("terminatedDate is null");

    public List<Lecturer> findActiveLecturers(Integer pageIndex, Integer pageSize, String name) {
        PanacheQuery<Lecturer> query;
        if (name == null || name.isEmpty()) {
            query = Lecturer.find("terminatedDate is null");
        } else {
            query = Lecturer.find(
                    "terminatedDate is null and (firstName like :name or lastName like :name)",
                    Parameters.with("name", "%" + name + "%")
            );
        }
        return query.page(pageIndex - 1, pageSize).list();
    }
}
