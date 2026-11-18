package io.nexus.java4shell.intelligence;

import io.nexus.java4shell.domain.RuntimeInfo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class VersionPolicyTest {
  private final VersionPolicy policy = new VersionPolicy();

  @Test
  void detects_fixed_builds() {
    assertTrue(policy.isPatched(new RuntimeInfo("Oracle", "17.0.21.1", "amd64", null)));
    assertTrue(policy.isPatched(new RuntimeInfo("Oracle", "21.0.13.1", "amd64", null)));
    assertFalse(policy.isPatched(new RuntimeInfo("Oracle", "21.0.13", "amd64", null)));
  }

  @Test
  void affected_range() {
    assertTrue(policy.isInAffectedRange(new RuntimeInfo("Oracle", "21.0.5", "amd64", null)));
    assertFalse(policy.isInAffectedRange(new RuntimeInfo("Oracle", "11.0.23", "amd64", null)));
  }
}
