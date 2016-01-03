package com.wmall.manager.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.wmall.dao.TreeDao;
import com.wmall.entity.Tree;
import com.wmall.manager.TreeMng;

@Transactional(isolation = Isolation.REPEATABLE_READ)
@Service
public class TreeMngImpl implements TreeMng {
	
	private Logger log=LoggerFactory.getLogger(TreeMngImpl.class);

	/**
	 * (1) 获取新节点的左右下标值：
	 * 
	 * 左下标值 = 父节点的右下标值； 右下标值 = 父节点的右下标值+1;
	 * 
	 * (2) 更新： 大于等于新增节点的左下标值的右下标值，都加个2；
	 * 
	 * (3) 更新：大于等于新增节点的右下标值的左下标值，都加个2；
	 * 
	 */
	public void add(String username, String creatorusername) {
		Tree creatorTree = dao.getByUsername(creatorusername);
		Tree tree = new Tree(username, creatorTree.getRightNum(),
				creatorTree.getRightNum() + 1);
		tree.init();
		tree.setId(dao.add(tree));

		List<Tree> trees = dao.getByRight(tree.getLeftNum(), tree.getId());
		for (Tree tree2 : trees) {
			dao.addRight(tree2.getId(), 2);
		}
		log.info("RIGHT：{}",trees.size());
		
		trees = dao.getByLeft(tree.getRightNum(), tree.getId());
		for (Tree tree2 : trees) {
			dao.addLeft(tree2.getId(), 2);
		}
		log.info("：{}",trees.size());
		
		Integer rankNum = dao.getRankById(tree.getId());
		dao.update(tree.getId(), rankNum);
	}
	
	
	public static void main(String [] args){}

	public Tree get(Long id) {
		return dao.get(id);
	}

	public Tree getByUsername(String username) {
		return dao.getByUsername(username);
	}

	public List<Tree> getByTree(String username,Integer rankNum) {
		Tree creatorTree = dao.getByUsername(username);
		return dao.getByTree(creatorTree.getLeftNum(),
				creatorTree.getRightNum(), creatorTree.getRankNum() + rankNum);
	}

	public Integer getSummaryForRank(String username) {
		Tree creatorTree = dao.getByUsername(username);
		return dao.getSummaryForRank(creatorTree.getLeftNum(),
				creatorTree.getRightNum());
	}
	
	public Integer getChildForCount(String username) {
		return dao.getChildForCount(username);
	}
	

	public void remove(String username) {
		Tree tree = dao.getByUsername(username);
		dao.delete(tree.getId());// 执行删除
		// 获取所有子节点
		List<Tree> trees = dao.getByLeftForTree(tree.getLeftNum(),
				tree.getRightNum());
		for (Tree tree2 : trees) {
			dao.update(tree2.getId(), tree2.getUsername(),
					tree2.getLeftNum() - 1, tree2.getRightNum() - 1,
					tree2.getRankNum());
		}
		List<Tree> rightTrees = dao.getByRightForTree(tree.getRightNum());
		for (Tree tree2 : rightTrees) {
			dao.update(tree2.getId(), tree2.getUsername(), tree2.getLeftNum(),
					tree2.getRightNum() - 2, tree2.getRankNum());
		}
		List<Tree> leftTrees = dao.getByLeftForTree(tree.getRightNum());
		for (Tree tree2 : leftTrees) {
			dao.update(tree2.getId(), tree2.getUsername(),
					tree2.getLeftNum() - 2, tree2.getRightNum(),
					tree2.getRankNum());
		}
		for (Tree tree2 : trees) {
			dao.update(tree2.getId(), dao.getRankById(tree2.getId()));
		}
		for (Tree tree2 : rightTrees) {
			dao.update(tree2.getId(), dao.getRankById(tree2.getId()));
		}
		for (Tree tree2 : leftTrees) {
			dao.update(tree2.getId(), dao.getRankById(tree2.getId()));
		}
	}
	
	@Autowired
	private TreeDao dao;
}
