package com.kh.app15.board.repository;

import java.util.List;

import com.kh.app15.board.entity.BoardDto;

public interface BoardRepository {

	//insert
	public abstract int insert(BoardDto b) throws Exception;

	//selectAll
	public abstract List<BoardDto> selectList();

	//edit
	public abstract int edit(BoardDto b);

	//delete
	public abstract int delete(BoardDto b);
}
