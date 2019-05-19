package com.example.studentspaceapp.service;

import android.util.Log;

import com.example.studentspaceapp.bean.User;
import com.example.studentspaceapp.utils.DBOpenHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DBService {

    private Connection conn=null; //打开数据库对象
    private PreparedStatement ps=null;//操作整合sql语句的对象
    private ResultSet rs=null;//查询结果的集合

    //DBService 对象
    public static DBService dbService=null;

    /**
     * 构造方法 私有化
     * */

    private DBService(){

    }

    /**
     * 获取MySQL数据库单例类对象
     * */

    public static DBService getDbService(){
        if(dbService==null){
            dbService=new DBService();
        }
        return dbService;
    }


    /**
     * 获取要发送短信的患者信息    查
     * */

    public List<User> getUserData(){
        //结果存放集合
        List<User> list=new ArrayList<>();
        //MySQL 语句
        String sql="select * from test";
        //获取链接数据库对象
        conn= DBOpenHelper.getConn();
        try {
            if(conn!=null&&(!conn.isClosed())){
                ps= conn.prepareStatement(sql);
                if(ps!=null){
                    rs= ps.executeQuery();
                    if(rs!=null){
                        while(rs.next()){
                            User u=new User();
                            u.setId(rs.getString("id"));
                            u.setUserName(rs.getString("username"));
                            u.setPassword(rs.getString("password"));
                            list.add(u);
                            Log.e("DBid",u.getId());
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBOpenHelper.closeAll(conn,ps,rs);//关闭相关操作
        return list;
    }



    /**
     * 批量向数据库插入数据   增
     * */

    public int insertUserData(List<User> list){
        int result=-1;
        if((list!=null)&&(list.size()>0)){
            //获取链接数据库对象
            conn= DBOpenHelper.getConn();
            //MySQL 语句
            String sql="INSERT INTO user (name,phone,content,state) VALUES (?,?,?,?)";
            try {
                boolean closed=conn.isClosed();
                if((conn!=null)&&(!closed)){
                    for(User user:list){
                        ps=  conn.prepareStatement(sql);
                        String username=user.getUserName();
                        String password = user.getPassword();
                        ps.setString(1,username);//第一个参数 name 规则同上
                        ps.setString(2,password);//第二个参数 phone 规则同上
                        result=ps.executeUpdate();//返回1 执行成功
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        DBOpenHelper.closeAll(conn,ps);//关闭相关操作
        return result;
    }





}
