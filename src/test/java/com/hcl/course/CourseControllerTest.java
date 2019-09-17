package com.hcl.course;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hcl.course.controller.CourseController;
import com.hcl.course.dto.CourseDetailDto;
import com.hcl.course.dto.CourseDto;
import com.hcl.course.service.CourseService;

@RunWith(MockitoJUnitRunner.class)
public class CourseControllerTest {

	@Mock
	private CourseService courseService;

	@InjectMocks
	private CourseController courseController;

	List<CourseDto> courseDtos = new ArrayList<>();
	CourseDetailDto courseDetailDto = null;
	List<CourseDetailDto> courseDetailDtos = new ArrayList<>();
	List<Integer> courseIds = new ArrayList<>();

	@Before
	public void setUp() {
		courseDtos.add(CourseDto.builder().courseDuration(3).courseId(101).courseName("JAVA").build());
		courseDtos.add(CourseDto.builder().courseDuration(4).courseId(102).courseName("Spring Boot").build());

		courseDetailDto = CourseDetailDto.builder().courseDuration(4).courseFee(3000).courseId(101)
				.courseName("Dhokala").build();
		
		courseDetailDtos.add(courseDetailDto);
		
		courseIds.add(101);
	}

	@Test
	public void testGetAllCourse() {

		ResponseEntity<List<CourseDto>> expResult = new ResponseEntity<>(courseDtos, HttpStatus.OK);
		Mockito.when(courseService.getAllCourse(1)).thenReturn(courseDtos);
		ResponseEntity<List<CourseDto>> actualResult = courseController.getAllCourse(1);

		assertEquals(expResult, actualResult);
	}

	@Test
	public void testGetCourseById() {
		ResponseEntity<CourseDetailDto> expectedResult = new ResponseEntity<>(courseDetailDto, HttpStatus.OK);
		Mockito.when(courseService.getCourseDetailById(101)).thenReturn(courseDetailDto);
		
		ResponseEntity<CourseDetailDto> actualResult = courseController.getCourseDetailById(101);
		assertEquals(expectedResult.getBody().getCourseName(), actualResult.getBody().getCourseName());
	}
	
	@Test
	public void testGetCoursesById() {
		ResponseEntity<List<CourseDetailDto>> expectedResult = new ResponseEntity<>(courseDetailDtos, HttpStatus.OK);
		Mockito.when(courseService.getCoursesById(courseIds)).thenReturn(courseDetailDtos);
		ResponseEntity<List<CourseDetailDto>> actualResult = courseController.getCoursesById(courseIds);
		assertEquals(expectedResult.getBody().get(0).getCourseName(), actualResult.getBody().get(0).getCourseName());
	}

	public static String asJsonString(final Object object) throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(object);

	}
}
