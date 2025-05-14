package efub.assignment.community.board.service;

import efub.assignment.community.board.domain.Board;
import efub.assignment.community.board.dto.BoardCreateRequestDto;
import efub.assignment.community.board.dto.BoardResponseDto;
import efub.assignment.community.board.dto.UpdateOwnerRequestDto;
import efub.assignment.community.board.repository.BoardRepository;
import efub.assignment.community.member.domain.Member;
import efub.assignment.community.member.repository.MemberRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    // 게시판 생성
    @Transactional
    public BoardResponseDto createBoard(BoardCreateRequestDto boardCreateRequestDto) {
        Long ownerId = boardCreateRequestDto.ownerId();
        Member owner = memberRepository.findByMemberId(ownerId)
                .orElseThrow(() -> new IllegalArgumentException("Member not found"));
        Board board = boardCreateRequestDto.toEntity(owner);
        Board savedBoard = boardRepository.save(board);
        return BoardResponseDto.from(savedBoard);
    }

    // 게시판 조회
    @Transactional
    public BoardResponseDto getBoard(Long boardId) {
        Board board = boardRepository.findByBoardId(boardId)
                .orElseThrow(() -> new IllegalArgumentException("Board not found"));
        return BoardResponseDto.from(board);
    }

    // 게시판 소유자 수정
    @Transactional
    public BoardResponseDto updateOwner(Long boardId, UpdateOwnerRequestDto updateOwnerRequestDto) {
        Board board = boardRepository.findByBoardId(boardId)
                .orElseThrow(() -> new IllegalArgumentException("Board not found"));
        Member newOwner = memberRepository.findByMemberId(updateOwnerRequestDto.getOwnerId())
                        .orElseThrow(() -> new IllegalArgumentException("Member not found"));
        board.updateOwner(newOwner);
        return BoardResponseDto.from(boardRepository.save(board));
    }

    // 게시판 삭제
    @Transactional
    public void deleteBoard(Long boardId) {
        boardRepository.deleteByBoardId(boardId);
    }
}
