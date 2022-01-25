package com.kh.app15.board.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.kh.app15.board.entity.BoardDto;

@Repository
//@Component 가능
public class BoardRepositoryImpl implements BoardRepository {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int insert(BoardDto b) throws Exception {
		return sqlSession.insert("board.insert", b);
	}

	@Override
	public List<BoardDto> selectList() {
		return sqlSession.selectList("board.selectAll");
	}

	@Override
	public int edit(BoardDto b) {
		return sqlSession.update("board.update", b);
	}

	@Override
	public int delete(BoardDto b) {
		return sqlSession.delete("board.delete", b);
	}

}
