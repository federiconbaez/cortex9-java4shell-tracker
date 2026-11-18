# Verdict Model

Six states:

- VULNERABLE: affected runtime + compatible CPU + no verified mitigation
- MITIGATED: mitigation verified effective
- PATCHED: version matches or exceeds fixed build
- NOT_AFFECTED: platform or version outside documented scope
- EXPOSURE_POSSIBLE: risk conditions present but evidence incomplete
- UNKNOWN: insufficient evidence

Never degrade UNKNOWN to NOT_AFFECTED.
