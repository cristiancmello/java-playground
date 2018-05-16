package com.example.nio.filesystems.read.directory;


import java.io.IOException;
import java.nio.file.*;

public class Application {
  public static void main(String[] args) {
    Path directory = FileSystems.getDefault().getPath("Examples/FileTree/Dir2");
    try (DirectoryStream<Path> contents = Files.newDirectoryStream(directory)) {
      for (Path file : contents) {
        System.out.println(file.getFileName());
      }
    } catch (IOException | DirectoryIteratorException e) {
      e.printStackTrace();
    }
  }
}
