package org.example.api.member;

import lombok.RequiredArgsConstructor;
import org.example.database.member.Member;
import org.example.database.member.dto.RegisterReq;
import org.example.database.member.repository.MemberRepo;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepo memberRepo;

    public void register(RegisterReq request) {
        Member member = request.toEntity(request);
        memberRepo.save(member);
    }
}
