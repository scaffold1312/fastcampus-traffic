package com.example.fastcampusmysql.controller;

import com.example.fastcampusmysql.domain.member.dto.MemberDto;
import com.example.fastcampusmysql.domain.member.dto.RegisterMemberCommand;
import com.example.fastcampusmysql.domain.member.entity.Member;
import com.example.fastcampusmysql.domain.member.service.MemberReadService;
import com.example.fastcampusmysql.domain.member.service.MemberWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * packageName    : com.example.fastcampusmysql.controller
 * fileName       : MemberController
 * author         : LeeSeongJun
 * date           : 2023-01-16
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-01-16        LeeSeongJun       최초 생성
 */
@RestController
@RequiredArgsConstructor
public class MemberController {
    final private MemberWriteService memberWriteService;
    final private MemberReadService memberReadService;

    /**
     * author         : LeeSeongJun
     * date           : 2023-01-27
     * description    :
     * ===========================================================
     * DATE              AUTHOR             NOTE
     * -----------------------------------------------------------
     * 2023-01-27        LeeSeongJun       최초 생성
     *
     * @param command
     * @return
     */
    @PostMapping("/members")
    public MemberDto register(@RequestBody RegisterMemberCommand command) {
        /**
         * Member Entity를 그대로 받게되면 강한 결합으로 여러 부분에서 수정이 이루어져야함
         * 따라서 MemberDto로 받아서 처리하는게 유연성 확장에 좋음
         */
        var member = memberWriteService.register(command);

        return memberReadService.toDto(member);
    }

    @GetMapping("/members/{id}")
    public Member getMember(@PathVariable Long id) {
        return memberReadService.getMember(id);
    }
}
