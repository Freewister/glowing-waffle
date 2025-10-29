package org.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.quarkus.test.common.WithTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.kafka.InjectKafkaCompanion;
import io.quarkus.test.kafka.KafkaCompanionResource;
import io.smallrye.reactive.messaging.kafka.companion.KafkaCompanion;
import jakarta.inject.Inject;
import java.time.Duration;
import java.util.List;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.jupiter.api.Test;

@QuarkusTest
@WithTestResource(KafkaCompanionResource.class)
class FailingMessageTransmitterTest {

  @Inject
  MessageTransmitter messageTransmitter;

  @InjectKafkaCompanion
  KafkaCompanion companion;

  @Test
  void foo() {
    // given

    // when
    messageTransmitter.emit("Hello Quarkus!");

    // then
    List<String> actual = companion.consumeStrings()
        .fromTopics("foo.bar-topic")
        .awaitRecords(1, Duration.ofSeconds(30))
        .stream()
        .map(ConsumerRecord::value)
        .toList();

    assertEquals(1, actual.size());
  }
}