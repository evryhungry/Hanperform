package com.capstone.hanperform.performances.controller;

import com.capstone.hanperform.performances.dto.PerformancesRequestDto;
import com.capstone.hanperform.performances.dto.PerformancesResponseDto;
import com.capstone.hanperform.performances.service.PerformancesService;
import com.capstone.hanperform.ticket.dto.TicketDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/performances")
public class PerformancesController {

    private final PerformancesService performancesService;

    @GetMapping
    public String getAllPerformances(Model model) {
        List<PerformancesResponseDto> performances = performancesService.getAllPerformances();
        model.addAttribute("performances", performances);
        return "index";  // index.html을 반환
    }

    @GetMapping("/{id}")
    public String getPerformanceById(@PathVariable Long id, Model model) {
        PerformancesResponseDto performance = performancesService.getPerformanceById(id);
        model.addAttribute("startDateFormatted", performance.getStartDate());
        model.addAttribute("endDateFormatted", performance.getEndDate());
        model.addAttribute("performance", performance);
        model.addAttribute("ticketDto", new TicketDto());
        return "performance_detail";  // performance_detail.html을 반환
    }

    @GetMapping("/add")
    public String showAddPerformanceForm(Model model) {
        model.addAttribute("performanceRequestDto", new PerformancesRequestDto());

        return "performance_form";  // performance_form.html을 반환
    }

    @PostMapping("/add")
    public String addPerformance(@ModelAttribute PerformancesRequestDto requestDto,
                                 @RequestParam MultipartFile image,
                                 RedirectAttributes redirectAttributes) {

        Long performanceId = performancesService.createPerformances(requestDto, image);
        redirectAttributes.addAttribute("id", performanceId);
        return "redirect:/index";
    }

    @GetMapping("/{id}/edit")
    public String editPerformanceForm(@PathVariable Long id, Model model) {
        PerformancesResponseDto performance = performancesService.getPerformanceById(id);
        model.addAttribute("performance", performance);
        return "performance_edit";  // performance_edit.html을 반환
    }

    @PostMapping("/{id}/edit")
    public String editPerformance(@PathVariable Long id,
                                  @ModelAttribute PerformancesRequestDto requestDto,
                                  @RequestParam MultipartFile image) {
        performancesService.updatePerformance(id, requestDto);
        if (image != null && !image.isEmpty()) {
            performancesService.updatePoster(id, image);
        }
        return "redirect:/performances/{id}";
    }

    @GetMapping("/{id}/delete")
    public String deletePerformance(@PathVariable Long id) {
        performancesService.deletePerformanceById(id);
        return "redirect:/performances";
    }
}
