package hello.servlet.web.frontcontroller.v3.controller;


import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;

import java.util.Map;

public class MemberSaveControllerV3 implements ControllerV3 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public ModelView process(Map<String, String> paramMap) {

        // paramMap에 있는 username을 가져옴
        String username = paramMap.get("username");
        int age = Integer.parseInt(paramMap.get("age"));

        // 가져온 정보를 member에 담아서 저장함
        Member member = new Member(username, age);
        memberRepository.save(member);

        ModelView mv = new ModelView("save-result");
        mv.getModel().put("member", member);

        // ModelView 객체에는 가져온 member의 정보와 uri정보가 담기게 된다.

        return mv;
    }
}
