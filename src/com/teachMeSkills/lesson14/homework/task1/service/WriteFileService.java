package com.teachMeSkills.lesson14.homework.task1.service;

import com.teachMeSkills.lesson14.homework.task1.consts.PathConstants;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import static com.teachMeSkills.lesson14.homework.task1.validator.DocumentNumberValidator.isDocNumber;

public class WriteFileService {

    public static void writeReport(String documentNumber) {
        String path = isDocNumber(documentNumber) ?
                PathConstants.VALID_DOCUMENT_NUMBERS : PathConstants.VALID_CONTRACT_NUMBERS;

        writeFile(path, documentNumber);
    }

    public static void writeFile(String path, String value) {
        try {
            value += "\n";
            Files.write(Path.of(path), value.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            WriteFileService.writeFile(PathConstants.ERROR_LOG, e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static void prepareDefaultFiles() {
        deleteFileIfExist(PathConstants.VALID_DOCUMENT_NUMBERS);
        deleteFileIfExist(PathConstants.VALID_CONTRACT_NUMBERS);
        deleteFileIfExist(PathConstants.INVALID_NUMBERS_LOG);
        deleteFileIfExist(PathConstants.ERROR_LOG);
        createFileWithPath(PathConstants.VALID_DOCUMENT_NUMBERS);
        createFileWithPath(PathConstants.VALID_CONTRACT_NUMBERS);
        createFileWithPath(PathConstants.ERROR_LOG);
        createFileWithPath(PathConstants.INVALID_NUMBERS_LOG);
    }

    private static void deleteFileIfExist(String path) {
        File file = new File(path);
        if (file.exists()) {
            file.delete();
            String message = "File with next path has been DELETED: " + path;
            System.out.println(message);
            WriteFileService.writeFile(PathConstants.EXECUTION_LOG, message);
        } else {
            String message = "File with path does not exist: " + path;
            System.out.println(message);
            WriteFileService.writeFile(PathConstants.EXECUTION_LOG, message);
        }
    }

    private static void createFileWithPath(String path) {
        File file = new File(path);
        try {
            if (!file.exists()) {
                file.createNewFile();
                String message = "File with next path has been CREATED: " + path;
                System.out.println(message);
                WriteFileService.writeFile(PathConstants.EXECUTION_LOG, message);
            } else {
                String message = "File with path exists: " + path;
                System.out.println(message);
                WriteFileService.writeFile(PathConstants.EXECUTION_LOG, message);
            }
        } catch (IOException e) {
            WriteFileService.writeFile(PathConstants.ERROR_LOG, e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
