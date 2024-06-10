package org.sttdb.repositories;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import org.sttdb.entities.Lecturer;

import java.util.List;

@ApplicationScoped
public class LecturerRepository implements PanacheRepositoryBase<Lecturer,String>{
    PanacheQuery<Lecturer> lecturers = Lecturer.find("terminatedDate is null");

    public List<Lecturer> findActiveLecturers(Integer pageIndex, Integer pageSize) {
        return lecturers.page(pageIndex - 1, pageSize).list();
    }
}
