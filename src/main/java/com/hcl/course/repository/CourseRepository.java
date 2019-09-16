package com.hcl.course.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hcl.course.entity.Course;

/**
 * @author Laxman
 *
 */
@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

//	public static final String FIND_COURSENAME_DURATION = "SELECT courseId, courseName, courseDuration FROM Course WHERE courseId NOT IN :courseIds ";
	
	@Query(value="SELECT courseId, courseName, courseDuration FROM Course WHERE courseId NOT IN :courseIds ")
	Optional<List<Object[]>> findCourseNameAndDuration(List<Integer> courseIds);
}