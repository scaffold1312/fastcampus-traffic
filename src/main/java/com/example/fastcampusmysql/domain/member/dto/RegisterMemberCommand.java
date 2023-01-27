package com.example.fastcampusmysql.domain.member.dto;

import java.time.LocalDate;

/**
 * packageName    : com.example.fastcampusmysql.domain.member.dto
 * fileName       : RegisterMemberCommand
 * author         : LeeSeongJun
 * date           : 2023-01-16
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-01-16        LeeSeongJun       최초 생성
 */
// record : 14부터 preview 등록 , 16부터 정식등록
// record로 작성하게되면 getter,setter를 자동으로 생성하게 해주고 property를 생성해줌(?)
public record RegisterMemberCommand(
        String email,
        String nickname,
        LocalDate birthday
) {
}
