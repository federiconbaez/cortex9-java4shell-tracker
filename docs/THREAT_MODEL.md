# Threat Model

Defensive tool only.

- Reads system information (CPU flags, java -version style data)
- Does not modify JVM flags
- Does not execute payloads
- Does not read process memory
- Does not require network for core diagnosis
- Does not collect credentials or full environment
