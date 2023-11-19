package com.soft.apilab.repositories;

import com.soft.apilab.models.Reserve;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

public interface ReserveRepository extends JpaRepository<Reserve, UUID> {
    Page<Reserve> findAll(Specification<Reserve> spec, Pageable pageable);

    @Query(value = "select case when count(*) > 0 then true else false end from tb_reserves where start_time = :date and lab_lab_id = :lab_id", nativeQuery = true)
    Boolean checkIfReserveExistByDate(@Param("date") LocalDateTime date, @Param("lab_id") UUID lab_id);

    @Query(value = "select to_char(start_time, 'HH24:00') as hora\n" +
            "from tb_reserves\n" +
            "where date(start_time) = :date and lab_lab_id = :lab_id", nativeQuery = true)
    Set<String> extractHourFromReservesDate(@Param("date") LocalDateTime date, @Param("lab_id") UUID lab_id);

    @Query(value = "select case when count(*) > 0 then true else false end from tb_reserves where instructor_instructor_id = :instructor_Id", nativeQuery = true)
    Boolean checkIfReserveExistByInstructor(@Param("instructor_Id") String instructor_Id);

    @Modifying
    @Query(value = "update tb_reserves set reserve_status = 'CONCLUDED' where end_time < :localDateTime", nativeQuery = true)
    void updateToConcludedReservesStatus(@Param("localDateTime") LocalDateTime localDateTime);

    @Query(value = "select * from tb_reserves where instructor_instructor_id = " +
            "(select instructor_instructor_id from tb_users where user_id = :userId) " +
            "order by case when reserve_status = 'RESERVED' then 0 else 1 end, start_time desc", nativeQuery = true)
    Page<Reserve> findAllReservesFromUser(@Param(value = "userId") UUID userId, Pageable pageable);
}
