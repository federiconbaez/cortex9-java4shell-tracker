package io.nexus.java4shell.domain;

import java.util.List;

public record RuntimeAssessment(
    String runtimeId,
    Verdict verdict,
    RuntimeInfo runtime,
    CpuInfo cpu,
    MitigationInfo mitigation,
    Reachability reachability,
    List<Evidence> evidence,
    List<String> limitations
) {}
