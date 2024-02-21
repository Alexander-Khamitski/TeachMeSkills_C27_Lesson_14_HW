package com.teachMeSkills.lesson14.homework.task1.service;

import com.teachMeSkills.lesson14.homework.task1.consts.PathConstants;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static com.teachMeSkills.lesson14.homework.task1.validator.DocumentNumberValidator.isDocumentNumberValid;

public class WriteReportService {

    public static void writeReportFromFile(String path) {
        try (FileReader fr = new FileReader(path);
             BufferedReader br = new BufferedReader(fr)) {
            WriteFileService.prepareDefaultFiles();
            String line;
            while ((line = br.readLine()) != null) {
                appendReportFile(line);
            }
        } catch (IOException e) {
            String message = String.format("File with path '%s' not found", path);
            System.out.println(message);
            WriteFileService.writeFile(PathConstants.ERROR_LOG, message);
        }
    }

    private static void appendReportFile(String line) {
        if (isDocumentNumberValid(line)) {
            WriteFileService.writeReport(line);
        }
    }
}
