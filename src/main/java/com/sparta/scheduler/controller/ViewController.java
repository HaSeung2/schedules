package com.sparta.scheduler.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/view")
public class ViewController {

    @GetMapping("/writeView")
    public String doWriteView() {
        return "contentView/writeView";
    }

    @GetMapping("/scheduleBoardView")
    public String doBoardView(@RequestParam("schedule_id") Long schedule_id, Model model) {
        model.addAttribute("schedule_id", schedule_id);
        return "contentView/scheduleBoardView";
    }

    @GetMapping("/deleteView/{schedule_id}")
    public String doDeleteView(@PathVariable("schedule_id") Long schedule_id) {
        return "contentView/deleteView";
    }

    @GetMapping("/modifyView/{schedule_id}")
    public String doModifyView(@PathVariable("schedule_id") Long schedule_id) {
        return "contentView/modifyView";
    }

    @GetMapping("/myScheduleView")
    public String doMyScheduleView() {
        return "contentView/MyScheduleView";
    }
}
