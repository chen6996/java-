package com.example.xiangmu02.server.impl;

import com.example.xiangmu02.dao.UsersMapper;
import com.example.xiangmu02.entity.News;
import com.example.xiangmu02.entity.NewsType;
import com.example.xiangmu02.entity.Users;
import com.example.xiangmu02.server.UsersServer;
import com.example.xiangmu02.util.PageResult;
import com.example.xiangmu02.util.UserQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UsersServerImpl implements UsersServer {
    @Autowired
    private UsersMapper usersMapper;
    //使用某个类的对象  一个Service下同样可以有多个daoMapper接口 .
    //@Autowired
    //	private StudentMapper  studentMapper;

    @Override
    public Users selectUsers(Users e) {
        return usersMapper.selectUsers(e);
    }

    @Override
    public List<NewsType> 查询新闻列表() {
        return usersMapper.selectAll();
    }

    @Override
    public PageResult<NewsType> 分页查询(UserQuery uq) {
        //先查询分页的总条数
        Long total = usersMapper.selectCountByQuery(uq);

        //判断总条数==0
        if (total == 0L) {
            return new PageResult<NewsType>();
        }
        //else 就取一个emp集合出来
        List<NewsType> result = usersMapper.selectAllByQuery(uq);

        return new PageResult<NewsType>(total, result);
    }

    @Override
    public PageResult<News> 分页查询2(UserQuery uq) {
        //先查询分页的总条数
        Long total = usersMapper.selectCountByQuery2(uq);

        //判断总条数==0
        if (total == 0L) {
            return new PageResult<News>();
        }
        //else 就取一个emp集合出来
        List<News> result = usersMapper.selectAllByQuery2(uq);

        return new PageResult<News>(total, result);
    }

    @Override
    public void shanchu(Integer id) {
        usersMapper.deleteEmp(id);
    }

    @Override
    public void batchDelete(List<NewsType> list) {
        usersMapper.batchDelete(list);
    }
    @Override
    public void shanchu2(Integer id) {
        usersMapper.deleteNews(id);
    }

    @Override
    public void batchDelete2(List<News> list) {
        usersMapper.batchDelete2(list);
    }
    @Override
    public int save(NewsType e) {
        return usersMapper.insertNewsType(e);
    }

    @Override
    public int bianji(NewsType e) {
        return usersMapper.updateNewsType(e);
    }

    @Override
    public int save2(News e) {
        return usersMapper.insertNews(e);
    }

    @Override
    public int bianji2(News e) {
        return usersMapper.updateNews(e);
    }
}
