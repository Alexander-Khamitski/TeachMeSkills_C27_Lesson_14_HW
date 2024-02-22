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
    }

    public static String getPathToFile() {
        Scanner scanner = new Scanner(System.in);
        String startAppMessage = String.format("Enter path to file or just use default file: '%s'\n", PathConstants.DEFAULT_PATH_FILE);
        System.out.printf(startAppMessage);
        WriteFileService.writeFile(PathConstants.EXECUTION_LOG, startAppMessage);
        return scanner.nextLine();
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
            WriteFileService.writeFile(PathConstants.EXECUTION_LOG, startMessage);

            String executionLogFileMessage = "File with next path has been created: " + PathConstants.EXECUTION_LOG;
            System.out.println(executionLogFileMessage);
            WriteFileService.writeFile(PathConstants.EXECUTION_LOG, executionLogFileMessage);

            String errorLogFileMessage = "File with next path has been created: " + PathConstants.ERROR_LOG;
            System.out.println(errorLogFileMessage);
            WriteFileService.writeFile(PathConstants.EXECUTION_LOG, errorLogFileMessage);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void closeApp() {
        String path = PathConstants.EXECUTION_LOG;
        File executionLog = new File(path);
        String endMessage = "End";
        if (executionLog.exists()) {
            WriteFileService.writeFile(path, endMessage);
        }
        System.out.println(endMessage);
    }
}
