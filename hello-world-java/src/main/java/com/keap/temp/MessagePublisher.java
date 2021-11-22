package com.keap.temp;

import io.micronaut.scheduling.annotation.Scheduled;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileFilter;
import java.util.List;

@Singleton
public class MessagePublisher {

  @Inject
  LifecyclePubSubClient lifecyclePubSubClient;

  @Scheduled(initialDelay = "10s", fixedDelay = "10s")
  public void sendMessage() {
    String tenant = readTenant();
    if (tenant != null) {
      System.out.println("Publishing message for: " + tenant);
      lifecyclePubSubClient.publishEvent(new PubsubMessage(tenant, "DUMMY"));
    }
  }

  @SneakyThrows
  private String readTenant() {
    File dir = new File("todo");

    File[] files = dir.listFiles(new FileFilter() {
      boolean first = true;
      public boolean accept(final File pathname) {
        if (first) {
          first = false;
          return true;
        }
        return false;
      }
    });

    if (files.length == 0) {
      System.out.println("All done!");
      return null;
    }

    List<String> lines = FileUtils.readLines(files[0], "UTF-8");

    // move file
    FileUtils.moveFileToDirectory(files[0], new File("done"), true);

    return lines.get(0);
  }
}
