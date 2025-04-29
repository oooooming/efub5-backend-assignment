package efub.assignment.community.board.controller;

import efub.assignment.community.board.dto.BoardCreateRequestDto;
import efub.assignment.community.board.dto.BoardResponseDto;
import efub.assignment.community.post.dto.PostListResponseDto;
import efub.assignment.community.board.dto.UpdateOwnerRequestDto;
import efub.assignment.community.board.service.BoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/boards")
public class BoardController {
    private final BoardService boardService;

    // 게시판 생성
    @PostMapping
    public ResponseEntity<BoardResponseDto> createBoard(@Valid @RequestBody BoardCreateRequestDto boardCreateRequestDto) {
        BoardResponseDto boardResponseDto = boardService.createBoard(boardCreateRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(boardResponseDto);
    }

    // 게시판 조회
    @GetMapping("/{boardId}")
    public ResponseEntity<BoardResponseDto> getBoard(@PathVariable Long boardId) {
        BoardResponseDto boardResponseDto = boardService.getBoard(boardId);
        return ResponseEntity.status(HttpStatus.OK).body(boardResponseDto);
    }

    // 게시판 소유자 수정
    @PatchMapping("/{boardId}/owner")
    public ResponseEntity<BoardResponseDto> changeOwner(@PathVariable Long boardId, @Valid @RequestBody UpdateOwnerRequestDto updateOwnerRequestDto) {
        BoardResponseDto boardResponseDto = boardService.updateOwner(boardId, updateOwnerRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(boardResponseDto);
    }

    // 게시판 삭제
    @DeleteMapping("/{boardId}")
    public ResponseEntity<String> deleteBoard(@PathVariable Long boardId) {
        boardService.deleteBoard(boardId);
        return ResponseEntity.ok("성공적으로 게시판 삭제가 완료되었습니다.");
    }
}
