package com.capstone.hanperform.performances.repository;

import com.capstone.hanperform.performances.entity.Performances;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerformancesRepository extends JpaRepository<Performances, Long> {
}
