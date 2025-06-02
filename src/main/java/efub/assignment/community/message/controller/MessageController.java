package efub.assignment.community.message.controller;

import efub.assignment.community.message.dto.response.MessageCreateResponseDto;
import efub.assignment.community.message.dto.response.MessageListResponseDto;
import efub.assignment.community.message.dto.resquest.MessageCreateRequestDto;
import efub.assignment.community.message.service.MessageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/messages")
public class MessageController {

    private final MessageService messageService;

    // 쪽지 생성
    @PostMapping
    public ResponseEntity<MessageCreateResponseDto> createMessage(@RequestBody MessageCreateRequestDto requestDto) {
        MessageCreateResponseDto responseDto = messageService.createMessage(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    // 쪽지 목록 조회
    @GetMapping("/{messageRoomId}")
    public ResponseEntity<MessageListResponseDto> getMessages(@PathVariable long messageRoomId,
                                                                @RequestParam @Valid long memberId) {
        MessageListResponseDto responseDto = messageService.getMessages(messageRoomId, memberId);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }
}
