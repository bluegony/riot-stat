package com.riot.study.config.mybatis;//package com.skplanet.proto.config.mybatis;
//
//import org.apache.ibatis.io.Resources;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.apache.ibatis.session.SqlSessionFactoryBuilder;
//
//import java.io.IOException;
//import java.io.InputStream;
//
///**
// * Created by bluegony on 2018. 1. 29..
// */
//public class ConnectionFactory {
//
//    public static SqlSessionFactory ssf;
//
//    static {
//
//        String resource = "mybatis/config.xml";
//        InputStream inputStream = null;
//
//        try {
//            inputStream = Resources.getResourceAsStream(resource);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        ssf = new SqlSessionFactoryBuilder().build(inputStream);
//
//    }
//
//
//    public static SqlSessionFactory getSqlSessionFactory(){
//        return ssf;
//    }
//
//
//}