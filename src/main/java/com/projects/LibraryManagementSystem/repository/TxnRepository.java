package com.projects.LibraryManagementSystem.repository;

import com.projects.LibraryManagementSystem.model.Txn;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TxnRepository extends JpaRepository<Txn,Integer> {

    Txn findByTxnId(String txnId);
}
