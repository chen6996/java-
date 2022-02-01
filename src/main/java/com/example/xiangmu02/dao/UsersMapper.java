package com.example.xiangmu02.dao;

import com.example.xiangmu02.entity.News;
import com.example.xiangmu02.entity.NewsType;
import com.example.xiangmu02.entity.Users;
import com.example.xiangmu02.util.UserQuery;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
//@Repository
public interface UsersMapper {
    //    SELECT * FROM users WHERE username='oracle' AND PASSWORD='222'
    @Select("select * from users where username=#{username} AND password=#{password}")
    Users selectUsers(Users e);
    @Select("select * from newstype")
    List<NewsType> selectAll();

    List<NewsType> selectAllByQuery(UserQuery uq);
    List<News> selectAllByQuery2(UserQuery uq);
    Long selectCountByQuery(UserQuery uq);
    Long selectCountByQuery2(UserQuery uq);
    void batchDelete(List<NewsType> list);
    @Delete("delete from newstype where newsTypeId=#{newsTypeId} ")
    int  deleteEmp(Integer newsTypeId);
    @Insert("insert into newstype values(null,#{newsTypeName},#{newsTypeExplain},#{createTime})")
    int  insertNewsType(NewsType e);

    //xml文件里写了
    int  updateNewsType(NewsType e);
    @Insert("insert into news values(null,#{newsTitle},#{newsContent},#{createTime},#{newsTypeId})")
    int  insertNews(News e);

    //xml文件里写了
    int  updateNews(News e);
    @Delete("delete from news where newsId=#{newsId} ")
    int  deleteNews(Integer newsId);
    void batchDelete2(List<News> list);
}
