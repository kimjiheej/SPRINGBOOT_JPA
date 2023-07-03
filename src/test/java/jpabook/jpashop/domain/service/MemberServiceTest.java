package jpabook.jpashop.domain.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class MemberServiceTest {


    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;
    @Autowired
    EntityManager em;

    @Test
    @Rollback(false)
    public void 회원가입() throws Exception{

        Member member = new Member();
        member.setName("kim");

        Long saveId = memberService.join(member);

        em.flush(); // 영속성 컨텐스트의 내용을 디비에 반영하는 것이다
        assertEquals(member,memberRepository.findOne(saveId));
    }


    @Test
    public void 중복_회원_예외() throws Exception{


        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");

        memberService.join(member1);

        try{
            memberService.join(member2);
        }catch(IllegalStateException e){
            return;
        }

        fail("예외가 발생해야 한다.");


    }

}