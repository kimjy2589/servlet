package hello.servlet.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

// Junit4 public 있어야하지만 5 는 없어도 됨
class MemberRepositoryTest {

    // 싱글톤이기 때문에 new로 불러오지 않음
    // 스프링을 사용하면 싱글톤 필요없음 -> 스프링에서 싱글톤을 보장해주기 때문에
    MemberRepository memberRepository = MemberRepository.getInstance();

    // 테스트가 끝나면 초기화
    @AfterEach
    void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void save() {
        //given
        Member member = new Member("hello", 20);

        //when
        Member savedMember = memberRepository.save(member);

        //then
        Member findMember = memberRepository.findById(savedMember.getId());
        assertThat(findMember).isEqualTo(savedMember);
    }

    @Test
    void findAll() {
        //given
        Member member1 = new Member("member1", 20);
        Member member2 = new Member("member2", 30);

        memberRepository.save(member1);
        memberRepository.save(member2);

        //when
        List<Member> result = memberRepository.findAll();

        //then
        assertThat(result.size()).isEqualTo(2);
        // member1, member2가 있는지
        assertThat(result).contains(member1, member2);

    }
}
