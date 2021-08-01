package hello.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 동시성 문제가 고려되어 있지 않음, 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
 *
 * HashMap
 * - synchronized 키워드가 없기 때문에 동기화가 보장되지 못함
 * - HashTable과 다르게 key, value null 값을 허용
 * - 속도가 빠르지만 신뢰성, 안정성은 떨어짐
 *
 * ConCurrentHashMap
 * - HashMap의 멀티쓰레드 환경에서의 동기화처리로 인한 문제점을 보완한 것
 * - key, value에 null 허용하지 않음
 *
 * HaspTable
 * - 동기화락 때문에 속도는 느리지만 data의 안정성이 높고 신뢰가 높은 컬렉션
 *
 * AtomicLong
 * - Long 자료형을 갖고 있는 Wrapping 클래스
 * - 멀티쓰레드에서 synchronized 없이 사용할 수 있음
 * - 적은 비용으로 동시성을 보장할 수 있음
 * */
public class MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    // 싱글톤
    private static final MemberRepository instance = new MemberRepository();

    public static MemberRepository getInstance() {
        return instance;
    }

    // 싱글톤일 때 생성자를 막아야 함
    // 아무나 생성하지 못하게 하기
    private MemberRepository() {
    }

    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(Long id) {
        return store.get(id);
    }

    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
