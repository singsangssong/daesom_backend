package org.example.database.member.dto;

import lombok.Builder;
import lombok.Getter;
import org.example.database.member.Member;

@Getter
public class MemberDto {
    private Long id;
    private String _id;
    private String name;

//    @Builder
//    public MemberDto (Long id, String _id,  String name) {
//        this.id = id;
//        this._id = _id;
//        this.name = name;
//    }

    @Builder
    public MemberDto (Member member) {
        this.id = member.getId();
        this._id = member.get_id();
        this.name = member.getName();
    }
}
