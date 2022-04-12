### Project screenshots:
![Screenshot_1649777419](https://user-images.githubusercontent.com/5457179/163000081-65723a43-adb3-4d40-b550-60b861eaa5f5.png)
![Screenshot_1649777414](https://user-images.githubusercontent.com/5457179/163000104-b93b05ad-c297-4bee-bd7c-914e9f199645.png)
![Screenshot_1649777396](https://user-images.githubusercontent.com/5457179/163000108-1c66c927-b4ca-4c77-a043-459ed9ed1bb2.png)

# StackExchange
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
Download or clone the project in Android Studio build and run, if there is any conflict or issue in build system, try to sync project with gradle files by click on the menu -> file -> sync project with gradle files.
