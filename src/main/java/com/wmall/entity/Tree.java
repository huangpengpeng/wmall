package com.wmall.entity;

import javax.persistence.Entity;

import com.wmall.entity.base.BaseTree;

/**
 * 
 insert into zoro.tree ( gmtCreated, gmtModify, leftNum, rankNum, rightNum,
 * username ) values ( now(), now(), 1, 1, 2, 'ROOT' ); 此表需要初始化一个ROOT
 * 所有的节点都是在ROOT下面
 * 
 * @author Administrator
 *
 */
@Entity
public class Tree extends BaseTree{
	
	public Tree(){}
	
	public Tree(String username, Integer leftNum, Integer rightNum) {
		super(username, leftNum, rightNum);
	}
	
	public void init(){}

	private static final long serialVersionUID = -3213843811971900767L;
}
