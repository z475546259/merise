package net.merise.platform.web.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.merise.platform.orm.base.Turn;
import net.merise.platform.service.TurnService;
import net.merise.platform.utils.Coder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/trun")
@Controller
@Transactional
public class TurnController {
	
	@Autowired
	private TurnService turnService;
	
	@RequestMapping("/findTurnByPage")
	@ResponseBody
	public Map<String, Object> findTurnByPage(int page, int rows){
		// 处理分页查询开始记录
		int m = (page - 1) * rows;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("start", m);
		params.put("size", rows);
		
		return turnService.findTurnByPage(params);
	}

	@RequestMapping("/saveTrue")
	@ResponseBody
	public void saveTrun(Turn turn){
		turn.setTurn_id(Coder.getSerialCode20());
		turn.setTurn_time(new Date());
		turnService.saveTrun(turn);
	}
}
