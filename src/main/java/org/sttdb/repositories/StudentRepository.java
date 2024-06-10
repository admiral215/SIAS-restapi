package org.sttdb.repositories;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import org.sttdb.entities.Student;

import java.util.List;

@ApplicationScoped
public class StudentRepository implements PanacheRepositoryBase<Student, String> {
    PanacheQuery<Student> students = Student.find("terminatedDate is null");

    public List<Student> findActiveStudents(Integer page, Integer size) {
        return students.page(page - 1, size).list();
    }
}
