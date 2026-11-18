package io.nexus.java4shell.domain;

public record Evidence(
    String source,
    String description,
    EvidenceConfidence confidence
) {}
