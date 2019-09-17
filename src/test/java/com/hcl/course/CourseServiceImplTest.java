package com.hcl.course;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.hcl.course.dto.CourseDetailDto;
import com.hcl.course.dto.CourseDto;
import com.hcl.course.entity.Course;
import com.hcl.course.repository.CourseRepository;
import com.hcl.course.service.CourseServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class CourseServiceImplTest {

	@Mock
	private CourseRepository courseRepository;

	@InjectMocks
	private CourseServiceImpl courseServiceImpl;

	List<Integer> courseIds = new ArrayList<>(Arrays.asList(1, 3));
	Optional<List<Object[]>> optionalCources = null;
	Optional<Course> optCourse = null;
	Optional<List<Course>> courses = null;

	@Before
	public void setUp() {
		List<Object[]> objects = new ArrayList<>();
		Object[] obj = new Object[3];
		obj[0] = 101;
		obj[1] = "Java";
		obj[2] = 4;
		objects.add(obj);
		optionalCources = Optional.of(objects);
		
		Course course = new Course();
		course.setCourseDuration(4);
		course.setCourseFee(3000.0);
		course.setCourseId(101);
		course.setCourseName("Java");
		course.setDescription("Hello World");
		optCourse = Optional.of(course);
		
		List<Course> courseList = new ArrayList<>();
		courseList.add(course);
		courses = Optional.of(courseList);
	}

	@Test
	public void testGetAllCourse() {
		Mockito.when(courseRepository.findCourseNameAndDuration(courseIds)).thenReturn(optionalCources);
		List<CourseDto> actualResult = courseServiceImpl.getAllCourse(0);

		assertEquals("Java", actualResult.get(0).getCourseName());
	}

	@Test
	public void testGetCourseDetailById() {
		Mockito.when(courseRepository.findById(101)).thenReturn(optCourse);
		CourseDetailDto actualResult = courseServiceImpl.getCourseDetailById(101);
		assertEquals("Java", actualResult.getCourseName());
	}
	
	@Test
	public void testGetCoursesById() {
		Mockito.when(courseRepository.findByCourseIdIn(courseIds)).thenReturn(courses);
		List<CourseDetailDto> actualResult = courseServiceImpl.getCoursesById(courseIds);
		
		assertEquals("Java", actualResult.get(0).getCourseName());
	}
}
