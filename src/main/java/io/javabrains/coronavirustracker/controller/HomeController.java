package io.javabrains.coronavirustracker.controller;


import io.javabrains.coronavirustracker.models.LocationStats;
import io.javabrains.coronavirustracker.service.CoronaVirusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    CoronaVirusDataService coronaVirusDataService;

    @GetMapping("/home.html")
    public String home(Model model){
        List<LocationStats> allStats = coronaVirusDataService.getAllStats();
        int totalReportedCases = allStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
        int totalNewCases = allStats.stream().mapToInt(stat -> stat.getDiffFromPrevDay()).sum();
        model.addAttribute("locationStats",allStats);
        model.addAttribute("totalReportedCases",totalReportedCases);
        model.addAttribute("totalNewCases",totalNewCases);
        return "home";
    }
    @GetMapping("/recovered.html")
    public String recovered(Model model){
        List<LocationStats> allRecoveredStats = coronaVirusDataService.getAllStats();
        int totalRecoveredCases = allRecoveredStats.stream().mapToInt(stat ->stat.getLatestTotalCases()).sum();
        int totalNewRecoveredCases = allRecoveredStats.stream().mapToInt(stat ->stat.getDiffFromPrevDay()).sum();
        model.addAttribute("locationStats",allRecoveredStats);
        model.addAttribute("totalRecoveredCases",totalRecoveredCases);
        model.addAttribute("totalNewRecoveredCases",totalNewRecoveredCases);
        return "recovered";
    }
}
