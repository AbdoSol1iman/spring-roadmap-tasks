# Changes Overview

This document summarizes the updates made to ensure each class focuses on its responsibilities and core workflows behave correctly.

## Guest
- Encapsulated fields to avoid direct mutation.
- Prevented negative loyalty points in setters and discount calculation.

## Room
- Encapsulated fields to keep room state controlled by the class.
- Made `findRoomByType` return the first matching available room and guard null inputs.

## Hotel
- Fixed date-range availability checks to avoid overlapping reservations being marked available.
- Filtered availability to room status and active reservations only.
- Ensured reservations are stored when created and that room status becomes `Reserved`.
- Added null checks for safe reservation operations (create/cancel/check-in/check-out).
- Corrected availability-by-type to filter from actual available rooms.
- Calculated occupancy based on real room statuses instead of cached counts.
- Calculated revenue from reservations that overlap the requested period.
- Improved hotel status output with computed counts and active reservation list.

## Reservation
- Corrected total calculation to apply discount as a percentage.
- Ensured `checkOut()` returns the final total.
- Added a null guard when adding services.
- Ensured status changes are consistent for check-in/check-out/cancel.
- Encapsulated reservation state and exposed getters for hotel/main usage.

## Notes
- These changes align responsibilities: `Hotel` manages inventory and reservation lifecycle, `Reservation` computes totals and status changes, `Room` tracks its own state, and `Guest` provides loyalty-based discounts.
