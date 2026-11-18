package io.nexus.java4shell.ports;

import io.nexus.java4shell.domain.RuntimeInfo;
import java.util.Optional;

public interface RuntimeProvider {
  Optional<RuntimeInfo> probe();
}
