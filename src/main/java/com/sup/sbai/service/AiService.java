package com.sup.sbai.service;

import com.sup.sbai.domain.ChatHistory;
import com.sup.sbai.dao.ChatHistoryRepository;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class AiService {

    private final ChatClient chatClient;
    private final ChatHistoryRepository repository;

    public AiService(ChatClient.Builder chatClientBuilder, ChatHistoryRepository repository) {
        this.chatClient = chatClientBuilder.build();
        this.repository = repository;
    }

    public ChatHistory askAzure(String prompt) {
        String reply = chatClient.prompt()
                .user(prompt)
                .call()
                .content();

        ChatHistory history = new ChatHistory();
        history.setUserInput(prompt);
        history.setAiResponse(reply);
        return repository.save(history);
    }

    public java.util.List<ChatHistory> getAll() {
        return repository.findAll();
    }
}
