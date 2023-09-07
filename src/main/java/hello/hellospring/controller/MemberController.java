package hello.hellospring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;


// Spring이 MemberController 객체 객체를 생성하여 Spring Container의 Bean으로서 관리된다
// Spring이 관리하게 되면 모든 객체를 Spring Container에 등록하고 Spring으로부터 받아다 사용해야 한다
// Service, Repository는 여러 인스턴스가 필요 없다. 하나만 생성하여 공용으로 사용하면 된다.
@Controller
public class MemberController {

    private MemberService service;

    @Autowired
    public MemberController(MemberService service) {
        this.service = service;
    }

    @GetMapping("members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping("members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        service.join(member);

        return "redirect:/";
    }

    @GetMapping("members")
    public String list(Model model) {
        List<Member> members = service.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
    
    
}
