package org.example.api.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.database.member.Member;
import org.example.database.member.dto.RegisterReq;
import org.example.database.member.repository.MemberRepo;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {
    private final MemberRepo memberRepo;

    public void register(RegisterReq request) {

        System.out.println("request = " + request.getPassword());

        Member member = request.toEntity(request);
        memberRepo.save(member);
    }
}
