package com.hcl.course.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Laxman
 *
 */
@Setter
@Getter
@Builder
public class CourseDto {

	private Integer courseId;
	private String courseName;
	private Integer courseDuration;
}
