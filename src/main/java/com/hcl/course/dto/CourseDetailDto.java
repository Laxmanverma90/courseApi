package com.hcl.course.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseDetailDto {

	private Integer courseId;
	private String courseName;
	private Integer courseDuration;
	private double courseFee;
	private String description;
}
