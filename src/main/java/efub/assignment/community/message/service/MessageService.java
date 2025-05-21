package efub.assignment.community.message.service;

import efub.assignment.community.member.domain.Member;
import efub.assignment.community.member.repository.MemberRepository;
import efub.assignment.community.message.domain.Message;
import efub.assignment.community.message.dto.response.MessageCreateResponseDto;
import efub.assignment.community.message.dto.response.MessageListResponseDto;
import efub.assignment.community.message.dto.resquest.MessageCreateRequestDto;
import efub.assignment.community.message.repository.MessageRepository;
import efub.assignment.community.messageRoom.domain.MessageRoom;
import efub.assignment.community.messageRoom.repository.MessageRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;
    private final MessageRoomRepository messageRoomRepository;
    private final MemberRepository memberRepository;

    // 쪽지 생성
    public MessageCreateResponseDto createMessage(@RequestBody MessageCreateRequestDto requestDto) {
        MessageRoom messageRoom = messageRoomRepository.findById(requestDto.messageRoomId())
                .orElseThrow(() -> new IllegalArgumentException("Message room not found"));
        Member sender = memberRepository.findByMemberId(requestDto.senderId())
                .orElseThrow(() -> new IllegalArgumentException("Member not found"));
        Message newMessage = requestDto.toEntity(messageRoom, sender);
        newMessage.setMessageRoom(messageRoom);
        messageRepository.save(newMessage);

        return MessageCreateResponseDto.from(newMessage);
    }

    // 쪽지 목록 조회
    @Transactional(readOnly = true)
    public MessageListResponseDto getMessages(long messageRoomId, long memberId){
        List<Message> messages = messageRepository.findAllByMessageRoomMessageRoomId(messageRoomId);
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("Member not found"));
        return MessageListResponseDto.from(messages, member);
    }
}
