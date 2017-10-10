package com.cn.fashion.service;

import java.util.List;
import java.util.Map;

import org.ZfashionFramework.common.model.DicModel;

public interface DicService {
	
	List<String> selectDicList();
	
	List<Map<String, Object>> selectDic(Map<String, Object> params);
	
	Integer insertDic(DicModel dicModel);

}
