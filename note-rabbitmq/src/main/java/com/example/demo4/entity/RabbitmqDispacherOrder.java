package com.example.demo4.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class RabbitmqDispacherOrder implements Serializable {

    private static final long serialVersionUID = -3999179499050229056L;

    private String dispatchId;

    private String orderId;

    private String userId;

    private String status;

    private String orderContent;

    private Date createTime;
}
