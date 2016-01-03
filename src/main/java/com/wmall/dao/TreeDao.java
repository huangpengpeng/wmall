package com.wmall.dao;

import java.util.List;

import com.wmall.entity.Tree;

public interface TreeDao {

	long add(Tree tree);
	
	Tree get(Long id);
	
	void update(Long id,String username, Integer leftNum, Integer rightNum,Integer rankNum);
	
	void addLeft(Long id,Integer num);
	
	void addRight(Long id,Integer num);
	
	/**
	 * 更新左下标值
	 */
	public int updateLeftNum(Integer rightNum, Long excludeId) ;

	public int updateRightNum(Integer leftNum, Long excludeId);
	
	void update(Long id, Integer rankNum);
	
	Tree getByUsername(String username);

	List<Tree> getByRight(Integer right, Long excludeId);
	
	List<Tree> getByLeft(Integer right, Long excludeId);
	
	Tree getByLeft(Integer left);
	
	Integer getRankById(Long id);
	/**
	 * 获取下面所有的子节点
	 * @param username
	 * @return
	 */
	List<Tree>  getByTree(Integer left, Integer right, Integer rankNum);
	
	List<Tree>  getByTree(Integer left, Integer right);
	
	List<Tree>  getByLeftForTree(Integer left, Integer right);
	
	List<Tree>  getByLeftForTree(Integer left);
	
	List<Tree>  getByRightForTree(Integer right);
	/**
	 * 获取总共多少级数
	 * @param username
	 * @return
	 */
	Integer getSummaryForRank(Integer left, Integer right);
	
	/**
	 * 获取某个用户下面所有子节点的数量
	 * @param username
	 * @return
	 */
	Integer getChildForCount(String username);
	
	void delete(Long id);
}
