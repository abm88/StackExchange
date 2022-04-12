# StackExchange

# StackExchange Users:

App consists of main screen that shows a list of StackExchange users with a search section. Clicking on search icon the search box becomes editable and clicking on search result item opens the detail screen that shows some details of the user.

See below the instructions for each one of these:

### Technical Specs:
- Kotlin
- MVVM
- Clean Arch
- Unit Test
- SOLID

Code base consists of 3 layers based on clean architecture, first layer is presentation (UI Framework) including UI, view models. Domain includes interactors/use cases, repositories (interface adapters) and the third layer is entities defining busiess logic.
---
![alt text](https://blog.cleancoder.com/uncle-bob/images/2012-08-13-the-clean-architecture/CleanArchitecture.jpg)


### Tools & Libraries:
- Coroutine/Flow
- Dagger
- Retrofit
- Mockito
- Jetpack Compose

### How to build:
Download or clone the project in Android Studio build and run, if there is any conflict or issue in build system, try to sync project with gradle files by click on the menu -> file -> sync project with gradle files
