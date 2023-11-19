package com.soft.apilab.repositories;

import com.soft.apilab.models.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface InstructorRepository extends JpaRepository<Instructor, String> {

    @Query(value = "insert into tb_instructors_subjects (instructor_instructor_id, subjects_subject_id) " +
            "values (:instructorId, :subjectId) on conflict (instructor_instructor_id, subjects_subject_id) do nothing " +
            "returning case when xmin is not null then 'true' else 'false' end;", nativeQuery = true)
    Boolean associateProfessorWithSubject(@Param(value = "instructorId") String instructorId,
                                                       @Param(value = "subjectId") UUID subjectId);

}
