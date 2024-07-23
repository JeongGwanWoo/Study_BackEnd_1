package hello.hello_spring.controller;

import hello.hello_spring.domain.Member;
import hello.hello_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

    private MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
//    DI에는 필드 주입, setter 주입, 생성자 주입 3가지 방법이 있다.
//    의존관계가 실행중에 동적으로 변하는 경우가 거의 없으므로 생정자 주입을 권장한다.
//    실무에서는 주로 정형화된 컨트롤러, 서비스, 리포지토리 같은 코드는 컴포넌트 스캔을 사용한다.
//    그리고 정형화되지 않거나, 상황에 따라 구현 클래스를 변경해야 하면 설정을 통해 스프링 빈으로 등록한다.
//    주의 : '@Autowired'를 통한 DI는 'helloController', 'MemberService'등과 같이 스프링이 관리하는 객체에서만 작동한다.
//    스프링 빈으로 등록하지 않고 내가 직접 생성한 객체에서는 동작하지 않는다.

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMember();
        model.addAttribute("member", members);
        return "members/memberList";
    }
}
