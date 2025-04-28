package org.fubabz.mcp.llm.controller;

import java.util.List;

import org.fubabz.mcp.llm.service.ChatMessage;

import lombok.Value;

@Value
public class SummarizeRequest {

    private List<ChatMessage> messages;

}
