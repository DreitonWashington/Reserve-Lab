package com.soft.apilab.repositories;

import com.soft.apilab.models.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;
import java.util.UUID;

public interface SubjectRepository extends JpaRepository<Subject, UUID> {

    @Query(value = "select * from tb_subjects inner join tb_instructors_subjects on tb_subjects.subject_id " +
            "= tb_instructors_subjects.subjects_subject_id where " +
            "tb_instructors_subjects.instructor_instructor_id = :instructorId", nativeQuery = true)
    Set<Subject> findSubjectsByInstructor(@Param(value = "instructorId") String instructorId);
}
