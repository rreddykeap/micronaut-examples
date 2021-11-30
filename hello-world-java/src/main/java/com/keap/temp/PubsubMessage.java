package com.keap.temp;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PubsubMessage {

    // sample json
    // {"action":"DELETED", "billingSystemId":"12451892", "edition":"Keap Mobile Free Trial Edition", "messageId":"55bb0174-a769-45a3-b261-b4eaca2d2af9", "messagePublished":"2021-11-01T03:00:02.632Z", "tenantId":"zmd512"}

    String tenantId;
    String action;
    String messageId;
}
