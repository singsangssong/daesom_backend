package org.example.api.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.database.member.Member;
import org.example.database.member.dto.MemberDto;
import org.example.database.member.dto.RegisterReq;
import org.example.database.member.repository.MemberRepo;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {
    private final MemberRepo memberRepo;

    // 회원 등록하기
    public Long register(RegisterReq request) {
        Member member = request.toEntity(request);
        return memberRepo.save(member).getId();
    }

    // 맴버 전체 리스트 조회하기
    public List<MemberDto> memberList() {
        List<MemberDto> memberDtoList = new ArrayList<>();
        List<Member> memberList = memberRepo.findAll();
        memberList.stream().forEach(member -> {
            memberDtoList.add(MemberDto.builder().member(member).build());
        });
        return memberDtoList;
    }

    // 맴버 상세보기
    public MemberDto memberDetail(Long id) throws Exception {
        HttpStatus notFound = HttpStatus.NOT_FOUND;
        Member member = memberRepo.findById(id).orElseThrow(Exception::new);
        return MemberDto.builder().member(member).build();
    }
}
