package com.example.demo.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity
@ApiModel("学生")
@Table(name = "Student")
public class Student implements Serializable {

    @Id
    @ApiModelProperty("id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ApiModelProperty("name")
    @NotBlank(message = "Name is 姓名")
    @Column(name = "name")
    private String name;

    @ApiModelProperty("email")
    @NotBlank(message = "Email")
    @Column(name = "email")
    private String email;

    @ApiModelProperty("phone_no")
    @NotBlank(message = "phone_no")
    @Column(name = "phone_no")
    private long phoneNo;

    @ApiModelProperty("code")
    @NotBlank(message = "学号")
    @Column(name="code")
    private  String  code;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(long phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
