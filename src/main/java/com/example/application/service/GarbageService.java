package com.example.application.service;


import java.util.List;

public interface GarbageService {
    //通过用户id获取用户对象的方法
    GarbageModel getGarbageById(Integer id);
    List<GarbageModel> getGarbageByName(String name);
}
