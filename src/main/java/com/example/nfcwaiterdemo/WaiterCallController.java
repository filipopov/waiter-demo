package com.example.nfcwaiterdemo;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class WaiterCallController {

  private final SimpMessagingTemplate messagingTemplate;

  public WaiterCallController(SimpMessagingTemplate messagingTemplate) {
    this.messagingTemplate = messagingTemplate;
  }

  @PostMapping("/call-waiter")
  public void callWaiter(@RequestBody CallRequest callRequest) {
    messagingTemplate.convertAndSend("/topic/waiter-calls", callRequest);
  }

  static class CallRequest {
    private String tableId;
    public String getTableId() { return tableId; }
    public void setTableId(String tableId) { this.tableId = tableId; }
  }
}
