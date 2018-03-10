package io.coupling.classes;

public class Receiver {

  public void receive(String message) {
    System.out.println(message);
  }

  public void forward(String message, Receiver receiver) {
    receiver.receive(message);
  }
}
