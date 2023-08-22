package com.peertutor.TuitionOrderMgr.util.repository;

import com.peertutor.TuitionOrderMgr.util.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByName(String name);

    List<Account> findByNameIn(List<String> name);
}

