package io.coupling.classes;

public class Caller {

  private Receiver receiver;

  public void call(String message) {
    receiver.receive(message);
  }

  public void chainCall(String message) {
    receiver.forward(message, receiver);
  }
}
