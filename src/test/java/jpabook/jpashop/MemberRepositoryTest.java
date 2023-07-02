package jpabook.jpashop;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Rollback(false)
class MemberRepositoryTest {

  @Autowired MemberRepository memberRepository;

  @Test
  @Transactional
    public void testMember() throws Exception{
      Member member = new Member();
      member.setUsername("memberA");
      Long saveId = memberRepository.save(member);

      Member findMember = memberRepository.find(saveId);

      Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
      Assertions.assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
      Assertions.assertThat(findMember).isEqualTo(member);
      System.out.println("findMEmber == member: "+ (findMember == member)); // 같다고 나온다. 같은 transaction 안에 있기 때문이다
  }

}