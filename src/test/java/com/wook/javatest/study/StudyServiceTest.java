package com.wook.javatest.study;

import com.wook.javatest.domain.Member;
import com.wook.javatest.domain.Study;
import com.wook.javatest.member.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudyServiceTest {

     @Mock MemberService memberService;
     @Mock StudyRepository studyRepository;

    @Test
    void createStudyService() {
        StudyService studyService = new StudyService(memberService, studyRepository);
        assertNotNull(studyService);

        Member member = new Member(1L, "wook@naver.com");
        when(memberService.findById(any()))
                .thenReturn(Optional.of(member));
        Optional<Member> findById = memberService.findById(1L);
        assertEquals("wook@naver.com", memberService.findById(1L).get().getEmail());
        assertEquals("wook@naver.com", memberService.findById(2L).get().getEmail());

        doThrow(new IllegalArgumentException()).when(memberService).validate(1L);
        assertThrows(IllegalArgumentException.class, () -> memberService.validate(1L));
        memberService.validate(2L);

        when(memberService.findById(any()))
                .thenReturn(Optional.of(member))    // 1st return
                .thenThrow(new RuntimeException())  // 2rd return
                .thenReturn(Optional.empty());   // 3rd return
        Optional<Member> byId = memberService.findById(1L);
        assertEquals("wook@naver.com", byId.get().getEmail());
        assertThrows(RuntimeException.class, () -> memberService.findById(1L));
        Optional<Member> optional = memberService.findById(1L);
        assertEquals(Optional.empty(), optional);
    }

    @Test
    void stubbingTest() {
        StudyService studyService = new StudyService(memberService, studyRepository);
        assertNotNull(studyService);

        Study study = new Study(10, "Test");
        Member member = new Member(1L, "wook@naver.com");

        // TODO memberService 객체에 findById 메소드를 1L 값으로 호출하면 member 객체를 리턴하도록 stubbing
        when(memberService.findById(1L)).thenReturn(Optional.of(member));
        // TODO studyRepository 객체에 save 메소드를 study 객체로 호출하면 study 객체 그대로 리턴하도록 Stubbing
        when(studyRepository.save(study)).thenReturn(study);

        Study newStudy = studyService.createNewStudy(1L, study);
        assertEquals(member.getId(), newStudy.getOwnerId());

        // 행동 횟수 검증
        verify(memberService, times(1)).notify(study);
        // verifyNoMoreInteractions(memberService);    // 객체의 추가 행동 확인
        verify(memberService, times(1)).notify(member);
        verify(memberService, never()).validate(any());

        // 순차적 행동 검증
        InOrder inOrder = inOrder(memberService);
        inOrder.verify(memberService).notify(study);
        inOrder.verify(memberService).notify(member);
    }

    @Test
    void bddTest() {
        StudyService studyService = new StudyService(memberService, studyRepository);
        Member member = new Member(1L, "wook@naver.com");
        Study study = new Study(10, "Test");
        given(memberService.findById(1L)).willReturn(Optional.of(member));
        given(studyRepository.save(study)).willReturn(study);

        // when
        studyService.createNewStudy(1L, study);

        // then
        assertEquals(member.getId(), study.getOwnerId());
        then(memberService).should().notify(study); // verify(memberService, times(1)).notify(study);
        then(memberService).should().notify(member);
        then(memberService).shouldHaveNoMoreInteractions(); // verifyNoMoreInteractions(memberService);
    }

    @DisplayName("다른 사용자가 볼 수 있도록 스터디를 공개한다.")
    @Test
    void openStudy() {
        // Given
        StudyService studyService = new StudyService(memberService, studyRepository);
        Study study = new Study(10, "더 자바, 테스트");
        assertNull(study.getOpenedDateTime());
        // TODO studyRepository Mock 객체의 save 메소드를호출 시 study를 리턴하도록 만들기.
        given(studyRepository.save(study)).willReturn(study);

        // When
        studyService.openStudy(study);

        // Then
        // TODO study의 status가 OPENED로 변경됐는지 확인
        assertEquals(StudyStatus.OPENED, study.getStatus());

        // TODO study의 openedDataTime이 null이 아닌지 확인
        assertNotNull(study.getOpenedDateTime());

        // TODO memberService의 notify(study)가 호출 됐는지 확인.
        then(memberService).should().notify(study); //
    }
}

