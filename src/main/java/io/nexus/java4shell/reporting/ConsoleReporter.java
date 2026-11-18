package io.nexus.java4shell.reporting;

import io.nexus.java4shell.domain.RuntimeAssessment;

public final class ConsoleReporter {
  public String render(RuntimeAssessment a) {
    return """
        CVE-2026-114938 Assessment
        Runtime: %s %s (%s)
        Verdict: %s
        CPU compatible: %s
        Mitigation verified: %s
        Limitations: %s
        """.formatted(
        a.runtime() != null ? a.runtime().vendor() : "?",
        a.runtime() != null ? a.runtime().version() : "?",
        a.runtime() != null ? a.runtime().architecture() : "?",
        a.verdict(),
        a.cpu() != null && a.cpu().isCompatible(),
        a.mitigation() != null && a.mitigation().isEffective(),
        String.join("; ", a.limitations())
    );
  }
}
