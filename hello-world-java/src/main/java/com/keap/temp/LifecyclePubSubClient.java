package com.keap.temp;

import io.micronaut.gcp.pubsub.annotation.PubSubClient;
import io.micronaut.gcp.pubsub.annotation.Topic;

@PubSubClient
public interface LifecyclePubSubClient {

  @Topic("v1.application-lifecycle-event")
  void publishEvent(PubsubMessage message);
}
