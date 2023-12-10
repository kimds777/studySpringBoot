package hellow.hellowspring.service;

import hellow.hellowspring.domain.Member;
import hellow.hellowspring.repository.MemberRepository;
import hellow.hellowspring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    //회원가입
    public Long join(Member member) {
        validateDuplicateMember(member); //동명이인 회원 가입 불가

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {

//        Optional<Member> result = memberRepository.findByName(member.getName());
//        result.ifPresent(m -> {
//            throw new IllegalStateException("이미 존재하는 회원입니다.");
//        }); 아래와 같이 Optional로 감싸지않고 사용할 수 도있다

        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    //전체 회원 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
