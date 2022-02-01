package com.example.xiangmu02.controller;

import com.example.xiangmu02.entity.News;
import com.example.xiangmu02.entity.NewsType;
import com.example.xiangmu02.server.UsersServer;
import com.example.xiangmu02.util.AjaxResult;
import com.example.xiangmu02.util.PageResult;
import com.example.xiangmu02.util.UserQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin  //跨域访问
@Controller
public class Controller2 {
    @Autowired   //使用类的对象  (组件注入)  依赖注入  //接口作为属性  //一个controller下可以有多个Autowired接口
    private UsersServer usersServer;
    @RequestMapping(value = "selectall2", method = RequestMethod.PATCH)
    @ResponseBody
    public PageResult<News> selectall2(@RequestBody UserQuery userQuery) {
        PageResult<News> newsPageResult = usersServer.分页查询2(userQuery);
        return newsPageResult;
    }
    @RequestMapping(value = "/save2", method = RequestMethod.PUT)
    @ResponseBody
    public AjaxResult save2(@RequestBody News u) {
        try {
            Date date = new Date();
            u.setCreateTime(date);
            System.out.println(u.getCreateTime());
            usersServer.save2(u);
            return new AjaxResult();
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult(false, "产生异常:" + e.getMessage());
        }
    }

    @RequestMapping(value = "/update2", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult update2(@RequestBody News u) {
        try {
            Date date = new Date();
            u.setCreateTime(date);
            usersServer.bianji2(u);
            return new AjaxResult();
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult(false, "产生异常:" + e.getMessage());
        }
    }
    @RequestMapping(value = "/delete2/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public AjaxResult delete2(@PathVariable("id") Integer id) {
        try {
            usersServer.shanchu2(id);
            return new AjaxResult();
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult(false, "产生异常:" + e.getMessage());
        }
    }

    @RequestMapping(value = "/batchDelete2", method = RequestMethod.PATCH)
    @ResponseBody
    public AjaxResult batchDelete2(@RequestBody List<News> users) {
        try {
            usersServer.batchDelete2(users);
            return new AjaxResult();
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult(false, "产生异常:" + e.getMessage());
        }
    }
}
