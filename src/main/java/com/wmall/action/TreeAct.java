package com.wmall.action;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wmall.manager.TreeMng;

@Controller
public class TreeAct {

	
	@RequestMapping(value="/tree/add.jhtml")
	public void add(HttpServletRequest request,ModelMap model,String username,String creatorusername){
		manager.add(username,  creatorusername);
	}
	
	@RequestMapping(value="/tree/getTree.html")
	public void getTree(HttpServletRequest request,ModelMap model,String username){
		model.addAttribute("trees", manager.getByTree(username,1));
	}
	
	@RequestMapping(value="/tree/getSummaryForRank.html")
	public void  getSummaryForRank(HttpServletRequest request,ModelMap model,String username){
		model.addAttribute("rankNum", manager.getSummaryForRank(username));
	}
	
	@RequestMapping(value="/tree/getChildForCount.html")
	public void getChildForCount(HttpServletRequest request,ModelMap model,String username){
		model.addAttribute("treesCount", manager.getChildForCount(username));
	}
	
	@RequestMapping(value="/tree/remove.jhtml")
	public void remove(HttpServletRequest request,ModelMap model,String username){
		manager.remove(username);
	}
	
	@Autowired
	private TreeMng manager;
}
