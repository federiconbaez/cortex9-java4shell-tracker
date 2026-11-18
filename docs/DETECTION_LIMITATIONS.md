# Detection Limitations

- CPU flags may be hidden by virtualization or containers → UNKNOWN / EXPOSURE_POSSIBLE
- Exact vendor build may not be identifiable
- Attacker-controlled input reachability to Base64 decoder cannot be determined from version alone
- A running JVM cannot reliably inspect flags of other JVMs without process-level access
- Policy matrix is currently Oracle/OpenJDK-centric; other distributions require explicit rules
