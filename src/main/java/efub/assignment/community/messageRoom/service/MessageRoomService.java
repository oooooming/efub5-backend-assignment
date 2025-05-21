package efub.assignment.community.messageRoom.service;

import efub.assignment.community.member.domain.Member;
import efub.assignment.community.member.repository.MemberRepository;
import efub.assignment.community.messageRoom.domain.MessageRoom;
import efub.assignment.community.messageRoom.dto.request.MessageRoomCreateRequestDto;
import efub.assignment.community.messageRoom.dto.request.existMessageRoomRequestDto;
import efub.assignment.community.messageRoom.dto.response.ExistMessageRoomResponseDto;
import efub.assignment.community.messageRoom.dto.response.MessageRoomListResponseDto;
import efub.assignment.community.messageRoom.dto.response.MessageRoomCreateResponseDto;
import efub.assignment.community.messageRoom.repository.MessageRoomRepository;
import efub.assignment.community.post.domain.Post;
import efub.assignment.community.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MessageRoomService {

    private final MemberRepository memberRepository;
    private final PostRepository postRepository;
    private final MessageRoomRepository messageRoomRepository;

    // 쪽지방 생성
    @Transactional
    public MessageRoomCreateResponseDto createMessageRoom(MessageRoomCreateRequestDto requestDto) {
        Member creater = memberRepository.findByMemberId(requestDto.createrId())
                .orElseThrow(IllegalArgumentException::new);
        Member receiver = memberRepository.findByMemberId(requestDto.receiverId())
                .orElseThrow(IllegalArgumentException::new);
        Post post = postRepository.findByPostId(requestDto.postId())
                .orElseThrow(IllegalArgumentException::new);

        MessageRoom newmessageRoom = requestDto.toEntity(creater, receiver, post);
        messageRoomRepository.save(newmessageRoom);

        return MessageRoomCreateResponseDto.from(newmessageRoom);
    }

    // 쪽지방 목록 조회
    @Transactional(readOnly = true)
    public MessageRoomListResponseDto getMessageRoomList(long memberId) {
        List<MessageRoom> messageRooms = messageRoomRepository.findAllByCreaterMemberId(memberId);
        return MessageRoomListResponseDto.from(memberId, messageRooms);
    }

    // 쪽지방 생성 여부 조회
    @Transactional(readOnly = true)
    public ExistMessageRoomResponseDto existMessageRoom(long memberId, existMessageRoomRequestDto requestDto) {
        Member creater = memberRepository.findByMemberId(memberId)
                .orElseThrow(IllegalArgumentException::new);
        Member receiver = memberRepository.findByMemberId(requestDto.receiverId())
                .orElseThrow(IllegalArgumentException::new);
        Post post = postRepository.findByPostId(requestDto.postId())
                .orElseThrow(IllegalArgumentException::new);

        Optional<MessageRoom> optionalRoom = messageRoomRepository
                .findByCreaterAndReceiverAndPost(creater, receiver, post);

        if (optionalRoom.isPresent()) {
            return new ExistMessageRoomResponseDto(optionalRoom.get().getMessageRoomId());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "메시지방이 존재하지 않습니다.");
        }
    }

    // 쪽지방 삭제
    public String deleteMessageRoom(long messageRoomId) {
        messageRoomRepository.deleteById(messageRoomId);
        return "쪽지방이 성공적으로 삭제되었습니다.";
    }

}
