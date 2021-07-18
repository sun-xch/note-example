package com.example.demo4.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class RabbitmqOrder implements Serializable {

    private static final long serialVersionUID = -319967792933557720L;

    private String orderId;

    private String userId;

    private String orderContent;

    private Date createTime;
}
