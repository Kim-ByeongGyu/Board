package org.example.boardproject.controller;

import lombok.RequiredArgsConstructor;
import org.example.boardproject.domain.Board;
import org.example.boardproject.repository.BoardRepository;
import org.example.boardproject.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    @Autowired
    private BoardRepository boardRepository;
    // 출력
    @GetMapping("/list")
    public String list(Model model,
                       @RequestParam(defaultValue = "1") int page,
                       @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Board> boards = boardService.findAll(pageable);
        model.addAttribute("boards", boards);
        model.addAttribute("currentPage", page);
        return "boards/list";
    }

    // 생성
    @GetMapping("/writeform")
    public String addForm(Model model) {
        model.addAttribute("board", new Board());
        return "boards/writeform";
    }

    @PostMapping("/write")
    public String add(@ModelAttribute Board board) {
        board.setCreated_at(LocalDateTime.now());
        board.setUpdated_at(LocalDateTime.now());
        boardService.save(board);
        return "redirect:/list";
    }

    // 상세
    @GetMapping("/view/{id}")
    public String view(@PathVariable Long id, Model model) {
        Board board = boardService.findBoardById(id);
        model.addAttribute("board", board);
        return "boards/view";
    }

    // 수정
    @GetMapping("/updateform/{id}")
    public String update(@PathVariable Long id, Model model) {
        Board board = boardService.findBoardById(id);
        model.addAttribute("board", board);
        return "boards/updateform";
    }

    @PostMapping("/updateform")
    public String update(@ModelAttribute Board board,
                         @RequestParam Long id,
                         @RequestParam String password,
                         Model model) {
        Board original = boardService.findBoardById(id);
        if (!password.equals(original.getPassword())) {
            model.addAttribute("error", "Incorrect password.");
            return "boards/updateform";
        }
        board.setCreated_at(original.getCreated_at());
        board.setUpdated_at(LocalDateTime.now());
        boardService.save(board);
        return "redirect:/list";
    }

    // 삭제
    @GetMapping("/deleteform/{id}")
    public String delete(@PathVariable Long id, Model model) {
        Board board = boardService.findBoardById(id);
        model.addAttribute("board", board);
        return "boards/deleteform";
    }

    @PostMapping("/delete")
    public String delete(@ModelAttribute Board board,
                         @RequestParam Long id,
                         @RequestParam String password,
                         Model model) {
        Board original = boardService.findBoardById(id);

        if (!password.equals(original.getPassword())) {
            // 비밀번호가 일치하지 않는 경우 처리
            model.addAttribute("error", "Incorrect password.");
            return "boards/deleteform";
        }

        // 비밀번호가 일치하는 경우 보드 삭제
        boardService.delete(board);
        return "redirect:/list";
    }

    // 검색
    @GetMapping("/search")
    public String search(@RequestParam String search,
                         @RequestParam(defaultValue = "1") int page,
                         @RequestParam(defaultValue = "5") int size,
                         Model model) {
        Pageable pageable = PageRequest.of(page-1, size); // 페이지당 10개의 결과를 보여줌
//        Page<Board> searchBoards = boardRepository.findByTitleContaining(search, pageable);
        Page<Board> searchBoards = boardService.search(search, pageable);
        model.addAttribute("searchBoards", searchBoards);
        model.addAttribute("currentPage", page);
        model.addAttribute("search", search);
        return "boards/searchResult";
    }
}
