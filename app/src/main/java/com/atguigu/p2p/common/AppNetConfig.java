package com.atguigu.p2p.common;

/**
 * Created by Administrator on 2017/6/21.
 */

public class AppNetConfig  {

     /*
    * 面试题 ： 项目中有多少个接口？
    *
    * */

    public static final String HOST ="47.93.118.241";//提供ip地址

    //提供web应用的地址
    public static final String BASE_URL ="http://" +HOST +":8081/P2PInvest/";
    //http://47.93.118.241:8081/P2PInvest/index
    //http://47.93.118.241:8081/P2PInvest/images/index01.png

    public static final String INDEX =BASE_URL +"index";//访问首页数据

    public static final String LOGIN =BASE_URL +"login";//访问登录的url
    //http://47.93.118.241:8081/P2PInvest/login
    public static final String PRODUCT =BASE_URL +"product";//访问“所有理财”的url
    //http://47.93.118.241:8081/P2PInvest/product
    public static final String UPDATE =BASE_URL +"update.json";//访问服务器端当前应用的版本信息

    public static final String REGISTER =BASE_URL +"UserRegister";//注册
    //http://47.93.118.241:8081/P2PInvest/UserRegister
    public static final String FEEDBACK =BASE_URL +"FeedBack";//用户反馈
}
