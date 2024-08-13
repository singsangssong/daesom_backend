package org.example.database.member.dto;

import lombok.Builder;
import org.example.database.member.Member;

public class MemberDto {
    private String _id;
    private String name;

    @Builder
    public MemberDto (String _id,  String name) {
        this._id = _id;
        this.name = name;
    }
}
