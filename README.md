# PhraseNote

![Phrasenote](- bannerhead -)

PhraseNote is an Android application that provides a resources to store BookMark Notes. You can add your notes about your best books and then search the notes that you have already saved. 

## Features

The PhraseNote app has the following features:

- Display a list of your Books
- Display your own Bookmark notes
- Add, edit, and delete articles your notes. 

## Architecture

The PhraseNote app is built using the MVVM (Model-View-ViewModel) architecture pattern, which separates the app's UI, business logic, and data layers. The app uses the following Android Architecture components:

- ViewModel: manages UI-related data and provides data to the UI upon request.
- LiveData: notifies observers (UI components) when the underlying data changes.
- Room: provides an abstraction layer over SQLite database to store and retrieve data efficiently.
- Data Binding: binds UI components in the layout XML to app data sources including on the Activity or Fragments. 

## Dependencies

The PhraseNote app uses the following third-party libraries:

- Android KTX
- Coroutines
- ViewModel and LiveData
- Room
- Navigation Components
- Data Binding