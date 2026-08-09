package com.airtribe.learntrack.entity;

/**
 * Represents an Enrollment linking a Student to a Course.
 */
public class Enrollment {
    private int id;
    private int studentId;
    private int courseId;
    private String enrollmentDate;
    private EnrollmentStatus status;

    public Enrollment() {
        this.status = EnrollmentStatus.ACTIVE;
    }

    public Enrollment(int id, int studentId, int courseId, String enrollmentDate, EnrollmentStatus status) {
        this.id = id;
        this.studentId = studentId;
        this.courseId = courseId;
        this.enrollmentDate = enrollmentDate;
        this.status = status;
    }

    public Enrollment(int id, int studentId, int courseId, String enrollmentDate) {
        this(id, studentId, courseId, enrollmentDate, EnrollmentStatus.ACTIVE);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(String enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public EnrollmentStatus getStatus() {
        return status;
    }

    public void setStatus(EnrollmentStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return String.format("Enrollment [ID: %d] | Student ID: %d | Course ID: %d | Date: %s | Status: %s",
                id, studentId, courseId, enrollmentDate, status);
    }
}
