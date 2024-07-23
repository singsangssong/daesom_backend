package org.example.api.member;

import lombok.RequiredArgsConstructor;
import org.example.database.member.dto.RegisterReq;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    @PostMapping("/member")
    public ResponseEntity<String> register(RegisterReq registerReq) {
        memberService.register(registerReq);

        return ResponseEntity.ok("okay");
    }
}
