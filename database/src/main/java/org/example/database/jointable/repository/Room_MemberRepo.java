package org.example.database.jointable.repository;

import org.example.database.jointable.ChatRoom_Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Room_MemberRepo extends JpaRepository<ChatRoom_Member, Long> {

}
