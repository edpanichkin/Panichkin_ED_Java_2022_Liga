package ru.edpanichkin.tasktracker.exception;

public class FilePathException extends RuntimeException {

  public FilePathException() {
  }

  public FilePathException(String message) {
    super(message);
  }

}