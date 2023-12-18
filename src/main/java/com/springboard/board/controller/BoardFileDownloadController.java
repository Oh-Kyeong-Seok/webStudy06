package com.springboard.board.controller;

import java.io.IOException;
import java.nio.charset.Charset;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import com.springboard.board.service.BoardService;
import com.springboard.board.vo.AttatchVO;

@Controller
public class BoardFileDownloadController {
	@Inject
	private BoardService service;
	
	@Value("#{appInfo.boFiles}")
	private Resource boFiles;
	
	@GetMapping("/board/{boNo}/boFile/{attNo}")
	public ResponseEntity<Resource> download(@PathVariable int attNo) throws IOException {
		AttatchVO atch = service.retrieveAttatch(attNo);
		
		Resource boFile = boFiles.createRelative(atch.getAttSavename());
		if(!boFile.exists()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 파일이 없습니다.");
		}
		
		HttpHeaders headers = new HttpHeaders();
		ContentDisposition disposition = ContentDisposition.attachment()
												.filename(atch.getAttFilename(), Charset.defaultCharset())
												.build();
		headers.setContentDisposition(disposition);		// 여기까지 다운로드과정
		headers.setContentLength(atch.getAttFilesize());
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		
		return ResponseEntity.ok()
							.headers(headers)
							.body(boFile);
	}
}
