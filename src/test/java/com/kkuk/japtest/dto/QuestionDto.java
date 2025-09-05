package com.kkuk.japtest.dto;

import java.time.LocalDateTime;

import com.kkuk.japtest.entity.Questiontbl;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDto {

	
	private Long qnum;
	private String qtitle;
	private String qcontent;
	private LocalDateTime qdate;
	private LocalDateTime udate;
	
	// Dto 객체에 들어온 값으로 entity 객체를 만들어서 반환(DTO -> Entity)
	public Questiontbl getEntity() { 
		Questiontbl questiontbl = new Questiontbl();
		questiontbl.setQtitle(this.qtitle);
		questiontbl.setQcontent(this.qcontent);
		return questiontbl;
	}
	
}
