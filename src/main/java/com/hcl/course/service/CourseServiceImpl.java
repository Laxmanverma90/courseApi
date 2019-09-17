package com.hcl.course.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.hcl.course.dto.CourseDetailDto;
import com.hcl.course.dto.CourseDto;
import com.hcl.course.entity.Course;
import com.hcl.course.exception.RecordNotFoundException;
import com.hcl.course.repository.CourseRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Laxman
 *
 */
@Slf4j
@Service
public class CourseServiceImpl implements CourseService {

	@Value("${enrolled.course.url}")
	private String enrolledCourseUrl;

	/**
	 * Autowiring CourseRepository
	 */
	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private RestTemplate restTemplate;

	/**
	 */
	@Override
	public List<CourseDto> getAllCourse(Integer registrationId) {

		log.info("CourseServiceImpl :: getAllCourse --- START -----");

		List<Integer> courseIds = getCoursesIdByRegistrationId(registrationId);

		List<CourseDto> courseDtos = new ArrayList<>();
		Optional<List<Object[]>> optionalCources = courseRepository.findCourseNameAndDuration(courseIds);
		if (optionalCources.isPresent()) {
			optionalCources.get().stream()
					.forEach(object -> courseDtos.add(CourseDto.builder().courseId((Integer) object[0])
							.courseName(object[1].toString()).courseDuration((Integer) object[2]).build()));
		}

		log.info("CourseServiceImpl :: getAllCourse --- END -----");
		return courseDtos;
	}

	public List<Integer> getCoursesIdByRegistrationId(Integer registrationId) {

		/*
		 * List<EnrolledCourse> enrolledCourses =
		 * restTemplate.exchange("http://10.117.189.245:8080/courses/" + registrationId,
		 * HttpMethod.GET, null, new ParameterizedTypeReference<List<EnrolledCourse>>()
		 * { }).getBody();
		 * 
		 * List<Integer> courseIds = enrolledCourses.stream().map(ex ->
		 * ex.getCourseId()).collect(Collectors.toList());
		 */
//		return courseIds;
		return new ArrayList<>(Arrays.asList(1, 3));
	}

	@Override
	public CourseDetailDto getCourseDetailById(Integer courseId) {

		log.info("CourseServiceImpl :: getCourseDetailById --- START -----{}", courseId);

		CourseDetailDto courseDetailDto = new CourseDetailDto();
		Optional<Course> course = courseRepository.findById(courseId);
		if (course.isPresent()) {
			BeanUtils.copyProperties(course.get(), courseDetailDto);
		}

		log.info("CourseServiceImpl :: getCourseDetailById --- END -----");
		return courseDetailDto;
	}

	@Override
	public List<CourseDetailDto> getCoursesById(List<Integer> courseIds) {

		log.info("CourseServiceImpl :: getCoursesById --- START -----");

		Optional<List<Course>> courses = courseRepository.findByCourseIdIn(courseIds);
		List<CourseDetailDto> courseDetailDtos = new ArrayList<>();
		if (!courses.isPresent()) {
			throw new RecordNotFoundException("No record found for input course Ids.");
		}

		courses.get().stream().forEach(course -> 
			courseDetailDtos.add(CourseDetailDto.builder().courseDuration(course.getCourseDuration())
					.courseFee(course.getCourseFee()).courseId(course.getCourseId()).courseName(course.getCourseName())
					.description(course.getDescription()).build()));

		log.info("CourseServiceImpl :: getCoursesById --- END -----");
		return courseDetailDtos;
	}

}
