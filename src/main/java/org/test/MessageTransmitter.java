package org.test;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

@ApplicationScoped
public class MessageTransmitter {

  @Inject
  @Channel("foo.bar-topic")
  Emitter<String> emitter;

  public void emit(String message) {
    emitter.send(message).toCompletableFuture().join();
  }
}
