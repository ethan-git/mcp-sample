package org.fubabz.mcp.llm.service;

import static java.util.Collections.EMPTY_LIST;
import static java.util.Objects.isNull;

import java.util.ArrayList;
import java.util.List;

import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Service
public class LLMChatService {

    private static final String PROMPT_SUMMARIZE_INSTRUCTIONS = """
            You are analyzing a live chat from viewers who are watching a streamer. \s
            describe the overall mood of the conversation using exactly 2-3 adjectives. \s
            For each adjective, add a matching emoji to represent the mood. \s
            You must strictly follow this format without any extra explanation:

            Mood: [adjective1] [emoji1], [adjective2] [emoji2], [optional adjective3] [emoji3]
            """;
    private static final String PROMPT_ACTION_ITEM_INSTRUCTIONS = """
            You are analyzing a live chat from viewers who are watching a streamer. \s
            From the chat, extract any action items that viewers are requesting the streamer to do. \s
            Summarize up to 3 action items, prioritizing by how frequently they are mentioned or their importance.

            If there are no clear requests, respond with "No action items."

            Format your answer strictly like this:

            Action Items:
            1. [First most important/requested item]
            2. [Second item] (if any)
            3. [Third item] (if any)
            """;
    private final OllamaChatModel ollamaChatClient;

    public String summarize(List<ChatMessage> messages) {
        SystemMessage systemMessage = new SystemMessage(PROMPT_SUMMARIZE_INSTRUCTIONS);
        UserMessage userMessage = new UserMessage(messagesToPlainText(messages));

        Prompt prompt = new Prompt(List.of(systemMessage, userMessage));
        ChatResponse chatResponse = ollamaChatClient.call(prompt);
        log.debug("LLM summarize response: {}", chatResponse);
        String response = chatResponse.getResult().getOutput().getText();
        log.debug("response: {}", response);
        return response;
    }

    private String messagesToPlainText(List<ChatMessage> messages) {
        if (isNull(messages)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (ChatMessage msg : messages) {
            sb.append(msg.getName()).append(": ").append(msg.getMessage()).append("\n");
        }
        return sb.toString();
    }

    public String actionItem(List<ChatMessage> messages) {
        SystemMessage systemMessage = new SystemMessage(PROMPT_ACTION_ITEM_INSTRUCTIONS);
        UserMessage userMessage = new UserMessage(messagesToPlainText(messages));

        Prompt prompt = new Prompt(List.of(systemMessage, userMessage));
        ChatResponse chatResponse = ollamaChatClient.call(prompt);
        log.debug("LLM actionItem response: {}", chatResponse);
        String response = chatResponse.getResult().getOutput().getText();
        log.debug("response: {}", response);
        return response;
    }
}
