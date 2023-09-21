package com.peertutor.TuitionOrderMgr.repository;

import com.peertutor.TuitionOrderMgr.model.TuitionOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Repository
public interface TuitionOrderRepository extends JpaRepository<TuitionOrder, Long>, JpaSpecificationExecutor<TuitionOrder> {
    List<TuitionOrder> findByStudentIdAndTutorId(long studentId, long tutorId);
    List<TuitionOrder> findByStudentIdAndTutorIdAndStatus(long studentId, long tutorId, int status);
    List<TuitionOrder> findByStatus(int status);
    List<TuitionOrder> findBySelectedDatesContainingAndStatusAndTutorId(String dates, int status, long tutorId);
}

