package hellow.hellowspring;

import hellow.hellowspring.domain.Member;
import hellow.hellowspring.repository.MemberRepository;
import hellow.hellowspring.repository.MemoryMemberRepository;
import hellow.hellowspring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Optional;

@Configuration
public class SpringConfig {
    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
}
