package com.example.business.entity;

import lombok.Data;

import java.io.Serializable;
@Data
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = -2975579457847445605L;

    private Integer pageSize;

    private Integer pageNo;

}
