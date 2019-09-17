package com.hcl.course.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.course.dto.CourseDetailDto;
import com.hcl.course.dto.CourseDto;
import com.hcl.course.service.CourseService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Laxman
 *
 */
@Slf4j
@RestController
@RequestMapping("/api")
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
public class CourseController {

	@Autowired
	private CourseService courseService;
	
	
	/**
	 * this method will return the list of Course which are avaliable 
	 * @return List<CourseDto>
	 * 
	 * @author Laxman
	 * @date 16 Sept 2019
	 */
	@GetMapping("/courses")
	public ResponseEntity<List<CourseDto>> getAllCourse(@RequestParam Integer registrationId){
		
		log.info("CourseController :: getAllCourse --- ");
		return new ResponseEntity<>(courseService.getAllCourse(registrationId), HttpStatus.OK);
	}
	
	@GetMapping("/courses/{courseId}")
	public ResponseEntity<CourseDetailDto> getCourseDetailById(@PathVariable Integer courseId){
		
		log.info("CourseController :: getCourseDetailById --- ");
		return new ResponseEntity<>(courseService.getCourseDetailById(courseId), HttpStatus.OK);
	}
	

	
}
