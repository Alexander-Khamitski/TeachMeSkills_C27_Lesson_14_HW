package com.teachMeSkills.lesson14.homework.task1.service;

import com.teachMeSkills.lesson14.homework.task1.consts.PathConstants;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class AppService {

    public static void startApp() {
        deleteLogFile(PathConstants.ERROR_LOG);
        deleteLogFile(PathConstants.EXECUTION_LOG);
        createLogFile();
        WriteFileService.prepareDefaultFiles();
    }

    public static String getPathToFile() {
        Scanner scanner = new Scanner(System.in);
        String startMessage = String.format("Enter path to file or just use default file: '%s'.", PathConstants.DEFAULT_PATH_FILE);
        System.out.println(startMessage);
        String path = scanner.nextLine();
        WriteFileService.writeNewLineToFile(PathConstants.EXECUTION_LOG, startMessage);
        WriteFileService.writeNewLineToFile(PathConstants.EXECUTION_LOG, String.format("User entered next path: '%s'.", path));
        return path;
    }

    public static String getPathToFile() {
        Scanner scanner = new Scanner(System.in);
        String startAppMessage = String.format("Enter path to file or just use default file: '%s'\n", PathConstants.DEFAULT_PATH_FILE);
        System.out.printf(startAppMessage);
        WriteFileService.writeFile(PathConstants.EXECUTION_LOG, startAppMessage);
        String path = scanner.nextLine();
        WriteFileService.writeFile(PathConstants.EXECUTION_LOG, "Entered by user path: " + path);
        return path;
    }

    private static void deleteLogFile(String path) {
        File file = new File(path);
        if (file.exists()) {
            file.delete();
        }
    }

    private static void createLogFile() {
        File executionLog = new File(PathConstants.EXECUTION_LOG);
        File errorLog = new File(PathConstants.ERROR_LOG);
        try {

            executionLog.createNewFile();
            errorLog.createNewFile();

            String startMessage = "Start";
            System.out.println(startMessage);
            WriteFileService.writeNewLineToFile(PathConstants.EXECUTION_LOG, startMessage);

            String executionLogFileMessage = "File with next path has been created: " + PathConstants.EXECUTION_LOG;
            System.out.println(executionLogFileMessage);
            WriteFileService.writeNewLineToFile(PathConstants.EXECUTION_LOG, executionLogFileMessage);

            String errorLogFileMessage = "File with next path has been created: " + PathConstants.ERROR_LOG;
            System.out.println(errorLogFileMessage);
            WriteFileService.writeNewLineToFile(PathConstants.EXECUTION_LOG, errorLogFileMessage);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void closeApp() {
        String path = PathConstants.EXECUTION_LOG;
        File executionLog = new File(path);
        String endMessage = "End";
        if (executionLog.exists()) {
            WriteFileService.writeNewLineToFile(path, endMessage);
        }
        System.out.println(endMessage);
    }
}
