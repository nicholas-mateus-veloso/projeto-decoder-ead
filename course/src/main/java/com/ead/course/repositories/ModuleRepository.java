package com.ead.course.repositories;

import com.ead.course.models.ModuleModel;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ModuleRepository extends JpaRepository<ModuleModel, UUID> {

//    Para carregar determinado método no banco de dados como FETCH.EAGER, caso tenha setado LAZY nos atributos do model
//    @EntityGraph(attributePaths = {"courses"})
//    ModuleModel findByTitle(String title);

    //@Modifying --> usando para updates e deletes
    @Query(value = "select * from tb_modules where course_course_id = :courseId", nativeQuery = true)
    List<ModuleModel> findAllModulesIntoCourse(@Param("courseId") UUID courseId);
}