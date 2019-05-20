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

    private Double price;
    private Double teacherMoney;
    private Double platformMoney;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getTeacherMoney() {
        return teacherMoney;
    }

    public void setTeacherMoney(Double teacherMoney) {
        this.teacherMoney = teacherMoney;
    }

    public Double getPlatformMoney() {
        return platformMoney;
    }

    public void setPlatformMoney(Double platformMoney) {
        this.platformMoney = platformMoney;
    }

    public Integer getIsPay() {
        return isPay;
    }

    public void setIsPay(Integer isPay) {
        this.isPay = isPay;
    }
}
