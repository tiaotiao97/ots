package com.ots.entity;

import java.awt.geom.FlatteningPathIterator;

public class TeacherCourse {
    private Long teacherId;
    private Long courseId;
    private Float coursePrice;

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Float getCoursePrice() {
        return coursePrice;
    }

    public void setCoursePrice(Float coursePrice) {
        this.coursePrice = coursePrice;
    }
}
