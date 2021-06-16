package edu.de.uni.passau.webeng.students.persistence.repository;

import edu.de.uni.passau.webeng.students.persistence.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
