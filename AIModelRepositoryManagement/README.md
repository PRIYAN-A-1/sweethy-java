# AI Model Repository Management System

## Problem Statement
The rapidly growing number of AI and ML models requires an organized repository system to manage models, versions, metadata, and performance metrics. Without a centralized system, it becomes difficult for organizations to track their AI assets and model evolution.

## Objectives
- Build a centralized repository for AI Models.
- Manage user access (Admin and Developer).
- Track multiple versions of an AI Model.
- Generate reports based on model frameworks and types.

## Features
- **User Roles:** Admin, Developer
- **Authentication:** Simple Login/Registration
- **Model Operations:** Add, View, Update, Delete, Search
- **Version Management:** Track model versions and updates
- **Reports:** Summarize active models and frameworks
- **File I/O:** Persist data in text files

## OOP Concepts Used
1. **Classes and Objects:** `User`, `AIModel`, etc.
2. **Encapsulation:** Private variables accessed via getter/setters.
3. **Inheritance:** `Admin` and `Developer` extending `User`.
4. **Polymorphism:** Method overriding (`displayDetails`).
5. **Abstraction:** Abstract class `User`.
6. **Interfaces:** `RepositoryOperations`.
7. **Exception Handling:** Custom exceptions (`ModelNotFoundException`, etc.).

## Technology Stack
- Java 17
- Core Java Collections (List, Map)
- File I/O (`BufferedReader`, `BufferedWriter`)

## How to Run
Run the application using the provided `run.bat` script:
```bash
run.bat
```

Sample Credentials:
- Admin: `admin` / `admin123`
- Developer: `devuser` / `dev123`
