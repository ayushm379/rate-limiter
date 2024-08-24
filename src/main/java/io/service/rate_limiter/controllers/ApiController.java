package io.service.rate_limiter.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class ApiController {

    @GetMapping("/rate-limited")
    public String rateLimited(@RequestHeader("X-USER-Id") String userId) {
        return "Hello " + userId + " From Rate Limited API";
    }

    @GetMapping("/not-rate-limited")
    public String nonRateLimited(@RequestHeader("X-USER-Id") String userId) {
        return "Hello " + userId + " From Non Rate Limited API";
    }

}
