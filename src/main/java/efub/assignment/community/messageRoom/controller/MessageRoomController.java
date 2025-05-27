package efub.assignment.community.messageRoom.controller;

import efub.assignment.community.messageRoom.dto.request.MessageRoomCreateRequestDto;
import efub.assignment.community.messageRoom.dto.request.existMessageRoomRequestDto;
import efub.assignment.community.messageRoom.dto.response.ExistMessageRoomResponseDto;
import efub.assignment.community.messageRoom.dto.response.MessageRoomListResponseDto;
import efub.assignment.community.messageRoom.dto.response.MessageRoomCreateResponseDto;
import efub.assignment.community.messageRoom.service.MessageRoomService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/messageRooms")
public class MessageRoomController {

    private final MessageRoomService messageRoomService;

    // 쪽지방 생성
    @PostMapping
    public ResponseEntity<MessageRoomCreateResponseDto> createMessageRoom(@RequestBody @Valid MessageRoomCreateRequestDto requestDto) {
        MessageRoomCreateResponseDto responseDto = messageRoomService.createMessageRoom(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    // 쪽지방 목록 조회
    @GetMapping("/{memberId}/list")
    public ResponseEntity<MessageRoomListResponseDto> getMessageRoomList(@PathVariable long memberId) {
        MessageRoomListResponseDto responseDto = messageRoomService.getMessageRoomList(memberId);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    // 쪽지방 생성 여부 확인
    @GetMapping("/{memberId}/exist")
    public ResponseEntity<ExistMessageRoomResponseDto> existMessageRoom(@PathVariable long memberId,
                                                                        @RequestBody @Valid existMessageRoomRequestDto requestDto) {
        ExistMessageRoomResponseDto response = messageRoomService.existMessageRoom(memberId, requestDto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // 쪽지방 삭제
    @DeleteMapping("/{messageRoomId}")
    public ResponseEntity<String> deleteMessageRoom(@PathVariable long messageRoomId) {
        String response = messageRoomService.deleteMessageRoom(messageRoomId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
