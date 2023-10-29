package com.dev.alex.phonecollect.service;

import com.dev.alex.phonecollect.model.OperatorEnum;
import com.dev.alex.phonecollect.model.Phone;
import com.dev.alex.phonecollect.repository.PhoneRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExportServiceImpl implements ExportService {

    @Autowired
    PhoneRepository repository;

    @Override
    public File exportToXls(OperatorEnum operator, LocalDateTime requestDate) {
       List<Phone> phones =  repository.findAllByOperatorAndRequestDateIsAfter(operator.getName(), requestDate); // parameter for date needed
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet();
        phones = phones.stream().distinct().sorted(Comparator.comparing(Phone::getCost)).collect(Collectors.toList());
        int rowCount = 0;
        for (Phone phone : phones) {
            Row row = sheet.createRow(rowCount++);
            row.createCell(0).setCellValue(phone.getPhoneNumber());
            row.createCell(1).setCellValue(phone.getCost());
            // Добавьте ячейки для остальных столбцов в соответствии с вашими требованиями
        }

        // Сохранение файла Excel
       File outputFile = new File(operator.getName() + " .xlsx");

        // Сохранение файла Excel
        try (FileOutputStream outputStream = new FileOutputStream(outputFile)) {
            workbook.write(outputStream);
            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return outputFile;
    }

}
