package com.capstone.hanperform.repository;

import com.capstone.hanperform.entity.Performances;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerformancesRepository extends JpaRepository<Performances, Long> {
}
