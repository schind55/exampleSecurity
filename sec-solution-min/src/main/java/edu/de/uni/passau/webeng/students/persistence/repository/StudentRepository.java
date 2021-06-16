package edu.de.uni.passau.webeng.students.persistence.repository;

import edu.de.uni.passau.webeng.students.persistence.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@SuppressWarnings("ALL")

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query(value = "select s from Student s where s.courses.size > :number")
    List<Student> getStudentsWithMoreCoursesThan(@Param("number") Integer number);

    @Query(value = "select * from Student s where (select count(c.students_matr_nr)" +
            "from course_students c where s.matr_nr=c.students_matr_nr)>?1", nativeQuery = true)
    List<Student> getStudentsWithMoreCoursesThanAlt(Integer number);
}
