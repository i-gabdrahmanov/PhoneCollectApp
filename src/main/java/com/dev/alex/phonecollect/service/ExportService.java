package com.dev.alex.phonecollect.service;

import com.dev.alex.phonecollect.model.OperatorEnum;
import com.dev.alex.phonecollect.model.Phone;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;

public interface ExportService {
    File exportToXls(List<Phone> phones, OperatorEnum operator, LocalDateTime requestDate);
}
