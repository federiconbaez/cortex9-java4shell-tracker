package io.nexus.java4shell.mitigation;

import io.nexus.java4shell.domain.EvidenceConfidence;
import io.nexus.java4shell.domain.MitigationInfo;

public final class MitigationEvaluator {
  public MitigationInfo evaluate(boolean intrinsicsDisabled, boolean avxRestricted, EvidenceConfidence confidence) {
    return new MitigationInfo(intrinsicsDisabled, avxRestricted, confidence);
  }
}
