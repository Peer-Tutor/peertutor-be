package com.peertutor.TuitionOrderMgr.controller;

import com.peertutor.TuitionOrderMgr.model.viewmodel.request.BookmarkReq;
import com.peertutor.TuitionOrderMgr.model.viewmodel.response.BookmarkRes;
import com.peertutor.TuitionOrderMgr.repository.BookmarkRepository;
import com.peertutor.TuitionOrderMgr.service.AuthService;
import com.peertutor.TuitionOrderMgr.service.BookmarkService;
import com.peertutor.TuitionOrderMgr.util.AppConfig;
import com.peertutor.TuitionOrderMgr.model.Bookmark;
import com.peertutor.TuitionOrderMgr.service.dto.BookmarkDTO;
import io.github.jhipster.web.util.PaginationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path="/bookmark-mgr")
public class BookmarkController {
    @Autowired
    AppConfig appConfig;
    @Autowired
    private BookmarkRepository bookmarkRepository;// = new CustomerRepository();
    @Autowired
    private BookmarkService bookmarkService;
    @Autowired
    private AuthService authService;
    @GetMapping(path="/health")
    public @ResponseBody String healthCheck(){
        return "Ok 2";
    }

    @PostMapping(path = "/bookmark")
    public @ResponseBody ResponseEntity<BookmarkRes> createBookmark(
            @RequestHeader("Name") String name,
            @RequestBody @Valid BookmarkReq req) {
        BookmarkDTO savedBookmark;

        req.name = name;
        savedBookmark = bookmarkService.createBookmark(req);

        if (savedBookmark == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }

        BookmarkRes res = new BookmarkRes(savedBookmark);

        return ResponseEntity.ok().body(res);
    }

    @GetMapping(path = "/bookmark")
    public @ResponseBody ResponseEntity<List<BookmarkDTO>> getBookmark(
            @RequestParam(name = "studentId") Long studentId,
            Pageable pageable) {
        Page<BookmarkDTO> page = bookmarkService.getBookmark(studentId, pageable);

        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @DeleteMapping(path = "/bookmark")
    @Transactional
    public @ResponseBody ResponseEntity<List<BookmarkDTO>> deleteCal(
            @RequestParam(name = "id") Optional<Long> id,
            @RequestParam(name = "tutorId") Optional<Long> tutorId,
            @RequestParam(name = "studentId") Optional<Long> studentId) {
        if (id.isPresent()) {
            bookmarkService.deleteBookmarkById(id.get());
        } else {
            bookmarkService.deleteBookmarkByStudentAndTutor(studentId.get(), tutorId.get());
        }

        return ResponseEntity.ok().body(null);
    }
}
