package com.projects.LibraryManagementSystem.repository;

import com.projects.LibraryManagementSystem.model.Txn;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;



import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@ActiveProfiles("test")
public class TxnRepositoryTest {

    @Autowired
    private TxnRepository txnRepository;

    @BeforeEach
    void setUp(){
        Txn txn = Txn.builder().txnId("txnId1").
                build();
        txnRepository.save(txn);
    }

    @Test
    void testFindByTxnId(){
        Txn txn1 = txnRepository.findByTxnId("txnId1");
         assertNotNull(txn1);
    }
}

