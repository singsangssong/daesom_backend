package org.example.database.member.repository;

import org.example.database.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepo extends JpaRepository<Member, Long> {

}
