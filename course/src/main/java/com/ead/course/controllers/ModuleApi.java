package com.ead.course.controllers;

import com.ead.course.dtos.ModuleDto;
import com.ead.course.models.ModuleModel;
import java.util.List;
import java.util.UUID;
import org.springframework.http.ResponseEntity;

public interface ModuleApi {

    ResponseEntity<Object> saveModule(UUID courseId, ModuleDto moduleDto);

    ResponseEntity<Object> deleteModule(UUID courseId, UUID moduleId);

    ResponseEntity<Object> updateModule(UUID courseId, UUID moduleId, ModuleDto moduleDto);

    ResponseEntity<List<ModuleModel>> getAllModules(UUID courseId);

    ResponseEntity<Object> getOneModules(UUID courseId, UUID moduleId);
}
