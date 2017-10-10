package com.cn.fashion.service.impl;

import java.util.List;
import java.util.Map;

import org.ZfashionFramework.common.model.DicModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn.fashion.mapper.DicMapper;
import com.cn.fashion.service.DicService;


@Service
public class DicServiceImpl implements DicService {

	@Autowired
	private DicMapper dicMapper;
	
	@Override
	public List<String> selectDicList() {
		
		return dicMapper.selectDicList();
	}

	@Override
	public List<Map<String, Object>> selectDic(Map<String, Object> params) {
		
		return dicMapper.selectDic(params);
	}

	@Override
	public Integer insertDic(DicModel dicModel) {
		
		Integer aaa = dicMapper.insertDic(dicModel);
		System.out.println("aaa ======" + aaa);
		return aaa;
	}

}
