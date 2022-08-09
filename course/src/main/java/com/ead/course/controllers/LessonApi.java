package com.ead.course.controllers;

import com.ead.course.dtos.LessonDto;
import com.ead.course.models.LessonModel;
import java.util.List;
import java.util.UUID;
import org.springframework.http.ResponseEntity;

public interface LessonApi {

    ResponseEntity<Object> saveLesson(UUID moduleId, LessonDto lessonDto);

    ResponseEntity<Object> deleteLesson(UUID moduleId, UUID lessonId);

    ResponseEntity<Object> updateLesson(UUID moduleId, UUID lessonId, LessonDto lessonDto);

    ResponseEntity<List<LessonModel>> getAllLessons(UUID moduleId);

    ResponseEntity<Object> getOneLessons(UUID moduleId, UUID lessonId);
}
