package com.example.application.controller;

import com.example.application.service.GarbageModel;
import com.example.application.service.GarbageService;
import com.example.application.service.GarbageVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


@Controller("garbage")//识别test.html中的object方法
@RequestMapping("/th")
public class ThymeleafController {
    String gar;
    @Autowired
    GarbageService garbageService;

    @Autowired
    private HttpServletRequest httpServletRequest;


    @RequestMapping("/test")
    public String test(ModelMap map){
        GarbageModel garbage=new GarbageModel();
        map.addAttribute("garbage",garbage);
        return "test";
    }

    @PostMapping("postform")
    public String postform(GarbageModel garbage) {
        gar=garbage.getName();
        return "redirect:/th/index";
    }
    @RequestMapping("/index")
    public String index(ModelMap map1){

        List<GarbageModel> garbageModelList=garbageService.getGarbageByName(gar);
        if (garbageModelList.isEmpty()){
            System.out.println("垃圾分类库里缺少的垃圾："+gar);
            return "center";
        }else{
            List<GarbageVO> garbageVOList=new ArrayList<>();
            for (GarbageModel g:garbageModelList) {
                GarbageVO garbageVO=new GarbageVO();
                BeanUtils.copyProperties(g,garbageVO);
                garbageVOList.add(garbageVO);
                System.out.println(garbageVO.getName());
                System.out.println(garbageVO.getBelong());
                map1.addAttribute("garbageVO",garbageVO);
            }
            return "index";
        }
    }


    //    @RequestMapping("/get")
//    @ResponseBody
//    public GarbageModel getUser(@RequestParam(name="id") Integer id) {  //@@RequestParam注释用于重新命名{name=""}字段
//        //调用service服务，获取对应id用户对象返回给前端
//        GarbageModel garbageModel= garbageService.getGarbageById(id);
//
//        //若获取对应用户信息不存在
//        if(garbageModel==null){
//            garbageModel.setId(122343);
//        }
//        //返回通用对象
//        return garbageModel;
//    }



//    @GetMapping("err")
//    @ResponseBody
//    public CommonReturnType postform(String garbage) {
//
//    //返回VO界面
//        List<GarbageModel> garbageModelList=garbageService.getGarbageByName(garbage);
//        List<GarbageVO> garbageVOList=new ArrayList<>();
//        for (GarbageModel g:garbageModelList) {
//            GarbageVO garbageVO=new GarbageVO();
//            BeanUtils.copyProperties(g,garbageVO);
//            garbageVOList.add(garbageVO);
//        }
//
//        return CommonReturnType.creat(garbageVOList);
//    }

}