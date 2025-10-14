package com.sup.sbai.controller;


import com.sup.sbai.domain.ChatHistory;
import com.sup.sbai.service.AiService;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/chat")
@CrossOrigin(origins = "*")
public class AiController {

    private final AiService service;

    public AiController(AiService service) {
        this.service = service;
    }

    @PostMapping("/ask")
    public ChatHistory ask(@RequestBody Map<String, String> body) {
        String prompt = body.get("prompt");
        return service.askAzure(prompt);
    }

    @GetMapping("/history")
    public List<ChatHistory> getAll() {
        return service.getAll();
    }
}
