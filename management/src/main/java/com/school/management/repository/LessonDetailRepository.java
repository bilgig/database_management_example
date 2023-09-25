package com.school.management.repository;

import com.school.management.model.LessonDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonDetailRepository extends JpaRepository <LessonDetail,Long>{

}
