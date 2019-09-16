package com.hcl.course.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CourseDetailDto {

	private Integer courseId;
	private String courseName;
	private Integer courseDuration;
	private double courseFee;
	private String description;
}
