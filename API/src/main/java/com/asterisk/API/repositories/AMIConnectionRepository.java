package com.asterisk.API.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AMIConnectionRepository extends JpaRepository<AMIConnectionRepository, Long> {
}
