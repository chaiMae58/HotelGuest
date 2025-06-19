# HotelGuest# Hotel Management System

A simple Java Swing desktop application to manage hotel guest registration, deregistration, room occupancy, history, and reporting.  
This project demonstrates the use of Java GUI components, event-driven programming, and basic hotel management logic.

## Features

- **Guest Registration:** Add guests by entering their first and last names with validation.
- **Guest Deregistration:** Remove guests from rooms by specifying the room number.
- **View Occupancy:** Display the currently occupied rooms.
- **Room History & Status:** Show historical occupancy and status of rooms.
- **Occupancy Report:** Generate reports about room occupancy statistics.
- **Persistent Data:** Automatically saves and loads hotel data between sessions.
- **Factory Method Pattern:** Uses the Factory Method design pattern to create room objects, promoting flexible and extensible code architecture.

## Technologies Used

- Java SE (Swing for GUI)
- Object-Oriented Design and Design Patterns (Factory Method)
- File-based persistence for saving hotel state

## How to Run

1. **Clone the repository:**
    ```bash
    git clone https://github.com/yourusername/hotel-management-system.git
    ```

2. **Compile the Java files:**
    ```bash
    javac -d bin src/*.java
    ```

3. **Run the application:**
    ```bash
    java -cp bin HotelAppUI
    ```

## Usage

- Launch the app.
- Use the buttons on the main window to register or deregister guests.
- View occupancy or reports by clicking the corresponding buttons.
- Data is saved automatically when you close the application.

