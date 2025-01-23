package com.capstone.hanperform;

import com.capstone.hanperform.performances.dto.PerformancesResponseDto;
import com.capstone.hanperform.performances.service.PerformancesService;
import com.capstone.hanperform.users.dto.UserDto;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@Slf4j
public class HomeController {

    private final PerformancesService performancesService;

    public HomeController(PerformancesService performancesService) {
        this.performancesService = performancesService;
    }

    // 공연 목록 페이지
    @GetMapping("/index")
    public String showIndex(Model model, HttpSession session) {
        log.info("====> showIndex() 실행됨");
        // 세션에서 로그인한 사용자 확인 (디버깅용 로그 추가)
        UserDto loggedInUser = (UserDto) session.getAttribute("loggedInUser");
        log.info("로그인된 사용자: {}", loggedInUser);

        // 로그인한 사용자만 접근 가능하게 하려면 유지
        // if (loggedInUser == null) {
        //     return "redirect:/users/login";
        // }

        // 공연 목록 불러오기 (디버깅용 로그 추가)
        List<PerformancesResponseDto> performances = performancesService.getAllPerformances();
        log.info("불러온 공연 목록 수: {}", performances.size());

        // 모델에 공연 목록 추가
        model.addAttribute("performances", performances);
        return "index"; // index.html 렌더링
    }
}
