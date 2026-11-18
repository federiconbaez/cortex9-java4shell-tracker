# Java4Shell Tracker

Evidence-based defensive assessor for CVE-2026-114938.

## Verdicts

VULNERABLE · MITIGATED · PATCHED · NOT_AFFECTED · EXPOSURE_POSSIBLE · UNKNOWN

## Usage

```bash
# Compile and run (Java 17+ for local check; project targets 21)
javac -d out -source 17 -target 17 \
  src/main/java/io/nexus/java4shell/domain/*.java \
  src/main/java/io/nexus/java4shell/intelligence/*.java \
  src/main/java/io/nexus/java4shell/engine/*.java \
  src/main/java/io/nexus/java4shell/discovery/*.java \
  src/main/java/io/nexus/java4shell/reporting/*.java \
  src/main/java/io/nexus/java4shell/cli/*.java

java -cp out io.nexus.java4shell.cli.Main
```

## What is measured

- VerdictEngine unit tests cover the main decision table cases.
- CPU scanner reads /proc/cpuinfo when available.
- Version policy currently covers Oracle/OpenJDK fixed builds for lines 17/21/25/27.

## What is not measured

- Full vendor matrix
- Process-level flag inspection of other JVMs
- Application-level Base64 reachability

No exploit code.
