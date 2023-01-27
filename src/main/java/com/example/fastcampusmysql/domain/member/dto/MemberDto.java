package com.example.fastcampusmysql.domain.member.dto;

import java.time.LocalDate;

/**
 * packageName    : com.example.fastcampusmysql.domain.member.dto
 * fileName       : MemberDto
 * author         : LeeSeongJun
 * date           : 2023-01-19
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-01-19        LeeSeongJun       최초 생성
 */
public record MemberDto(
        Long id,
        String email,
        String nickname,
        LocalDate birthday
) {}
