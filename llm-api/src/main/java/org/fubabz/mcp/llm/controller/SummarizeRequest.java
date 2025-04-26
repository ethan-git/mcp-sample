package org.fubabz.mcp.llm.controller;

import lombok.Value;

@Value
public class SummarizeRequest {

    private String promptMessage;
    private String historyId;

}
