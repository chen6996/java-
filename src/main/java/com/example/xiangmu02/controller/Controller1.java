package com.example.xiangmu02.controller;

import com.example.xiangmu02.entity.NewsType;
import com.example.xiangmu02.entity.Users;
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
public class Controller1 {

    @Autowired   //使用类的对象  (组件注入)  依赖注入  //接口作为属性  //一个controller下可以有多个Autowired接口
    private UsersServer usersServer;

    @RequestMapping(value = "selectUsers", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult select1(@RequestBody Users users) {
        Users users1 = usersServer.selectUsers(users);
        if (usersServer.selectUsers(users) == null) {
            return new AjaxResult(false, "您输入的账号或密码不正确,请重新输入");
        } else {
            return new AjaxResult(true, users1.getUsername());
        }
    }

    @RequestMapping(value = "selectall", method = RequestMethod.PATCH)
    @ResponseBody
    public PageResult<NewsType> selectall(@RequestBody UserQuery userQuery) {
        return usersServer.分页查询(userQuery);
    }



    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public AjaxResult delete(@PathVariable("id") Integer id) {
        try {
            usersServer.shanchu(id);
            return new AjaxResult();
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult(false, "产生异常:" + e.getMessage());
        }
    }

    @RequestMapping(value = "/batchDelete", method = RequestMethod.PATCH)
    @ResponseBody
    public AjaxResult batchDelete(@RequestBody List<NewsType> users) {
        try {
            usersServer.batchDelete(users);
            return new AjaxResult();
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult(false, "产生异常:" + e.getMessage());
        }
    }

    @RequestMapping(value = "/save", method = RequestMethod.PUT)
    @ResponseBody
    public AjaxResult save(@RequestBody NewsType u) {
        try {
            Date date = new Date();
            u.setCreateTime(date);
            System.out.println(u.getCreateTime());
            usersServer.save(u);
            return new AjaxResult();
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult(false, "产生异常:" + e.getMessage());
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult update(@RequestBody NewsType u) {
        try {
            Date date = new Date();
            u.setCreateTime(date);
            usersServer.bianji(u);
            return new AjaxResult();
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult(false, "产生异常:" + e.getMessage());
        }
    }


}
