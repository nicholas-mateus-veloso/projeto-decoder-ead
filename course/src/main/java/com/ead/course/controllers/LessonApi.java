package com.ead.course.controllers;

import com.ead.course.dtos.LessonDto;
import com.ead.course.models.LessonModel;
import com.ead.course.specifications.SpecificationTemplate;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface LessonApi {

    ResponseEntity<Object> saveLesson(UUID moduleId, LessonDto lessonDto);

    ResponseEntity<Object> deleteLesson(UUID moduleId, UUID lessonId);

    ResponseEntity<Object> updateLesson(UUID moduleId, UUID lessonId, LessonDto lessonDto);

    ResponseEntity<Page<LessonModel>> getAllLessons(UUID moduleId,
                                                    SpecificationTemplate.LessonSpec spec,
                                                    Pageable pageable);

    ResponseEntity<Object> getOneLessons(UUID moduleId, UUID lessonId);
}
