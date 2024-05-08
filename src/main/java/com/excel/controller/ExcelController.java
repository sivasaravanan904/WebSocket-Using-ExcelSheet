package com.excel.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;

import com.excel.service.ExcelService;

@RestController
public class ExcelController {

    @Autowired
    private ExcelService excelService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Scheduled(fixedRate = 2000) // every 2 seconds
    public void sendDataAutomatically() {
        List<String[]> excelData = excelService.getData();
        messagingTemplate.convertAndSend("/topic/excelData", excelData);
        System.out.println("excelData" + Arrays.deepToString(excelData.toArray()));
    }
}
//package com.excel.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.messaging.handler.annotation.MessageMapping;
//import org.springframework.messaging.handler.annotation.SendTo;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.excel.service.ExcelService;
//
//import java.util.Arrays;
//import java.util.List;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.messaging.handler.annotation.MessageMapping;
//import org.springframework.messaging.handler.annotation.SendTo;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import com.excel.service.ExcelService;
//
//@CrossOrigin
//@RestController
//public class ExcelController {
//
//    @Autowired
//    private ExcelService excelService;
//
//    @MessageMapping("/fetchExcel")
//    @SendTo("/topic/excelData")
//    public List<String[]> sendExcelData() {
//        return excelService.getData();
//    }
//
//    @Scheduled(fixedRate = 10000)
//    public void sendDataAutomatically() {
//        sendExcelData();
//        List<String[]> data = sendExcelData();
//        System.out.println("excelData" + Arrays.deepToString(data.toArray()));
//    }
//
//    @GetMapping("/data")
//    public List<String[]> getExcelData() {
//        return excelService.getData();
//    }
//}
