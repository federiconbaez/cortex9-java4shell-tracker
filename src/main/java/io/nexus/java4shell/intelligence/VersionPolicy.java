package io.nexus.java4shell.intelligence;

import io.nexus.java4shell.domain.RuntimeInfo;

public final class VersionPolicy {
  public boolean isPatched(RuntimeInfo runtime) {
    if (runtime == null || runtime.version() == null) return false;
    // Conservative Oracle/OpenJDK rules for the four main lines
    String v = runtime.version();
    if (v.startsWith("17.") && compare(v, "17.0.21.1") >= 0) return true;
    if (v.startsWith("21.") && compare(v, "21.0.13.1") >= 0) return true;
    if (v.startsWith("25.") && compare(v, "25.0.5.1") >= 0) return true;
    if (v.startsWith("27.") && compare(v, "27.0.1.1") >= 0) return true;
    return false;
  }

  public boolean isInAffectedRange(RuntimeInfo runtime) {
    if (runtime == null || runtime.version() == null) return false;
    String v = runtime.version();
    return v.startsWith("17.") || v.startsWith("21.") || v.startsWith("25.") || v.startsWith("27.");
  }

  // Very simple numeric comparison for dotted versions; not a full semver
  private static int compare(String a, String b) {
    String[] pa = a.split("\\.");
    String[] pb = b.split("\\.");
    int len = Math.max(pa.length, pb.length);
    for (int i = 0; i < len; i++) {
      int va = i < pa.length ? parse(pa[i]) : 0;
      int vb = i < pb.length ? parse(pb[i]) : 0;
      if (va != vb) return Integer.compare(va, vb);
    }
    return 0;
  }

  private static int parse(String s) {
    try { return Integer.parseInt(s.replaceAll("[^0-9].*", "")); }
    catch (NumberFormatException e) { return 0; }
  }
}
