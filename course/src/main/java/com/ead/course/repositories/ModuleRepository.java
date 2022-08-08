package com.ead.course.repositories;

import com.ead.course.models.ModuleModel;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModuleRepository extends JpaRepository<ModuleModel, UUID> {
}