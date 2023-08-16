package com.atypon.AnalyticsService;

import com.atypon.AnalyticsService.models.MarksEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MarksEntryRepository extends JpaRepository<MarksEntry, Long> {

    List<MarksEntry> findByUsername(String username);
}
