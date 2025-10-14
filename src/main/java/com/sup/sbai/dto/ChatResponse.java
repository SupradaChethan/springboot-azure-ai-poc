package com.sup.sbai.dto;

import lombok.Data;

import java.util.List;

@Data
public class ChatResponse {
    private List<Choice> choices;

    @Data
    public static class Choice {
        private int index;
        private Message message;
    }

    @Data
    public static class Message {
        private String role;
        private String content;
    }
}
