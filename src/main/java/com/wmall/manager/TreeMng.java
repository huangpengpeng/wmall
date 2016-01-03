package com.wmall.manager;

import java.util.List;

import com.wmall.entity.Tree;

public interface TreeMng {

	Tree get(Long id);
	
	
	Tree getByUsername(String username);
	
	/**
	 * 给父节点增加子节点
	 * @param username
	 * @param creatorusername  父节点昵称
	 */
	public void add(String username,String creatorusername) ;
	
	/**
	 * 获取下面所有的子节点
	 * @param username
	 * @return
	 */
	List<Tree>  getByTree(String username,Integer rankNum);
	
	/**
	 * 获取总共多少级数
	 * @param username
	 * @return
	 */
	Integer getSummaryForRank(String username);
	
	/**
	 * 获取某个用户下面所有子节点的数量
	 * @param username
	 * @return
	 */
	Integer getChildForCount(String username);
	
	/**
	 * 删除一个节点并移动节点到其父节点
	 * @param username
	 */
	void  remove(String username);
}
