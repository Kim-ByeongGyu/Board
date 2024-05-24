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

    @Transactional(readOnly = true)
    public Iterable<Board> findAll() {
        return boardRepository.findAll();
    }

    public Page<Board> findAll(Pageable pageable) {
        Pageable sortedByDescCteareAt = PageRequest.of(
                pageable.getPageNumber(), pageable.getPageSize(),
                Sort.by(Sort.Direction.DESC, "created_at"));

        return boardRepository.findAll(sortedByDescCteareAt);
    }

    @Transactional(readOnly = true)
    public Board findBoardById(Long id) {
        return boardRepository.findById(id).orElse(null);
    }

    @Transactional
    public Board save(Board board) {
        return boardRepository.save(board);
    }

    @Transactional
    public void delete(Board board) {
        boardRepository.delete(board);
    }

    @Transactional
    public Page<Board> search(String query, Pageable pageable) {
        Pageable sortedByDescCteareAt = PageRequest.of(
                pageable.getPageNumber(), pageable.getPageSize(),
                Sort.by(Sort.Direction.DESC, "created_at"));
        return boardRepository.findByTitleContaining(query, sortedByDescCteareAt);
    }
}
