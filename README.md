# Movies App

![Movie App - Jetpack Compose](https://github.com/ahuamana/MoviesAppJetpackCompose/assets/60039961/a491680c-5bae-4464-b8ec-c781e6e5b60d)


The Movies App is a sample Android application that allows users to browse and discover movies. It follows the principles of Clean Architecture, utilizes the MVVM (Model-View-ViewModel) architectural pattern, and is built using Jetpack Compose. The app also integrates Room for local database storage and utilizes Coroutines and Flow for asynchronous programming.

## Features

- Browse and search movies
- View movie details, including title, overview, release date, and ratings
- Add movies to favorites
- View favorite movies

## Technologies and Libraries Used

- Android Jetpack Compose: Modern UI toolkit for building native Android UI.
- Kotlin Coroutines: For asynchronous and concurrent programming.
- Room: Android's SQLite database library for local data storage.
- ViewModel: Part of the Android Architecture Components, used to manage UI-related data in a lifecycle-aware manner.
- Retrofit: HTTP client library for making network requests.
- Gson: JSON serialization/deserialization library for parsing API responses.
- Dagger Hilt: Dependency injection framework for Android.
- Coil: Image loading library for displaying movie posters and images.
- Kotlin DSL: Gradle build scripts written in Kotlin.

## Architecture

The app follows the principles of Clean Architecture, which promotes separation of concerns and modular development. The architecture consists of the following layers:

- **Presentation**: Contains the Jetpack Compose UI components, ViewModels, and UI-related logic.
- **Domain**: Contains the business logic and defines the use cases of the application.
- **Framework**: Implements the concrete implementations of the data sources, such as network and local database.
- **Data**: Handles data operations, including fetching data from the network and accessing the local database using Room.
- **Dependency Injection**: Uses Dagger Hilt for dependency injection, enabling modular and testable code.

## Setup and Configuration

To run the Movies App on your local machine, follow these steps:

1. Clone the repository: `git clone https://github.com/your-username/movies-app.git`
2. Open the project in Android Studio.
3. Build and run the app on an emulator or physical device.

## Development Workflow

The development workflow for the Movies App follows the standard MVVM pattern and Clean Architecture principles. Here's an overview of the workflow:

1. UI components, screens, and layouts are implemented using Jetpack Compose.
2. ViewModel classes are created to hold and manage UI-related data and business logic.
3. Use cases are implemented in the domain layer to handle the app's specific business requirements.
4. Data is fetched from the network using Retrofit and converted to domain models using Moshi.
5. Data is stored locally in a Room database for offline availability.
6. Dependency injection is used to provide necessary dependencies throughout the app.
7. Unit tests and UI tests are written to ensure the correctness and reliability of the app.

## Testing

The Movies App includes both unit tests and UI tests to verify the correctness of its components and functionality. Testing is an essential part of the development process to ensure stability and reliability.

To run the unit tests, use the following command:
