package org.fubabz.mcp.llm.service;

import java.util.List;

import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Service
public class LLMService {

    private static final String PROMPT_GENERAL_INSTRUCTIONS = """
               [대화 요약과 감정 분석]
                다음 대화를 요약하고 감정을 분석해줘:

                대화 내용:
                %s
                """;
    private final OllamaChatModel ollamaChatClient;

    public String askLLM(String promptMessage, String historyId) {
        SystemMessage generalInstructionsSystemMessage = new SystemMessage(PROMPT_GENERAL_INSTRUCTIONS);
        UserMessage currentPromptMessage = new UserMessage(promptMessage);

        Prompt prompt = new Prompt(List.of(generalInstructionsSystemMessage, currentPromptMessage));
        String response = ollamaChatClient.call(prompt).getResult().getOutput().getText();

        return response;
    }
}
