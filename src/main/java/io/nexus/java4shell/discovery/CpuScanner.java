package io.nexus.java4shell.discovery;

import io.nexus.java4shell.domain.CpuInfo;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public final class CpuScanner {
  public CpuInfo scan() {
    boolean visible = false;
    boolean f = false, bw = false, vbmi = false, bmi2 = false;
    try {
      String content = Files.readString(Path.of("/proc/cpuinfo"));
      visible = true;
      f = content.contains("avx512f");
      bw = content.contains("avx512bw");
      vbmi = content.contains("avx512vbmi");
      bmi2 = content.contains("bmi2");
    } catch (IOException ignored) {}
    return new CpuInfo(f, bw, vbmi, bmi2, visible);
  }
}
