package io.nexus.java4shell.domain;

public record RuntimeInfo(
    String vendor,
    String version,
    String architecture,
    String build
) {}
