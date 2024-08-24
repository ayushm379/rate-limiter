package io.service.rate_limiter.rateLimiter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import io.service.rate_limiter.services.CacheService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class RateLimiterInterceptor implements HandlerInterceptor {

    @Autowired
    private CacheService cacheService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String userId = request.getHeader("X-USER-Id");
        if (!cacheService.canAccess(userId)) {
            response.setStatus(429);
            response.getWriter().write("Too Many Requests, Try After " + cacheService.getTtl(userId) + " Seconds");
            return false;
        }
        cacheService.incrementAccess(userId);
        return true;
    }

}
