package hello.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    // 싱글톤
    private static final MemberRepository instance = new MemberRepository();

    public static MemberRepository getInstance() {
        return instance;
    }

    private MemberRepository() {

    }

    // 회원 저장
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    // 회원 찾기
    public Member findById(Long id) {
        return store.get(id);
    }

    // 전체 회원 조회
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
        // store자체를 리턴하는 게 아니라 ArrayList에 담아서 보내준다.
        // 그 이유는 밖에서 ArrayList를 조작한다고 해도 실제로 store를 건드리지는 않기 때문이다. (store 보호를 위해)
    }

    public void clearStore() {
        store.clear();
    }
}
