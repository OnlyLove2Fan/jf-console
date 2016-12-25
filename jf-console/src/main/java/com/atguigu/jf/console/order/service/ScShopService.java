package com.atguigu.jf.console.order.service;

import java.util.List;

import com.atguigu.jf.console.order.bean.pojo.ScShop;

public interface ScShopService {

	/**
    * 
    * @方法名: selectScShopList  
    * @功能描述:  查询所有商店为下拉框准备
    * @return
    * @throws Exception
    * @作者 syl
    * @日期 2016年12月1日
    */
   List<ScShop> selectScShopList() throws Exception;
}
