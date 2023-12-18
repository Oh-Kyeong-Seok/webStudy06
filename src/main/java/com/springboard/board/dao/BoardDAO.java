package com.springboard.board.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.springboard.board.vo.FreeBoardVO;
import com.springboard.paging.vo.PaginationInfo;

@Mapper
public interface BoardDAO {
	
	/**
	 * 게시글 추가
	 * @param board
	 * @return
	 */
	public int insertBoard(FreeBoardVO board);
	
	/**
	 * 게시글 조회
	 * @param boNo
	 * @return
	 */
	public FreeBoardVO selectBoard(int boNo);
	
	/**
	 * 조회수 증가
	 * @param boNo
	 * @return
	 */
	public int incrementHit(int boNo);
	
	/**
	 * 검색 조건에 맞는 게시글 수 조회
	 * @param paging
	 * @return
	 */
	public int selectTotalRecord(PaginationInfo<FreeBoardVO> paging);
	
	/**
	 * 검색 조건에 맞는 게시글 목록 조회
	 * @param paging
	 * @return
	 */
	public List<FreeBoardVO> selectBoardList(PaginationInfo<FreeBoardVO> paging);
	
	public int updateBoard(FreeBoardVO board);
	
	public int deleteBoard(int boNo);
}
