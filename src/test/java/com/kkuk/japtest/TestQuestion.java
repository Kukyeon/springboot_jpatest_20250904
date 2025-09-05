package com.kkuk.japtest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kkuk.japtest.dto.QuestionDto;
import com.kkuk.japtest.entity.Questiontbl;
import com.kkuk.japtest.repository.QuestionRepository;

@SpringBootTest
public class TestQuestion {

	@Autowired
	private QuestionRepository questionRepository;
	
//	@Test
//	@DisplayName("질문 등록 테스트")
//	public void writeQuestion() {
////		Questiontbl question = new Questiontbl();
////		question.setQtitle("두번째 질문입니다.");
////		question.setQcontent("내일의 저녁은 무엇인가요?");
////		
////		QuestionDto questionDto = new QuestionDto();
////		questionDto.setQtitle("홍길동");
////		questionDto.setQcontent("저는 홍길동입니다.");
////		
////		Questiontbl question = questionDto.getEntity();
////		
////		// JPA메서드는 엔티티 객체만 인자값으로 받을수있다.
////		questionRepository.save(question); // insert문 쿼리 실행됨
////		
//	}
//	
//	@Test
//	@DisplayName("질문 삭제 테스트")
//	public void deleteQuestion() {
//		
//		
//		//questionRepository.deleteAll(); // 모든 데이터 삭제 -주의요망...
//		//questionRepository.deleteById(4L); // Long 타입은 숫자 + L 
//		// 기본키 값인 qnum = 3 인 레코드를 찾아서 삭제 
//		
////		List<Questiontbl> qList = new ArrayList<>();
////		questionRepository.deleteAll(qList); // entity 리스트 삭제 (원하는레코드들만)
//		
//		//questionRepository.deleteAllByQtitle("홍길동"); // 질문 제목으로 삭제
//	}
//	
//	@Test
//	@DisplayName("질문 조회 테스트")
//	public void searchQuestion() {
//		
//		List<Questiontbl> questionAllList = questionRepository.findAll();
//		// findAll = 모든 레코드 가져오기 ( select * from jpaquestiontbl )
//		
//		for(Questiontbl question : questionAllList) {
//			System.out.println(question.getQnum());
//			System.out.println(question.getQtitle());
//			System.out.println(question.getQcontent());
//			System.out.println(question.getQdate());
//			System.out.println(question.getUdate());
//			System.out.println("----------------");
//			
//		}
//			
//		// 질문일 순으로 가장 최근질문이 위로 오게끔 출력
//		List<Questiontbl> questionOrderList = questionRepository.findAllByOrderByQdateDesc();
//		
//		for(Questiontbl question : questionOrderList) {
//			System.out.println(question.getQnum());
//			System.out.println(question.getQtitle());
//			System.out.println(question.getQcontent());
//			System.out.println(question.getQdate());
//			System.out.println(question.getUdate());
//			System.out.println("----------------");
//		}
//	}
	
	@Test
	@DisplayName("특정 질문 검색")
	public void searchQuestionByField() {
		Optional<Questiontbl> questionOption = questionRepository.findById(5L);
		//select * from jpaquestiontbl where qnum=4
		
		//기본키로 검색했을때 레코드가 1개 또는 존재하지않는경우 발생
		//Option<Questiontbl> 형태로 반환타입을 정해야함
		//isPresent() 를 활용하면 Option<Questiontbl> 내에 객체의 존재여부를 알수있음
		if(questionOption.isPresent()) { // ture 일시 해당 기본키를 가진 레코드가 존재함->조회가능
			Questiontbl question = questionOption.get(); //해당 기본키를 가진 entity(레코드)가 반환
			System.out.println(question.getQnum());
			System.out.println(question.getQtitle());
		}else {// 해당 기본키를 가진 레코드가 존재하지않음 -> 조회실패
			System.out.println("검색실패");
		}
		
		//select * from questiontbl where qnum=1 and qtitle=홍길동
		Questiontbl questiontbl = questionRepository.findByQnumAndQtitle(1L, "홍길동");
		
		//제목이 정확히 일치하는 조건으로 검색
		List<Questiontbl> questions = questionRepository.findAllByQtitle("홍길동");
		
		for(Questiontbl questio : questions) {
			System.out.println(questio.getQtitle());
		}
		
		//질문제목에 특정문자가 들어있으면 찾는 조건으로 조회 -> like
		List<Questiontbl> likeQuestion = questionRepository.findAllByQtitleLikeOrderByQdateDesc("%질문%");
		for(Questiontbl questiontbl2 : likeQuestion) {
			System.out.println(questiontbl2.getQnum());
			System.out.println(questiontbl2.getQtitle());
			System.out.println("----------------");
		}
		
		//직접 쓴 sql문 (@Query 사용) 으로 조회
		Questiontbl questiontb = questionRepository.findQuestionByQnum(5L);
		System.out.println(questiontb.getQnum());
		System.out.println(questiontb.getQtitle());
	}
}
