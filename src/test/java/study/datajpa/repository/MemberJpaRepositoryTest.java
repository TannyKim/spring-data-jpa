package study.datajpa.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import study.datajpa.entity.Member;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
/**
 * 스프링 테스트에서 transactional는 다 롤백하고 jpa 영속석 컨텍스트도 flush를 안한다.
 * 따라서 insert 쿼리문도 로그에 안보인다.
 * rollbac(false)로 확인해 볼 수 있다.
 */
@Rollback(value = false)
class MemberJpaRepositoryTest {

    @Autowired MemberJpaRepository memberJpaRepository;

    @Test
    public void testMember() {
        Member member = new Member("memberA");
        Member savedMember = memberJpaRepository.save(member);

        Member findMember = memberJpaRepository.find(savedMember.getId());

        assertThat(findMember.getId()).isEqualTo(member.getId());
        assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
        /**
         * findMember == member
         * 같은 Transactional 에서는 영속성 컨텍스트의 동일성을 보장한다.
         * 갘은 인스턴스인 것이 보장된다.
         * 트랜잭션이 다르면 당연히 다른 객체
         * 1차 캐시의 원리
         */
        assertThat(findMember).isEqualTo(member);
    }
}