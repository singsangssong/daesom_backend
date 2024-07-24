package org.example.server;

import lombok.RequiredArgsConstructor;
import org.example.database.chatmsg.repository.ChatMsgRepo;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MsgService {

    private final ChatMsgRepo chatMsgRepo;

    // 전송된 메세지 저장
    public void onMsg(String msg) {

    }
}
