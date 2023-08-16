package com.atypon.EnterDataWebApp;


import com.atypon.EnterDataWebApp.models.MarksEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarksEntryRepository extends JpaRepository<MarksEntry, Long> {
}
