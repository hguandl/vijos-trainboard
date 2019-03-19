package cn.edu.sustech.adoj.trainboard.web;

import cn.edu.sustech.adoj.trainboard.dao.MongoService;
import cn.edu.sustech.adoj.trainboard.model.RecordDisplay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class TrainBoard {
    private final MongoService mongoService;

    @Autowired
    public TrainBoard(MongoService mongoService) {
        this.mongoService = mongoService;
    }

    @GetMapping("/train/{pid}")
    public String train(@PathVariable String pid, Model model) {
        List<RecordDisplay> records = mongoService.getRecordList(pid);
        model.addAttribute("pid", pid);
        model.addAttribute("records", records);
        return "train";
    }
}
