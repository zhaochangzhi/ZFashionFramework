package com.cn.fashion.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.ZfashionFramework.common.enums.DicTypeEnum;
import org.ZfashionFramework.common.model.DicModel;
import org.ZfashionFramework.common.utils.CommonUtils;
import org.ZfashionFramework.common.utils.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.cn.fashion.service.DicService;

@Controller
@RequestMapping("/dic")
public class DicController {
	
	@Autowired
	private DicService dicService;
	
	/**
	 * 字典设置 列表
	 * @return
	 */
	@RequestMapping("/list")
	private ModelAndView list() {
		
		System.out.println("========== 字典设置 列表 ==========");
		List<String> list = dicService.selectDicList();
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("list", list);
		return new ModelAndView("dic/list", data);
	}
	
	/**
	 * 新增 字典 - 跳转
	 * @return
	 */
	@RequestMapping("/add")
	private ModelAndView add() {
		
		System.out.println("========== 新增 字典 - 跳转 ==========");
		List<String> list = dicService.selectDicList();
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("list", list);
		return new ModelAndView("dic/add", data);
	}
	
	/**
	 * 字典内容
	 * @param type
	 * @return
	 */
	@RequestMapping("/info")
	private ModelAndView info(@Valid String type) {
		
		System.out.println("========== 字典内容 ==========");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("type", type);
		params.put("status", "ENABLE");
		List<Map<String, Object>> list = dicService.selectDic(params);
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("list", list);
		return new ModelAndView("dic/content",data);
	}
	
	/**
	 * ajax 最大字典码
	 * @param type
	 * @return
	 */
	@RequestMapping("/ajaxDicCode")
	private void ajaxDicCode(@Valid String type, HttpServletResponse response) {
		
		System.out.println("========== ajax 最大字典码 ==========");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("type", type);
		params.put("status", "ENABLE");
		List<Map<String, Object>> list = dicService.selectDic(params);
		String code = DicTypeEnum.getPrex(type) + "001"; //默认
		if(!list.isEmpty()) {
			
			code = CommonUtils.generateDicCode(list.get(0).get("code").toString(), type);
		}
		HttpUtils.ajaxOutPrintString(response, code);
	}
	
	/**
	 * 保存 字典
	 * @param dicModel
	 * @return
	 */
	@RequestMapping("/save")
	public void save(@Valid DicModel dicModel, HttpServletResponse response) {
		
		System.out.println("model ==========>>" + JSONObject.toJSONString(dicModel));
		String flag = "error";
		if (dicService.insertDic(dicModel) > 0) {
			
			flag = "success";
		}
		HttpUtils.ajaxOutPrintString(response, flag);
	}
	

}
