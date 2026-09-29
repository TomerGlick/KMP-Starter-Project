# KMP Weather — Kotlin Multiplatform workshop

A small weather app built with Kotlin Multiplatform and Compose Multiplatform. The UI, networking and
state management are all written once in `commonMain` and run on Android, Desktop and iOS.

## Presentation & Workshop Resources

The workshop presentation slides and setup guide are included in the repository:
📄 [`Kotlin Multiplatform What It Is and How It Works.pdf`](Kotlin%20Multiplatform%20What%20It%20Is%20and%20How%20It%20Works.pdf)
📋 [`KMP Workshop Setup Before the Session.pdf`](KMP%20Workshop%20Setup%20Before%20the%20Session.pdf)

## Before the session (please do this the day before)

Please review the setup guide 📋 [`KMP Workshop Setup Before the Session.pdf`](KMP%20Workshop%20Setup%20Before%20the%20Session.pdf).
The first Gradle sync downloads a lot, so do it ahead of time:

1. Install **JDK 17 or newer** and the latest **Android Studio** (or IntelliJ IDEA).
2. Install the **Kotlin Multiplatform** plugin (Settings → Plugins → Marketplace).
3. Clone this repo, open it, and let Gradle sync finish.
4. Run the app once on Android and once on Desktop (commands below). You should see "Hello, KMP!".

A Mac with Xcode is only needed for iOS, which is optional for this workshop.

## Running the app

| Platform | How |
|---|---|
| Desktop | `./gradlew :composeApp:run` (Windows: `gradlew.bat :composeApp:run`) |
| Android | Select the `composeApp` run configuration in Android Studio, or `./gradlew :composeApp:installDebug` |
| Tests   | `./gradlew :composeApp:desktopTest` |
| iOS     | See "iOS" below |

## Project layout

```
composeApp/src/
├── commonMain/   ← shared code: UI, ViewModel, networking, models (almost everything)
├── commonTest/   ← shared tests, run on every platform
├── androidMain/  ← Android entry point (MainActivity) + Android `actual`s
├── desktopMain/  ← Desktop entry point (main.kt) + JVM `actual`s
└── iosMain/      ← iOS entry point (MainViewController) + iOS `actual`s
```

## Checkpoint branches

If you fall behind, check out the next branch and carry on from there.

| Branch | What it adds |
|---|---|
| `step-0-starter` | The empty app, running on every platform, with all dependencies ready |
| `step-1-expect-actual` | A `platformName()` function with a different implementation per platform |
| `step-2-networking` | Open-Meteo API model, Ktor repository, shared ViewModel (one hardcoded city) |
| `final-solution` | The finished final project (see [LAB.md](LAB.md)) |

To see exactly what changed in a step: `git diff step-1-expect-actual step-2-networking`

## iOS

The iOS targets and `MainViewController()` are already set up, but the Xcode project isn't included.
To run on iOS, generate a project at https://kmp.jetbrains.com with the same settings (module name
`composeApp`, iOS with shared UI), copy its `iosApp/` folder into this repo's root, open
`iosApp/iosApp.xcodeproj` in Xcode and run.

## Versions

Library and plugin versions are pinned in `gradle/libs.versions.toml`. They're a set known to work together.
Newer versions from the KMP wizard are fine too, but upgrade Kotlin, Compose Multiplatform and the lifecycle
library together.
