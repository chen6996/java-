package com.example.xiangmu02.entity;

import lombok.Data;

import java.util.Date;

@Data
public class NewsType {
    private int newsTypeId;
    private String newsTypeName;
    private String newsTypeExplain;
    private Date createTime;

}
