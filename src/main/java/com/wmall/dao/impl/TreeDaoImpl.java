package com.wmall.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.common.jdbc.JdbcTemplateBaseDao;
import com.common.jdbc.SqlBuilder;
import com.wmall.dao.TreeDao;
import com.wmall.entity.Tree;

@Repository
public class TreeDaoImpl extends JdbcTemplateBaseDao implements TreeDao {

	public long add(Tree tree) {
		return super.add(tree);
	}

	public Tree get(Long id) {
		return super.queryForObject(id);
	}

	public void update(Long id, String username, Integer leftNum,
			Integer rightNum, Integer rankNum) {
		SqlBuilder sqlBuilder = new SqlBuilder(
				"update Tree set gmtModify=current_timestamp()");
		if (sqlBuilder.ifNotNull(username)) {
			sqlBuilder.set("username", username);
		}
		if (sqlBuilder.ifNotNull(leftNum)) {
			sqlBuilder.set("leftNum", leftNum);
		}
		if (sqlBuilder.ifNotNull(rightNum)) {
			sqlBuilder.set("rightNum", rightNum);
		}
		if (sqlBuilder.ifNotNull(rankNum)) {
			sqlBuilder.set("rankNum", rankNum);
		}
		super.update(id, sqlBuilder);
	}

	@Override
	protected Class<?> getEntityClass() {
		return Tree.class;
	}

	public Tree getByUsername(String username) {
		SqlBuilder sqlBuilder = new SqlBuilder("select * from Tree where 1=1");
		if (sqlBuilder.ifNotNull(username)) {
			sqlBuilder.andEqualTo("username", username);
		}
		return super.queryForObject(sqlBuilder);
	}

	public List<Tree> getByRight(Integer right, Long excludeId) {
		SqlBuilder sqlBuilder = new SqlBuilder("select * from Tree where 1=1");
		if (sqlBuilder.ifNotNull(excludeId)) {
			sqlBuilder.andNotEqualTo("id", excludeId);
		}
		if (sqlBuilder.ifNotNull(right)) {
			sqlBuilder.andGreaterThanOrEqualTo("rightNum", right);
		}
		return query(sqlBuilder);
	}

	public List<Tree> getByLeft(Integer left, Long excludeId) {
		SqlBuilder sqlBuilder = new SqlBuilder("select * from Tree where 1=1");
		if (sqlBuilder.ifNotNull(excludeId)) {
			sqlBuilder.andNotEqualTo("id", excludeId);
		}
		if (sqlBuilder.ifNotNull(left)) {
			sqlBuilder.andGreaterThanOrEqualTo("leftNum", left);
		}
		return query(sqlBuilder);
	}

	public Integer getRankById(Long id) {
		SqlBuilder sqlBuilder = new SqlBuilder(
				"SELECT COUNT(parent.id)+1 FROM  Tree AS node , Tree AS parent WHERE node.leftNum > parent.leftNum AND node.leftNum < parent.rightNum AND node.id = ?");
		sqlBuilder.setParam(id);
		return queryForPrimitive(sqlBuilder, Integer.class);
	}

	public void addLeft(Long id, Integer num) {
		SqlBuilder sqlBuilder = new SqlBuilder(
				"update Tree set gmtModify=current_timestamp() , leftNum=leftNum+?")
				.setParam(num);
		super.update(id, sqlBuilder);
	}

	public void addRight(Long id, Integer num) {
		SqlBuilder sqlBuilder = new SqlBuilder(
				"update Tree set gmtModify=current_timestamp() , rightNum=rightNum+?")
				.setParam(num);
		super.update(id, sqlBuilder);
	}

	public void update(Long id, Integer rankNum) {
		SqlBuilder sqlBuilder = new SqlBuilder(
				"update Tree set gmtModify=current_timestamp()");
		if (sqlBuilder.ifNotNull(rankNum)) {
			sqlBuilder.set("rankNum", rankNum);
		}
		super.update(id, sqlBuilder);
	}


	public List<Tree> getByTree(Integer left, Integer right, Integer rankNum) {
		SqlBuilder sqlBuilder=new SqlBuilder("SELECT * FROM Tree WHERE leftNum > ? AND rightNum < ? AND rankNum <= ? ORDER BY leftNum asc");
		sqlBuilder.setParam(left);
		sqlBuilder.setParam(right);
		sqlBuilder.setParam(rankNum);
		return super.query(sqlBuilder);
	}
	
	public List<Tree> getByLeftForTree(Integer left, Integer right) {
		SqlBuilder sqlBuilder=new SqlBuilder("SELECT * FROM Tree WHERE leftNum BETWEEN ? AND ?  ORDER BY leftNum asc");
		sqlBuilder.setParam(left);
		sqlBuilder.setParam(right);
		return super.query(sqlBuilder);
	}

	public List<Tree> getByTree(Integer left, Integer right) {
		SqlBuilder sqlBuilder = new SqlBuilder(
				"SELECT * FROM Tree WHERE leftNum > ? AND rightNum < ? ORDER BY leftNum asc");
		sqlBuilder.setParam(left);
		sqlBuilder.setParam(right);
		return super.query(sqlBuilder);
	}
	
	public Integer getSummaryForRank(Integer left, Integer right) {
		SqlBuilder sqlBuilder = new SqlBuilder(
				"SELECT count(DISTINCT  rankNum) FROM Tree WHERE leftNum > ? AND rightNum < ? ");
		sqlBuilder.setParam(left);
		sqlBuilder.setParam(right);
		return super.queryForPrimitive(sqlBuilder, Integer.class);
	}

	public Integer getChildForCount(String username) {
		SqlBuilder sqlBuilder = new SqlBuilder(
				"SELECT  (rightNum-leftNum-1) / 2 FROM Tree WHERE username=?");
		sqlBuilder.setParam(username);
		return super.queryForPrimitive(sqlBuilder, Integer.class);
	}

	public void delete(Long id) {
		super.delete(id);
	}

	public Tree getByLeft(Integer left) {
		SqlBuilder sqlBuilder=new SqlBuilder("select * from Tree where 1=1");
		if(sqlBuilder.ifNotNull(left)){
			sqlBuilder.andEqualTo("leftNum", left);
		}
		return queryForObject(sqlBuilder);
	}

	public List<Tree> getByLeftForTree(Integer left) {
		SqlBuilder sqlBuilder=new SqlBuilder("select * from Tree where 1=1 and leftNum > ?");
		sqlBuilder.setParam(left);
		return query(sqlBuilder);
	}

	public List<Tree> getByRightForTree(Integer right) {
		SqlBuilder sqlBuilder=new SqlBuilder("select * from Tree where 1=1 and rightNum > ?");
		sqlBuilder.setParam(right);
		return query(sqlBuilder);
	}

	public int updateLeftNum(Integer rightNum, Long excludeId) {
		SqlBuilder sqlBuilder = new SqlBuilder(
				"UPDATE Tree SET leftNum = leftNum + 2 WHERE leftNum >= ? AND  id != ?");
		sqlBuilder.setParam(rightNum);
		sqlBuilder.setParam(excludeId);
		return super.update(sqlBuilder);
	}

	public int updateRightNum(Integer leftNum, Long excludeId) {
		SqlBuilder sqlBuilder=new SqlBuilder("UPDATE Tree SET rightNum = rightNum + 2 WHERE rightNum >= ? AND id != ?");
		sqlBuilder.setParam(leftNum);
		sqlBuilder.setParam(excludeId);
		return super.update(sqlBuilder);
	}
}
