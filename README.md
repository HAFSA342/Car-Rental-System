# Car Rental System

A Java-based GUI application for managing car rentals with features for customers to rent and return cars.

## Features

- View available cars with details (brand, model, price)
- Rent a car with customer information
- Return rented cars
- View rental history
- User-friendly graphical interface

## Classes

- **Car**: Represents a car with properties like ID, brand, model, and price
- **Customer**: Represents a customer with ID and name
- **Rental**: Represents a rental transaction linking a car, customer, and rental period
- **CarRentalSystem**: Main system class that manages cars, customers, and rental operations

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or higher
- Java Swing library (included in JDK)

### Running the Application

1. Compile the Java files:
   ```
   javac car.java
   ```

2. Run the application:
   ```
   java CarRentalSystem
   ```

## Usage

1. The main menu provides options to:
   - View all cars
   - Rent a car
   - Return a car
   - View rental history
   - Exit the application

2. To rent a car:
   - Enter your name
   - Select a car ID from the available cars
   - Enter the number of rental days
   - Confirm the rental details

3. To return a car:
   - Enter the car ID to return

## Demo Cars

The system comes pre-loaded with these cars:
- Toyota Camry ($50/day)
- Honda Civic ($45/day)
- Ford Mustang ($80/day)
- Chevrolet Malibu ($55/day)