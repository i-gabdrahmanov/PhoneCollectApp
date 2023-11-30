package com.dev.alex.phonecollect.service;

import com.dev.alex.phonecollect.model.OperatorEnum;
import com.dev.alex.phonecollect.model.Phone;

import java.io.File;
import java.util.List;

public interface PhoneService {
    List<Phone> collectNumbers (OperatorEnum operator, boolean multithread);

    void collectAllNumbers();

    File getFileForMessage(OperatorEnum operator);

    void deleteOldPhones();
}
