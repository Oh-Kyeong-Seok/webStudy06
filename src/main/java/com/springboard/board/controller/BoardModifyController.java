package com.springboard.board.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springboard.board.InvalidPasswordException;
import com.springboard.board.service.BoardService;
import com.springboard.board.vo.FreeBoardVO;
import com.springboard.validate.grouphint.UpdateGroup;

@Controller
@RequestMapping("/board/{boNo}/edit")
@SessionAttributes("targetBoard")
public class BoardModifyController {
	
	@Inject
	private BoardService service;
	
	@GetMapping
	public String editForm(@PathVariable int boNo, Model model) {
		if(!model.containsAttribute("targetBoard")) {
			FreeBoardVO targetBoard = service.retrieveBoard(boNo);
			model.addAttribute("targetBoard", targetBoard);			
		}
		return "board/boardEdit";
	}
	
	@PutMapping
	public String boardUpdate(
			@Validated(UpdateGroup.class) @ModelAttribute("targetBoard") FreeBoardVO targetBoard
			, BindingResult errors
			, SessionStatus sessionStatus
			, RedirectAttributes redirectAttributes
	) {
		String viewName = null;
		if(!errors.hasErrors()) {
			try {
				service.modifyBoard(targetBoard);
				viewName = "redirect:/board/{boNo}";
			}catch (InvalidPasswordException e) {
				redirectAttributes.addFlashAttribute("message", e.getMessage());
				viewName = "redirect:/board/{boNo}/edit";
			}	
			
		}else {
			String attrName = BindingResult.MODEL_KEY_PREFIX + "targetBoard";
			redirectAttributes.addFlashAttribute(attrName, errors);
			viewName = "redirect:/board/{boNo}/edit";
		}
		
		return viewName;
	}
}
