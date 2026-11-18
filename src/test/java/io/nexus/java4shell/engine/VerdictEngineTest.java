package io.nexus.java4shell.engine;

import io.nexus.java4shell.domain.*;
import io.nexus.java4shell.intelligence.VersionPolicy;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class VerdictEngineTest {
  private final VerdictEngine engine = new VerdictEngine(new VersionPolicy());

  @Test
  void vulnerable_when_affected_cpu_no_mitigation() {
    var r = new RuntimeInfo("Oracle", "21.0.13", "amd64", null);
    var c = new CpuInfo(true, true, true, true, true);
    var m = new MitigationInfo(false, false, EvidenceConfidence.VERIFIED);
    var result = engine.assess("r1", r, c, m, Reachability.UNKNOWN);
    assertEquals(Verdict.VULNERABLE, result.verdict());
  }

  @Test
  void patched_when_fixed_build() {
    var r = new RuntimeInfo("Oracle", "21.0.13.1", "amd64", null);
    var c = new CpuInfo(true, true, true, true, true);
    var result = engine.assess("r2", r, c, null, Reachability.UNKNOWN);
    assertEquals(Verdict.PATCHED, result.verdict());
  }

  @Test
  void not_affected_on_arm() {
    var r = new RuntimeInfo("Oracle", "21.0.13", "aarch64", null);
    var c = new CpuInfo(false, false, false, false, true);
    var result = engine.assess("r3", r, c, null, Reachability.UNKNOWN);
    assertEquals(Verdict.NOT_AFFECTED, result.verdict());
  }

  @Test
  void mitigated_when_flag_verified() {
    var r = new RuntimeInfo("Oracle", "21.0.13", "amd64", null);
    var c = new CpuInfo(true, true, true, true, true);
    var m = new MitigationInfo(true, false, EvidenceConfidence.VERIFIED);
    var result = engine.assess("r4", r, c, m, Reachability.UNKNOWN);
    assertEquals(Verdict.MITIGATED, result.verdict());
  }

  @Test
  void unknown_when_flags_hidden() {
    var r = new RuntimeInfo("Oracle", "21.0.13", "amd64", null);
    var c = new CpuInfo(false, false, false, false, false);
    var result = engine.assess("r5", r, c, null, Reachability.UNKNOWN);
    assertEquals(Verdict.EXPOSURE_POSSIBLE, result.verdict());
  }
}
