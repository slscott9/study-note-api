package com.stuart.studynoteapi.repository;

import com.stuart.studynoteapi.models.UserSet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SetRepository extends JpaRepository<UserSet, Long> {
}
