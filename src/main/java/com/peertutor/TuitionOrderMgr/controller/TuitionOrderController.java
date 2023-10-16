package com.peertutor.TuitionOrderMgr.controller;

import com.peertutor.TuitionOrderMgr.exception.ExistingTuitionOrderException;
import com.peertutor.TuitionOrderMgr.model.viewmodel.request.TuitionOrderReq;
import com.peertutor.TuitionOrderMgr.model.viewmodel.response.TuitionOrderRes;
import com.peertutor.TuitionOrderMgr.repository.TuitionOrderRepository;
import com.peertutor.TuitionOrderMgr.service.AuthService;
import com.peertutor.TuitionOrderMgr.service.ExternalCallService;
import com.peertutor.TuitionOrderMgr.service.TuitionOrderService;
import com.peertutor.TuitionOrderMgr.service.dto.*;
import com.peertutor.TuitionOrderMgr.util.AppConfig;
import io.github.jhipster.web.util.PaginationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path = "/tuition-order-mgr")
public class TuitionOrderController {
    @Autowired
    AppConfig appConfig;
    @Autowired
    private TuitionOrderRepository tuitionOrderRepository;
    @Autowired
    private TuitionOrderService tuitionOrderService;
    @Autowired
    private AuthService authService;

    @Autowired
    private ExternalCallService externalCallService;

    @GetMapping(path = "/health")
    public @ResponseBody String healthCheck() {
        return "Ok";
    }

    @PostMapping(path = "/tuitionOrder")
    @ExceptionHandler(ExistingTuitionOrderException.class)
    public @ResponseBody ResponseEntity createTuitionProfile(
            @RequestHeader("Name") String name,
            @RequestBody @Valid TuitionOrderReq req) {
        TuitionOrderDTO savedTuitionOrder;
        req.name = name;

        try {
            savedTuitionOrder = tuitionOrderService.createTuitionOrder(req);
        } catch (ExistingTuitionOrderException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("You have an ongoing tuition order with the tutor");
        }


        if (savedTuitionOrder == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }

        TuitionOrderRes res = new TuitionOrderRes(savedTuitionOrder);

        return ResponseEntity.ok().body(res);
    }

    @GetMapping("/detailedTuitionOrders")
    public ResponseEntity<List<TuitionOrderDetailedDTO>> getAllDetailedTuitionOrders(
            @RequestParam(name = "studentId") Optional<Long> studentId,
            @RequestParam(name = "tutorId") Optional<Long> tutorId,
            @RequestParam(name = "status") Optional<Integer> status,
            @PageableDefault(size = 500) Pageable pageable
    ) {
        TuitionOrderCriteria criteria = new TuitionOrderCriteria(studentId, tutorId, status);
        Page<TuitionOrderDetailedDTO> page = tuitionOrderService.getTuitionOrderDetailsByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);

        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/tuitionOrders")
    public ResponseEntity<List<TuitionOrderDTO>> getTuitionOrderByCriteria(
            @RequestParam(name = "studentId") Optional<Long> studentId,
            @RequestParam(name = "tutorId") Optional<Long> tutorId,
            @RequestParam(name = "status") Optional<Integer> status,
            Pageable pageable
    ) {
        TuitionOrderCriteria criteria = new TuitionOrderCriteria(studentId, tutorId, status);
        Page<TuitionOrderDTO> page = tuitionOrderService.getTuitionOrderByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/tuitionOrder")
    public ResponseEntity<TuitionOrderDTO> getTuitionOrderByCriteria(
            @RequestParam(name = "id") Long id
    ) {
        // get tutor name
        TuitionOrderDTO tuitionOrder = tuitionOrderService.getTuitionOrderById(id);

        if (tuitionOrder == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        return ResponseEntity.ok().body(tuitionOrder);
    }
}
