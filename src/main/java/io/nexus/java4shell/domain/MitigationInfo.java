package io.nexus.java4shell.domain;

public record MitigationInfo(
    boolean base64IntrinsicsDisabled,
    boolean avxRestricted,
    EvidenceConfidence verification
) {
  public boolean isEffective() {
    return verification == EvidenceConfidence.VERIFIED && (base64IntrinsicsDisabled || avxRestricted);
  }
}
