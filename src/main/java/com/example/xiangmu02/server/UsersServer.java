package com.example.xiangmu02.server;

import com.example.xiangmu02.entity.News;
import com.example.xiangmu02.entity.NewsType;
import com.example.xiangmu02.entity.Users;
import com.example.xiangmu02.util.PageResult;
import com.example.xiangmu02.util.UserQuery;

import java.util.List;

public interface UsersServer {
 Users selectUsers(Users e);
 List<NewsType> 查询新闻列表();
 PageResult<NewsType> 分页查询(UserQuery uq);
 PageResult<News> 分页查询2(UserQuery uq);
 void shanchu(Integer id);
 void batchDelete(List<NewsType> list);
 int save(NewsType e);
 int bianji(NewsType e);
 int save2(News e);
 int bianji2(News e);
 void shanchu2(Integer id);
 void batchDelete2(List<News> list);
}
