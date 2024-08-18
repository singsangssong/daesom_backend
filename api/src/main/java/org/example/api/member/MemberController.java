package org.example.api.member;

import lombok.RequiredArgsConstructor;
import org.example.database.member.dto.MemberDto;
import org.example.database.member.dto.RegisterReq;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;
    @PostMapping("") // 회원 등록
    public ResponseEntity<Long> register(@RequestBody RegisterReq registerReq) {
        Long registerId = memberService.register(registerReq);
        return ResponseEntity.ok(registerId);
    }

    // 사용자 상세보기
    @GetMapping("/{id}")
    public ResponseEntity<MemberDto> memberDetail(@PathVariable("id") Long id) throws Exception {
        return ResponseEntity.ok(memberService.memberDetail(id));
    }
    @GetMapping("/list") // 전체 맴버 리스트
    public ResponseEntity<List<MemberDto>> memberList() {
        List<MemberDto> memberDtos = memberService.memberList();
        return ResponseEntity.ok(memberDtos);
    }



}
