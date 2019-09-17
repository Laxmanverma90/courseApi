package com.hcl.course.service;

import java.util.List;

import com.hcl.course.dto.CourseDetailDto;
import com.hcl.course.dto.CourseDto;

public interface CourseService {

	List<CourseDto> getAllCourse(Integer registrationId);

	CourseDetailDto getCourseDetailById(Integer courseId);

	List<CourseDetailDto> getCoursesById(List<Integer> courseIds);

}
