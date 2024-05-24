package org.example.boardproject.service;

import lombok.RequiredArgsConstructor;
import org.example.boardproject.domain.Board;
import org.example.boardproject.repository.BoardRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    // 전부 출력
    @Transactional(readOnly = true)
    public Iterable<Board> findAll() {
        return boardRepository.findAll();
    }
    // + 페이징 출력
    public Page<Board> findAll(Pageable pageable) {
        Pageable sortedByDescCteareAt = PageRequest.of(
                pageable.getPageNumber(), pageable.getPageSize(),
                Sort.by(Sort.Direction.DESC, "created_at"));

        return boardRepository.findAll(sortedByDescCteareAt);
    }

    // 하나 출력
    @Transactional(readOnly = true)
    public Board findBoardById(Long id) {
        return boardRepository.findById(id).orElse(null);
    }

    // 저장
    @Transactional
    public Board save(Board board) {
        return boardRepository.save(board);
    }

    // 삭제
    @Transactional
    public void delete(Board board) {
        boardRepository.delete(board);
    }

    // 제목으로 검색
    // 해당 단어 포함하면 출력
    @Transactional
    public Page<Board> search(String search, Pageable pageable) {
        Pageable sortedByDescCteareAt = PageRequest.of(
                pageable.getPageNumber(), pageable.getPageSize(),
                Sort.by(Sort.Direction.DESC, "created_at"));
        return boardRepository.findByTitleContaining(search, sortedByDescCteareAt);
    }
}
