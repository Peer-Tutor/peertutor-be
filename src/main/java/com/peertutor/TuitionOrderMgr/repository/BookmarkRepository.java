package com.peertutor.TuitionOrderMgr.repository;

import com.peertutor.TuitionOrderMgr.model.Bookmark;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
    Bookmark findById(long id);
    Bookmark findByTutorID(long tutorID);
    Page<Bookmark> findByStudentID(long studentID, Pageable pageable);
    List<Bookmark> findByStudentID(long studentID);
    Optional<Bookmark> findByTutorIDAndStudentID(long tutorID, long studentID);
    void deleteByTutorIDAndStudentID(long tutorID, long studentID);
}

