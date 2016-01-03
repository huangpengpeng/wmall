package com.wmall.entity.base;

import javax.persistence.MappedSuperclass;

import com.common.jdbc.VersionEntity;

@MappedSuperclass
public class BaseTree extends VersionEntity{

	private static final long serialVersionUID = -4063085597477687360L;
	
	public BaseTree(){}
	
	public BaseTree(String username,Integer leftNum,Integer rightNum){
		this.setUsername(username);
		this.setLeftNum(leftNum);
		this.setRightNum(rightNum);
		this.setRankNum(rightNum);
	}

	private String username;
	
	private Integer leftNum;
	
	private Integer rightNum;
	
	private Integer rankNum;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getLeftNum() {
		return leftNum;
	}

	public void setLeftNum(Integer leftNum) {
		this.leftNum = leftNum;
	}

	public Integer getRightNum() {
		return rightNum;
	}

	public void setRightNum(Integer rightNum) {
		this.rightNum = rightNum;
	}

	public Integer getRankNum() {
		return rankNum;
	}

	public void setRankNum(Integer rankNum) {
		this.rankNum = rankNum;
	}
}
