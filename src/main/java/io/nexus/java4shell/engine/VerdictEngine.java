package io.nexus.java4shell.engine;

import io.nexus.java4shell.domain.*;
import io.nexus.java4shell.intelligence.VersionPolicy;

import java.util.ArrayList;
import java.util.List;

public final class VerdictEngine {
  private final VersionPolicy versionPolicy;

  public VerdictEngine(VersionPolicy versionPolicy) {
    this.versionPolicy = versionPolicy;
  }

  public RuntimeAssessment assess(String id, RuntimeInfo runtime, CpuInfo cpu, MitigationInfo mitigation, Reachability reachability) {
    List<Evidence> evidence = new ArrayList<>();
    List<String> limitations = new ArrayList<>();

    if (runtime == null) {
      limitations.add("Runtime information missing");
      return new RuntimeAssessment(id, Verdict.UNKNOWN, runtime, cpu, mitigation, reachability, evidence, limitations);
    }

    evidence.add(new Evidence("version-policy", "Checked against known fixed builds", EvidenceConfidence.PARTIAL));

    if (versionPolicy.isPatched(runtime)) {
      return new RuntimeAssessment(id, Verdict.PATCHED, runtime, cpu, mitigation, reachability, evidence, limitations);
    }

    boolean versionAffected = versionPolicy.isInAffectedRange(runtime);
    boolean cpuOk = cpu != null && cpu.isCompatible();
    boolean mitigated = mitigation != null && mitigation.isEffective();

    if (!cpu.flagsVisible()) {
      limitations.add("CPU flags not fully visible");
    }
    if (reachability == Reachability.UNKNOWN) {
      limitations.add("Attacker-controlled Base64 reachability not assessed");
    }

    if (!versionAffected) {
      return new RuntimeAssessment(id, Verdict.NOT_AFFECTED, runtime, cpu, mitigation, reachability, evidence, limitations);
    }

    if (!cpuOk && cpu != null && cpu.flagsVisible()) {
      return new RuntimeAssessment(id, Verdict.NOT_AFFECTED, runtime, cpu, mitigation, reachability, evidence, limitations);
    }

    if (mitigated) {
      return new RuntimeAssessment(id, Verdict.MITIGATED, runtime, cpu, mitigation, reachability, evidence, limitations);
    }

    if (versionAffected && cpuOk && !mitigated) {
      return new RuntimeAssessment(id, Verdict.VULNERABLE, runtime, cpu, mitigation, reachability, evidence, limitations);
    }

    return new RuntimeAssessment(id, Verdict.EXPOSURE_POSSIBLE, runtime, cpu, mitigation, reachability, evidence, limitations);
  }
}
