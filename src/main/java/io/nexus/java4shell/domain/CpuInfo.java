package io.nexus.java4shell.domain;

public record CpuInfo(
    boolean avx512f,
    boolean avx512bw,
    boolean avx512vbmi,
    boolean bmi2,
    boolean flagsVisible
) {
  public boolean isCompatible() {
    return flagsVisible && avx512f && avx512bw && avx512vbmi && bmi2;
  }
}
