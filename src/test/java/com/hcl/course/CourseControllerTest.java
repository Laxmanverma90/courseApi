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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.hcl.course.controller.CourseController;
import com.hcl.course.dto.CourseDto;
import com.hcl.course.service.CourseService;

@RunWith(MockitoJUnitRunner.class)
public class CourseControllerTest {

	@Mock
	private CourseService courseService;
	
	@Mock
	private MockMvc mockMvc;

	@InjectMocks
	private CourseController courseController;
	
	List<CourseDto> courseDtos = new ArrayList<>();
	
	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(courseController).build();
		
		courseDtos.add(CourseDto.builder().courseDuration(3).courseId(101).courseName("JAVA").build());
		courseDtos.add(CourseDto.builder().courseDuration(4).courseId(102).courseName("Spring Boot").build());
	}
	
	@Test
	public void testGetAllCourse() throws Exception {
		Mockito.when(courseService.getAllCourse(Mockito.anyInt())).thenReturn(courseDtos);
		mockMvc.perform(MockMvcRequestBuilders.get("/api/courses").contentType(MediaType.APPLICATION_JSON).accept(MediaType.ALL)).andReturn();
		
		ResponseEntity<List<CourseDto>> actualResult = courseController.getAllCourse(0);
		
		assertEquals("JAVA", actualResult.getBody().get(0).getCourseName());
	}
}
