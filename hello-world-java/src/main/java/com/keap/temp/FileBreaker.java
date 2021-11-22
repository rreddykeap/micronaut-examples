package com.keap.temp;

import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

public class FileBreaker {
  @SneakyThrows
  public static void main(String[] args) {
      File file = new File("apps.txt");
      List<String> lines = FileUtils.readLines(file, "UTF-8");
      lines.forEach(l -> {
          String[] tenants = l.split(",");
          Stream.of(tenants).forEach(tenant -> {
              try {
                  FileUtils.writeStringToFile(new File("todo/" + tenant.trim()), tenant.trim());
              } catch (IOException e) {
                  e.printStackTrace();
              }
          });
      });
  }
}
