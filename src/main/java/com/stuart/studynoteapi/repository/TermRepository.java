package com.stuart.studynoteapi.repository;

import com.stuart.studynoteapi.models.Term;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TermRepository extends JpaRepository<Term, Long> {
}
