package com.teachMeSkills.lesson14.homework.task1.run;

import com.teachMeSkills.lesson14.homework.task1.consts.PathConstants;
import com.teachMeSkills.lesson14.homework.task1.service.AppService;
import com.teachMeSkills.lesson14.homework.task1.service.WriteReportService;

import java.util.Scanner;

public class Runner {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.printf("Enter path to file or just use default file: '%s'\n", PathConstants.DEFAULT_PATH_FILE);
        String path = scanner.nextLine();
        AppService.startApp();
        WriteReportService.writeReportFromFile(path);
        AppService.closeApp();
    }

}
