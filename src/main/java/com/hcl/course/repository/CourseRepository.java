package com.hcl.course.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.course.entity.Course;

/**
 * @author Laxman
 *
 */
@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

}
