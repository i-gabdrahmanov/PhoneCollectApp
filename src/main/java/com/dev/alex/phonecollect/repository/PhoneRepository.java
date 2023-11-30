package com.dev.alex.phonecollect.repository;

import com.dev.alex.phonecollect.model.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long> {

    List<Phone> findAllByOperatorAndRequestDateIsAfter (String operator, LocalDateTime requestDate);
    @Query(value = "select request_date from phones where operator = ?1 order by id desc limit 1", nativeQuery = true)
    Optional<Timestamp> getLastUpdateDateTime(String operator);

    List<Phone> findAllByOperator(String operator);

    List<Phone> findAllByRequestDateIsBefore(LocalDateTime requestDate);
}
