package io.nexus.java4shell.cli;

import io.nexus.java4shell.discovery.CpuScanner;
import io.nexus.java4shell.domain.*;
import io.nexus.java4shell.engine.VerdictEngine;
import io.nexus.java4shell.intelligence.VersionPolicy;
import io.nexus.java4shell.reporting.ConsoleReporter;

public final class Main {
  public static void main(String[] args) {
    var cpu = new CpuScanner().scan();
    var runtime = new RuntimeInfo("local", System.getProperty("java.version", "unknown"),
        System.getProperty("os.arch", "unknown"), null);
    var mitigation = new MitigationInfo(false, false, EvidenceConfidence.UNVERIFIED);
    var engine = new VerdictEngine(new VersionPolicy());
    var assessment = engine.assess("current", runtime, cpu, mitigation, Reachability.UNKNOWN);
    System.out.println(new ConsoleReporter().render(assessment));
  }
}
