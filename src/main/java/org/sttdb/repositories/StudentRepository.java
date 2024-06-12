package org.sttdb.repositories;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;
import org.sttdb.entities.Student;

import java.util.List;

@ApplicationScoped
public class StudentRepository implements PanacheRepositoryBase<Student, String> {
    PanacheQuery<Student> students = Student.find("terminatedDate is null");

    public List<Student> findActiveStudents(Integer page, Integer size, String name) {
        PanacheQuery<Student> query;
        if (name == null || name.isEmpty()) {
            query = Student.find("terminatedDate is null");
        } else {
            query = Student.find(
                    "terminatedDate is null and (firstName like :name or lastName like :name)",
                    Parameters.with("name", "%" + name + "%")
            );
        }
        return query.page(page - 1, size).list();
    }
}
