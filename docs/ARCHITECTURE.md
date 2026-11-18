# Architecture

Hexagonal-style separation:

- `domain`: pure value objects and verdicts
- `intelligence`: version and vendor policies (data-driven)
- `engine`: VerdictEngine (pure, deterministic)
- `discovery`: scanners (CPU, runtime, process)
- `reporting`: Console / JSON / Markdown
- `cli`: entry point

The engine never reads the filesystem or executes commands.
