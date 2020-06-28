package com.example.application.service;


import com.example.application.entity.GarbageDO;
import com.example.application.mapper.GarbageDOMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GarbageServiceImpl implements GarbageService {

    @Autowired
    private GarbageDOMapper garbageDOMapper;


    @Override
    public GarbageModel getGarbageById(Integer id) {
        //调用daomapper获取对应用户daoObject
        GarbageDO garbageDO=garbageDOMapper.selectByPrimaryKey(id);
        if(garbageDO==null)
        {
            return null;
        }
        GarbageModel garbageModel=new GarbageModel();
        BeanUtils.copyProperties(garbageDO,garbageModel);

        return garbageModel;
    }

    @Override
    public List<GarbageModel> getGarbageByName(String name) {
       List<GarbageDO> garbageDOList=garbageDOMapper.selectGarbageByName(name);

       List<GarbageModel> garbageModelArrayList=new ArrayList<>();

        for (GarbageDO g:garbageDOList) {
            GarbageModel garbageModel=new GarbageModel();
            BeanUtils.copyProperties(g,garbageModel);
            garbageModelArrayList.add(garbageModel);
        }

        return garbageModelArrayList;
    }
}
