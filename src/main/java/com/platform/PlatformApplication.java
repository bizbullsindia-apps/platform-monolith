package com.platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@SpringBootApplication
@RestController
public class PlatformApplication {
  public static void main(String[] args) {
    SpringApplication.run(PlatformApplication.class, args);
  }

  @GetMapping("/api/health")
  public Map<String, Object> health() {
    return Map.of("status", "UP", "message", "Platform Monolith Running - One Platform Many Brands", "tenant", "default");
  }

  @GetMapping("/")
  public Map<String, Object> home() {
    return Map.of("app", "Platform Monolith", "docs", "/api/health");
  }
}
