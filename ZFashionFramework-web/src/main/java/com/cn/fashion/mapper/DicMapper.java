package com.cn.fashion.mapper;

import java.util.List;
import java.util.Map;

import org.ZfashionFramework.common.model.DicModel;

public interface DicMapper {
	
	List<String> selectDicList();
	
	List<Map<String, Object>> selectDic(Map<String, Object> params);
	
	Integer insertDic(DicModel dicModel);
	
}
