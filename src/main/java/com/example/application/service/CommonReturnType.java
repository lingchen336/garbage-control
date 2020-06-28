package com.example.application.service;


public class CommonReturnType {
    //表明请求处理的结果“success""fail"
    private String staus;
    //若返回结果错误，则返回通用的错误码格式
    private Object data;

    public static CommonReturnType creat(Object result){
        return CommonReturnType.creat(result,"success");
    }

    public static CommonReturnType creat(Object result,String status){
        CommonReturnType type=new CommonReturnType();
        type.setStaus(status);
        type.setData(result);
        return type;
    }
    public String getStaus() {
        return staus;
    }

    public void setStaus(String staus) {
        this.staus = staus;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
