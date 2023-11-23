package com.dev.alex.phonecollect.service;

import com.dev.alex.phonecollect.model.OperatorEnum;
import com.dev.alex.phonecollect.repository.PhoneRepository;
import com.dev.alex.phonecollect.utils.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import java.io.IOException;

@Service
public class PhoneServiceImpl implements PhoneService {

    @Autowired
    private PhoneCollector phoneCollector;

    @Autowired
    private PhoneRepository phoneRepository;

    @Autowired
    private TransactionTemplate transactionTemplate;



    @Override
    public String collectNumbers(OperatorEnum operator) {
        String jsonString;
        try {
            jsonString = phoneCollector.collectPhones(operator);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return jsonString;
    }


}
