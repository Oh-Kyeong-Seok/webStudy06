package com.springboard.board.vo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default;
import com.springboard.validate.grouphint.DeleteGroup;
import com.springboard.validate.grouphint.UpdateGroup;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(of="boNo")
public class FreeBoardVO implements Serializable{
   private int rnum;	
   
   private int filecnt;
	
   @NotNull(groups = {UpdateGroup.class, DeleteGroup.class})
   private int boNo;
   @NotBlank
   private String boTitle;
   @NotBlank
   private String boWriter;
   @NotBlank
   private String boIp;
   @Email
   private String boMail;
   @NotBlank(groups = {Default.class, DeleteGroup.class})
   @JsonIgnore
   private transient String boPass;
   @ToString.Exclude
   private String boContent;
   private LocalDateTime boDate;
   private int boHit;
   
   private MultipartFile[] boFile;
   private List<AttatchVO> attatchList;		// has many
   public void setBoFile(MultipartFile[] boFile) {
	   if(boFile != null) {
		   this.boFile = Arrays.stream(boFile)
					   		.filter((f)->!f.isEmpty())
					   		.toArray(MultipartFile[]::new);
	   }
	   if(this.boFile != null) {
		   this.attatchList = Arrays.stream(this.boFile)
			   						.map((f)->new AttatchVO(f))
			   						.collect(Collectors.toList());
	   }
}
}