package com.teachMeSkills.lesson14.homework.task1.service;

import com.teachMeSkills.lesson14.homework.task1.consts.PathConstants;
import com.teachMeSkills.lesson14.homework.task1.exceptions.DocumentNumberFormatException;
import com.teachMeSkills.lesson14.homework.task1.validator.DocumentNumberValidator;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class WriteReportService {

    public static void writeReportFromFile(String path) {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                appendReportFile(line);
            }
        } catch (FileNotFoundException e) {
            String message = String.format("File with path '%s' not found", path);
            System.out.println(message);
            WriteFileService.writeNewLineToFile(PathConstants.ERROR_LOG, message);
            WriteFileService.writeNewLineToFile(PathConstants.EXECUTION_LOG, message);
        } catch (IOException e) {
            WriteFileService.writeNewLineToFile(PathConstants.ERROR_LOG, e.getMessage());
            WriteFileService.writeNewLineToFile(PathConstants.EXECUTION_LOG, e.getMessage());
        } catch (Exception e) {
            WriteFileService.writeNewLineToFile(PathConstants.ERROR_LOG, e.getMessage());
            WriteFileService.writeNewLineToFile(PathConstants.EXECUTION_LOG, e.getMessage());
        }
    }

    private static void appendReportFile(String line) {
        try {
            if (DocumentNumberValidator.isDocumentNumberValid(line)) {
                WriteFileService.writeReport(line);
                System.out.println(line + " - is VALID number");
                WriteFileService.writeNewLineToFile(PathConstants.EXECUTION_LOG, line + " - is VALID number");
            }
        } catch (DocumentNumberFormatException e) {
            String logMessage = line + " - is INVALID number: " + e.getMessage();
            System.out.println(logMessage);
            WriteFileService.writeNewLineToFile(PathConstants.INVALID_NUMBERS_LOG, logMessage);
            WriteFileService.writeNewLineToFile(PathConstants.EXECUTION_LOG, logMessage);
        }
    }
}
