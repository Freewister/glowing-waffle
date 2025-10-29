package org.test;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import java.util.Map;

public class DoubleQuoteResource implements QuarkusTestResourceLifecycleManager {

  @Override
  public Map<String, String> start() {
    return Map.of(
        "mp.messaging.outgoing.\"\"foo.bar-topic\"\".connector", "smallrye-kafka",
        "mp.messaging.outgoing.\"\"foo.bar-topic\"\".value.serializer", "io.quarkus.kafka.client.serialization.ObjectMapperSerializer",
        "mp.messaging.outgoing.\"\"foo.bar-topic\"\".value.deserializer", "io.quarkus.kafka.client.serialization.ObjectMapperDeserializer"

    );
  }

  @Override
  public void stop() {

  }
}
