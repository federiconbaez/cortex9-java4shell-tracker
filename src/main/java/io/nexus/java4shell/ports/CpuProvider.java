package io.nexus.java4shell.ports;

import io.nexus.java4shell.domain.CpuInfo;

public interface CpuProvider {
  CpuInfo probe();
}
