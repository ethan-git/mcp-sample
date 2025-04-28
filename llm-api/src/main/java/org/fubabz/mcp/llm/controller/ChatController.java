package org.fubabz.mcp.llm.controller;

import org.fubabz.mcp.llm.service.LLMChatService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class ChatController {

    private final LLMChatService llmChatService;

    @PostMapping("/summarize")
    public String summarize(@RequestBody SummarizeRequest summarizeRequest) {
        return llmChatService.summarize(summarizeRequest.getMessages());
    }

    @PostMapping("/action-item")
    public String actionItem(@RequestBody SummarizeRequest summarizeRequest) {
        return llmChatService.actionItem(summarizeRequest.getMessages());
    }
}
