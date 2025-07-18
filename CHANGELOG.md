# CHANGELOG

## 0.7.1

- Add `kotlinx.datetime.Instant` and `kotlinx.datetime.Clock` type aliases to, respectively, `kotlin.time.Instant` and `kotlin.time.Clock` to make migration more convenient.
- Add a fallback timezone database implementation for Darwin that uses the Foundation framework if the timezone database is unavailable on the filesystem ([#485](https://github.com/Kotlin/kotlinx-datetime/issues/485)).
- Restore binary compatibility in string format definitions utilizing dates on the JVM ([#545](https://github.com/Kotlin/kotlinx-datetime/issues/545)).

## 0.7.0

Breaking changes:

- Remove `kotlinx.datetime.Instant` and `kotlinx.datetime.Clock` in favor of `kotlin.time.Instant` ([#506](https://github.com/Kotlin/kotlinx-datetime/pull/506)). See <https://github.com/Kotlin/kotlinx-datetime?tab=readme-ov-file#deprecation-of-instant> for a description of what to do if this causes problems.
- Unify the range of admissible `LocalDate` and `LocalDateTime` values on all platforms ([#432](https://github.com/Kotlin/kotlinx-datetime/issues/432)).
- Rename `dayOfMonth` to `day`, `monthNumber` to `month` ([#84](https://github.com/Kotlin/kotlinx-datetime/issues/84)).
- Make `DayOfWeek` and `Month` no longer type aliases to `java.time.DayOfWeek` and `java.time.Month` ([#96](https://github.com/Kotlin/kotlinx-datetime/issues/96)).
- Make `"UTC"` and not `"Z"` be the timezone identifier for `TimeZone.UTC` ([#474](https://github.com/Kotlin/kotlinx-datetime/issues/474)).
- Accept `"z"` in `TimeZone.of`, with the same meaning as `"Z"` ([#529](https://github.com/Kotlin/kotlinx-datetime/issues/529)).
- Add `DateTimeFormat`-based `KSerializer` implementations for datetime entities, and change the default serializers to be based on `toString`/`parse` pairs ([#415](https://github.com/Kotlin/kotlinx-datetime/issues/415)).

Additions:

- Add `java.io.Serializable` implementations for most of the entities in the library ([#143](https://github.com/Kotlin/kotlinx-datetime/issues/143)). Thanks for spotting and fixing a potential vulnerability, @lukellmann!
- Add `LocalDateRange`, a range of `LocalDate` values ([#190](https://github.com/Kotlin/kotlinx-datetime/issues/190)). Thanks, @PeterAttardo!
- Add `YearMonth` and `YearMonthRange` ([#457](https://github.com/Kotlin/kotlinx-datetime/issues/168)).

Tweaks and fixes:

- Support obtaining the system timezone on old Debian-based distributions ([#430](https://github.com/Kotlin/kotlinx-datetime/issues/430)).
- Fix not being able to parse some valid timezone identifiers using the `timeZoneId()` directive ([#444](https://github.com/Kotlin/kotlinx-datetime/issues/444)).
- Use the grammar defined in RFC 9557 for parsing timezone identifiers in the `timeZoneId()` directive, allowing parsing values not present in the timezone database ([#531](https://github.com/Kotlin/kotlinx-datetime/issues/531)).
- Improved error messages when formatting an invalid `DateTimeComponents` value ([#471](https://github.com/Kotlin/kotlinx-datetime/issues/471)).
- Set the JPMS dependency on `kotlinx.serialization.core` to be optional ([#496](https://github.com/Kotlin/kotlinx-datetime/issues/496)).

## 0.6.2

- Add the Wasm/WASI target support ([#366](https://github.com/Kotlin/kotlinx-datetime/pull/366)). Thanks, @igoriakovlev!
- Add `TimeSource.asClock` ([#164](https://github.com/Kotlin/kotlinx-datetime/pull/164)). Thanks, @hfhbd!
- Implement parsing and formatting day-of-year ([#414](https://github.com/Kotlin/kotlinx-datetime/issues/414))
- Improve the precision of `NSDate` to `Instant` conversions ([#427](https://github.com/Kotlin/kotlinx-datetime/issues/427))
- Deprecate `DatePeriod.plus` and `DateTimePeriod.plus` with a warning ([#381](https://github.com/Kotlin/kotlinx-datetime/issues/381))
- Deprecate `Clock.asTimeSource` with a warning ([#372](https://github.com/Kotlin/kotlinx-datetime/issues/372))
- Fix a bug in `module-info` preventing using `kotlinx.datetime.format` together with JPMS ([#438](https://github.com/Kotlin/kotlinx-datetime/pull/438)). Thanks, @cdelabou!
- Small tweaks and fixes.

## 0.6.1

- Prohibit parsing non-ASCII digits as numbers in `DateTimeFormat` ([#405](https://github.com/Kotlin/kotlinx-datetime/issues/405))
- More accurately determine which files represent time zones and which don't on Linux and Darwin ([#395](https://github.com/Kotlin/kotlinx-datetime/pull/395))
- Fix a regression that occasionally considered the right boundary of a time overlap to be a part of it on Native ([#399](https://github.com/Kotlin/kotlinx-datetime/issues/399))
- Introduce various small improvements to the timezone handling on Windows ([#390](https://github.com/Kotlin/kotlinx-datetime/pull/390))
- On Linux, allow not having any `/etc/localtime` set, defaulting to `TimeZone.UTC` as the system time zone ([#426](https://github.com/Kotlin/kotlinx-datetime/pull/426))

## 0.6.0

- Introduce the widely requested API for locale-invariant parsing and formatting ([#343](https://github.com/Kotlin/kotlinx-datetime/pull/343))
- Completely overhaul the KDoc-based documentation ([#347](https://github.com/Kotlin/kotlinx-datetime/issues/347))
- Breaking change: forbid parsing `Instant` values without the second-of-minute component on the JVM and JS, fixing inconsistency with Native ([#369](https://github.com/Kotlin/kotlinx-datetime/issues/369))
- Breaking change: use the fully qualified name of classes in the JSON serializers ([#308](https://github.com/Kotlin/kotlinx-datetime/pull/308))
- Fix Proguard emitting warning about missing `kotlinx-serialization` classes when serialization is not used ([#336](https://github.com/Kotlin/kotlinx-datetime/pull/336))
- Reimplement the timezone database handling for Native targets from scratch ([#286](https://github.com/Kotlin/kotlinx-datetime/pull/286), [#327](https://github.com/Kotlin/kotlinx-datetime/pull/327))
- Support Android NDK targets ([#344](https://github.com/Kotlin/kotlinx-datetime/pull/344))
- Small tweaks and fixes.

### Changelog relative to version 0.6.0-RC.2

- Completely overhaul the KDoc-based documentation ([#347](https://github.com/Kotlin/kotlinx-datetime/issues/347))
- Breaking change: forbid parsing `Instant` values without the second-of-minute component on the JVM and JS, fixing inconsistency with Native ([#369](https://github.com/Kotlin/kotlinx-datetime/issues/369))
- Improve error descriptiveness in some cases ([#360](https://github.com/Kotlin/kotlinx-datetime/pull/360), [#371](https://github.com/Kotlin/kotlinx-datetime/pull/371))
- Remove `stat` usages to comply with Apple's new publishing requirements ([#385](https://github.com/Kotlin/kotlinx-datetime/pull/385))
- Fix parsing of formats where `optional` is directly between numbers ([#362](https://github.com/Kotlin/kotlinx-datetime/pull/362))
- Forbid empty and duplicate month, day-of-week, and AM/PM marker names in datetime formats ([#362](https://github.com/Kotlin/kotlinx-datetime/pull/362))

## 0.6.0-RC.2

- Support Android NDK targets ([#344](https://github.com/Kotlin/kotlinx-datetime/pull/344))
- Ensure ABI compatibility with v0.5.0 ([#357](https://github.com/Kotlin/kotlinx-datetime/pull/357))

## 0.6.0-RC

- Introduce the widely requested API for locale-invariant parsing and formatting ([#343](https://github.com/Kotlin/kotlinx-datetime/pull/343))
- Breaking change: use the fully qualified name of classes in the JSON serializers ([#308](https://github.com/Kotlin/kotlinx-datetime/pull/308))
- Fix Proguard emitting warning about missing `kotlinx-serialization` classes when serialization is not used ([#336](https://github.com/Kotlin/kotlinx-datetime/pull/336))
- Reimplement the timezone database handling for Native targets from scratch ([#286](https://github.com/Kotlin/kotlinx-datetime/pull/286), [#327](https://github.com/Kotlin/kotlinx-datetime/pull/327))

## 0.5.0

- Update Kotlin dependency to 1.9.21, kotlinx.serialization to 1.6.2
- Add support of Wasm-Js target through Js interop with the same js-joda library as in Js ([#315](https://github.com/Kotlin/kotlinx-datetime/pull/315))
- Prevent secondary outputs of Java 9 compilation getting packed into jar ([#305](https://github.com/Kotlin/kotlinx-datetime/pull/305))

## 0.4.1

- Update Kotlin dependency to 1.8.21, kotlinx.serialization to 1.5.1
- Support more Kotlin/Native targets: `linuxArm64`, `linuxArm32Hfp`, `watchosDeviceArm64`
- Implement [comparable time marks](https://kotlinlang.org/docs/time-measurement.html#measure-differences-in-time) in a time source returned by `Clock.asTimeSource()` ([#271](https://github.com/Kotlin/kotlinx-datetime/pull/271))
- Deprecate `Instant` and `LocalDate` arithmetic operations (`plus` and `minus`) taking `DateTimeUnit` without a number of units ([#247](https://github.com/Kotlin/kotlinx-datetime/pull/247))
- Fix adding small `Duration` to large `Instant` on JS and Native ([#264](https://github.com/Kotlin/kotlinx-datetime/pull/264))

## 0.4.0

- Add the `LocalTime` class for representing time-of-day ([#57](https://github.com/Kotlin/kotlinx-datetime/pull/57)). Thank you, @bishiboosh!
- Provide `LocalTime#toSecondOfDay`, `LocalTime.fromSecondOfDay`, and various other functions for compact representation of `LocalTime` ([#204](https://github.com/Kotlin/kotlinx-datetime/pull/204)). Thank you, @vanniktech!
- Provide `LocalDate#toEpochDays`, `LocalDate.fromEpochDays` for representing a `LocalDate` as a single number ([#214](https://github.com/Kotlin/kotlinx-datetime/pull/214)).
- Rename `Clock.todayAt` to `Clock.todayIn` for naming consistency ([#206](https://github.com/Kotlin/kotlinx-datetime/pull/206)).
- Update the Kotlin dependency to 1.7.0.

## 0.3.3

- Just updated Kotlin dependency to 1.7.0-Beta and kotlinx.serialization to 1.3.2

## 0.3.2

#### Features

- Update Kotlin dependency to 1.6.0 and remove `ExperimentalTime` from API involving `Duration` which became stable ([#156](https://github.com/Kotlin/kotlinx-datetime/issues/156))
- Add an explicit `module-info` descriptor to JVM variant of the library ([#135](https://github.com/Kotlin/kotlinx-datetime/pull/135))
- `kotlinx.datetime.Instant` conversions to and from JS `Date` ([#170](https://github.com/Kotlin/kotlinx-datetime/issues/170)).


## 0.3.1

#### Fixes

- Fixed a crash in desugared code on Android when trying to construct time zones with some specific identifiers ([149](https://github.com/Kotlin/kotlinx-datetime/issues/149))

## 0.3.0

#### Features

- Added `iosSimulatorArm64`, `watchosSimulatorArm64`, `tvosSimulatorArm64`, `macosArm64` target support ([141](https://github.com/Kotlin/kotlinx-datetime/issues/141), [144](https://github.com/Kotlin/kotlinx-datetime/issues/144)).

#### Changes

- `ZoneOffset` was replaced by two other classes: `FixedOffsetTimeZone`, which represents a time zone with a fixed offset, and `UtcOffset`, which represents just the UTC offset ([PR#125](https://github.com/Kotlin/kotlinx-datetime/pull/125)).
- The `DayBased` and `MonthBased` subclasses of `DateTimeUnit.DateBased` are now accessed as `DateTimeUnit.DayBased` and `DateTimeUnit.MonthBased` as opposed to `DateTimeUnit.DateBased.DayBased` and `DateTimeUnit.DateBased.MonthBased` respectively ([PR#131](https://github.com/Kotlin/kotlinx-datetime/pull/131)).

## 0.2.1

#### Fixes

- Fixed the library being incompatible with kotlinx.serialization 1.2.0 and above ([#118](https://github.com/Kotlin/kotlinx-datetime/issues/118)).

#### Features

- `watchosX64` target support. In practice, this means the ability to run projects that depend on this library in the iOS Simulator for Apple Watch.

## 0.2.0

#### Fixes

- Fixed `TimeZone.currentSystemDefault()` crashing on Darwin if the resulting time zone is not listed among `TimeZone.knownTimeZoneIdentifiers` ([#94](https://github.com/Kotlin/kotlinx-datetime/issues/94))

#### Features

- `kotlinx-serialization` support ([#37](https://github.com/Kotlin/kotlinx-datetime/issues/37))
- Normalization of `DateTimePeriod` components, meaning that periods that are semantically equivalent are considered equal ([#81](https://github.com/Kotlin/kotlinx-datetime/issues/81))
- `Instant` can now be parsed from an ISO-8601 string with an offset other than `Z` ([#56](https://github.com/Kotlin/kotlinx-datetime/issues/56))

## 0.1.1

#### Fixes
 
- Fix a crash when getting the current time on iOS 9 ([#52](https://github.com/Kotlin/kotlinx-datetime/issues/52))
- Wrong answers in some cases when adding date-based units to instants on Darwin and Windows ([#51](https://github.com/Kotlin/kotlinx-datetime/issues/51)) 

#### Features

- Zone-agnostic time-based arithmetic on Instants, e.g. `Instant.plus(value, DateTimeUnit.TimeBased)`
- Add `Instant.fromEpochSeconds(epochSeconds: Long, nanosecondAdjustment: Int)` construction function
- Introduce `minus` operations complementary to existing `plus` arithmetic operations ([#42](https://github.com/Kotlin/kotlinx-datetime/issues/42))

## 0.1.0

#### Initial implementation 

A minimal, but still valuable multiplatform implementation of date and time types.
