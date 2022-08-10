package com.ead.course.controllers;

import com.ead.course.dtos.ModuleDto;
import com.ead.course.models.ModuleModel;
import com.ead.course.specifications.SpecificationTemplate;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface ModuleApi {

    ResponseEntity<Object> saveModule(UUID courseId, ModuleDto moduleDto);

    ResponseEntity<Object> deleteModule(UUID courseId, UUID moduleId);

    ResponseEntity<Object> updateModule(UUID courseId, UUID moduleId, ModuleDto moduleDto);

    ResponseEntity<Page<ModuleModel>> getAllModules(UUID courseId,
                                                    SpecificationTemplate.ModuleSpec spec,
                                                    Pageable pageable);

    ResponseEntity<Object> getOneModules(UUID courseId, UUID moduleId);

}
