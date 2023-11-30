package com.dev.alex.phonecollect.service;

import com.dev.alex.phonecollect.model.OperatorEnum;

import java.util.List;

public interface PhoneService {
    String collectNumbers(OperatorEnum operatorEnum);
    List<String> collectNumbers (OperatorEnum operator, boolean multithread);
}
