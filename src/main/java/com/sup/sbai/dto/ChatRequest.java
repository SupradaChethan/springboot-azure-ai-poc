package com.sup.sbai.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
public class ChatRequest {
    private List<Map<String, String>> messages;
}
