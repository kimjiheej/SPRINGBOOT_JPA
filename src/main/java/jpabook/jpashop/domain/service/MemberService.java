package jpabook.jpashop.service;


import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional // 그래야 로딩이 잘 된다
@RequiredArgsConstructor // final 있는 필드만을 가지고 생성자를 만들어주는 것이다
public class MemberService {

    @Autowired
    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    // 회원가입

    public Long join(Member member){

        // 중복 회원을 검증한다고 해보자
        validateDuplicateMember(member);  // 중복 회원이 있는지 검증
        memberRepository.save(member);
        return member.getId(); //
    }

    private void validateDuplicateMember(Member member) {
        // exception
      List<Member> findMembers =   memberRepository.findByName(member.getName());
      if(!findMembers.isEmpty()){
          throw new IllegalStateException("이미 존재하는 회원입니다");
      }

    }

    // 회원 전체 조회

    @Transactional
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Member member(Long memberId){
        return memberRepository.findOne(memberId);
    }
}
