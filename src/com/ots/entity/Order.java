package com.ots.entity;

public class Order {
    private Long orderId;
    private Long teacherId;
    private Long studentId;
    private Long courseId;


    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    private Float price;
    private Float teacherMoney;
    private Float platformMoney;
    private Integer isPay;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Float getTeacherMoney() {
        return teacherMoney;
    }

    public void setTeacherMoney(Float teacherMoney) {
        this.teacherMoney = teacherMoney;
    }

    public Float getPlatformMoney() {
        return platformMoney;
    }

    public void setPlatformMoney(Float platformMoney) {
        this.platformMoney = platformMoney;
    }

    public Integer getIsPay() {
        return isPay;
    }

    public void setIsPay(Integer isPay) {
        this.isPay = isPay;
    }
}
