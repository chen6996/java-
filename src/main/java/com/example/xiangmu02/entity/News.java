package com.example.xiangmu02.entity;

import lombok.Data;

import java.util.Date;

@Data
public class News {
    private int newsId;
    private String newsTitle;
    private String newsContent;
    private Date createTime;
    private int newsTypeId;
    private NewsType newsType;

}
