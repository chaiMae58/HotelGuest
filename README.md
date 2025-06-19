# Hotel Management System

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

## Project Structure
The project is organized into the following packages:
- `src/HotelApp`: The main class.
- `src/Hotel` : Core class managing rooms, guests, registrations, and overall hotel data.
- `src/HotelAppUI`: Contains the Swing-based user interface 
- `src/Room`: Abstract class or interface representing a room extended by StandardRoom and BusinessRoom.
- `src/Guest`: Represents a guest with first name and last name.
- `src/Hotelpersistence` : classs handling loading and saving hotel data.
- `src/RoomFactory`: mplementation of the Factory Method pattern to create room objects.
- `HotelManagementSystem_SysML v2`:.zip file containing a sysml v2 project defining hotel system's structure and needs.
- `HotelSystemTestScenario.pdf`: provides a detailed walkthrough of functional tests performed on the hotel management system.

## Technologies Used
- Java 17 Semeru
- Maven 3.6.3
- IIntelliJ Community Edition
- Tested with IntelliJ Maven integration

## How to Run

1. **Clone the repository:**
    ```bash
    git clone https://github.com/yourusername/hotel-management-system.git
    ```

2. **Build with Maven or your preferred build tool:**

3. **Run the `HotelAppUI` main class:**


## Usage

- Launch the app.
- Use the buttons on the main window to register or deregister guests.
- View occupancy or reports by clicking the corresponding buttons.
- Data is saved automatically when you close the application.

## User interface: 

![image](https://github.com/user-attachments/assets/927e906b-9956-4ea7-9290-b7b311e83d93)

