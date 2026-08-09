package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.util.IdGenerator;

import java.util.ArrayList;
import java.util.List;

/**
 * Service managing Course in-memory data using ArrayList.
 */
public class CourseService {
    private final List<Course> courses;

    public CourseService() {
        this.courses = new ArrayList<>();
    }

    /**
     * Adds an existing course object.
     */
    public void addCourse(Course course) throws InvalidInputException {
        if (course == null) {
            throw new InvalidInputException("Course cannot be null.");
        }
        courses.add(course);
    }

    /**
     * Method Overloading: Creates and adds course using individual parameters.
     */
    public Course addCourse(String courseName, String description, int durationInWeeks) throws InvalidInputException {
        if (courseName == null || courseName.trim().isEmpty()) {
            throw new InvalidInputException("Course name is required.");
        }
        if (durationInWeeks <= 0) {
            throw new InvalidInputException("Course duration must be greater than 0 weeks.");
        }
        int id = IdGenerator.getNextCourseId();
        Course course = new Course(id, courseName.trim(), description.trim(), durationInWeeks);
        courses.add(course);
        return course;
    }

    /**
     * Retrieves course by ID. Throws EntityNotFoundException if missing.
     */
    public Course getCourseById(int id) throws EntityNotFoundException {
        for (Course course : courses) {
            if (course.getId() == id) {
                return course;
            }
        }
        throw new EntityNotFoundException("Course with ID " + id + " was not found.");
    }

    /**
     * Returns list of all courses.
     */
    public List<Course> getAllCourses() {
        return new ArrayList<>(courses);
    }

    /**
     * Toggles course active status (activate/deactivate).
     */
    public void toggleCourseStatus(int id) throws EntityNotFoundException {
        Course course = getCourseById(id);
        course.setActive(!course.isActive());
    }
}
