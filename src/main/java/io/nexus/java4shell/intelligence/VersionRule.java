package io.nexus.java4shell.intelligence;

public record VersionRule(
    String vendor,
    String family,
    String fixedVersion
) {}
