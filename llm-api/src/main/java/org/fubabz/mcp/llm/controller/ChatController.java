package org.fubabz.mcp.llm.controller;

import org.fubabz.mcp.llm.service.LLMService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class ChatController {

    private final LLMService llmService;

    @PostMapping("/summarize")
    public String summarize(@RequestBody SummarizeRequest summarizeRequest) {
        return llmService.askLLM(summarizeRequest.getPromptMessage(), summarizeRequest.getHistoryId());
    }
}
