package com.example.demo4.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class RabbitmqOrderMessage implements Serializable {

    private static final long serialVersionUID = 3112919488239132850L;

    private String orderId;

    private String status;

    private String orderContent;

    private String uniqueId;
}
