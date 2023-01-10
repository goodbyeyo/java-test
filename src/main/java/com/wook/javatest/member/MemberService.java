package com.wook.javatest.member;

import com.wook.javatest.domain.Member;
import com.wook.javatest.domain.Study;
import java.util.Optional;

public interface MemberService {

    Optional<Member> findById(Long memberId);

    void validate(Long memberId);

    void notify(Study newstudy);

    void notify(Member member);
}
