package hello.servlet.domaion.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 동시성 문제가 고려되어 있지 않음, 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
 * */
public class MemberRepository {



    private static Map<Long, Member> store = new HashMap<>();
    private static long sequnce = 0;

    private static final MemberRepository instance = new MemberRepository(); //싱글톤

    public static MemberRepository getInstance() { //무조건  getInstance로 조회한다.
        return instance;
    }
    private MemberRepository() { //샹성자를 막아야함

    }

    public Member save(Member member) {
        member.setId(++sequnce);
        store.put(member.getId(), member);
        return member;
    }

    public  Member findById(Long id) {
        return store.get(id);
    }

    public List<Member> findAll() {
        return new ArrayList<>(store.values());
        //store에 담긴 모든 value를 ArrayList에 담아서 넘겨준다. -> new ArrayList에 값을 담거나 조작해도 store에 있는 value list를 건들고 싶지 않아서
    }

    public void clearStore() {
        store.clear();
    }
}
