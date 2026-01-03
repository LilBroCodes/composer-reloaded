### Fixes
 - Velora particles now actually render above 1.20.6
 - Composer's implementations of vanilla registries are now actually registered instead of existing in their own little universe
 - Colors in commands now distinguish between ARGB and RGB formats

---
### General Improvements
 - Toasts are now synchronized based on a centralized serializer system instead of having to implement custom packets for every toast type manually
 - Velora particles now offer the ability to specify the interpolation for all previously linear-interpolated properties, with the default being linear

---
### New features
 - Overlays! Similar to popups, but more configurable, optionally non-blocking, and fancier
 - Overlays also operate around a centralized serializer system identical to the one of toasts
 - Some miscellaneous mathematics, rendering, randomization and registration utilities
 - Utility command for dumping composer's custom & vanilla's builtin registries