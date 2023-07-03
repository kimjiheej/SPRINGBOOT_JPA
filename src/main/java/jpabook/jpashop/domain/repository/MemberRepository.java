package jpabook.jpashop.domain.repository;


import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    @Autowired
    private final EntityManager em;

    public void save(Member member){
        em.persist(member); // 객체가 저장된다
    }

    public Member findOne(Long id){
        return em.find(Member.class,id); // 단건 조회이다.
    }

    public List<Member> findAll(){ // 기능적으로는 거의 동일하기는 하다
       return  em.createQuery("select m from Member m",Member.class) // jpql 을 사용하였다
                .getResultList();
    }

    public List<Member> findByName(String name){
        return em.createQuery("select m from Member m where m.name= :name", Member.class)
                .setParameter("name",name)
                .getResultList();
    }
}
