package com.teachMeSkills.lesson14.homework.task1.run;

import com.teachMeSkills.lesson14.homework.task1.service.AppService;
import com.teachMeSkills.lesson14.homework.task1.service.WriteReportService;

public class Runner {

    public static void main(String[] args) {
        AppService.startApp();
        String path = AppService.getPathToFile();
        WriteReportService.writeReportFromFile(path);
        AppService.closeApp();
    }

}
