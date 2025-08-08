import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

class Car {
    private String carId;
    private String brand;
    private String model;
    private double basePricePerDay;
    private boolean isAvailable;

    public Car(String carId, String brand, String model, double basePricePerDay) {
        this.carId = carId;
        this.brand = brand;
        this.model = model;
        this.basePricePerDay = basePricePerDay;
        this.isAvailable = true;
    }

    public String getCarId() {
        return carId;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public double calculatePrice(int rentalDays) {
        return basePricePerDay * rentalDays;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void rent() {
        isAvailable = false;
    }

    public void returnCar() {
        isAvailable = true;
    }
}

class Customer {
    private String customerId;
    private String name;

    public Customer(String customerId, String name) {
        this.customerId = customerId;
        this.name = name;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }
}

class Rental {
    private Car car;
    private Customer customer;
    private int days;

    public Rental(Car car, Customer customer, int days) {
        this.car = car;
        this.customer = customer;
        this.days = days;
    }

    public Car getCar() {
        return car;
    }

    public Customer getCustomer() {
        return customer;
    }

    public int getDays() {
        return days;
    }
}

class CarRentalSystem {
    private List<Car> cars;
    private List<Customer> customers;
    private List<Rental> rentals;
    public CarRentalSystem() {
        cars = new ArrayList<>();
        customers = new ArrayList<>();
        rentals = new ArrayList<>();
    }
    public void addCar(Car car) {
        cars.add(car);
    }
    public void addCustomer(Customer customer) {
        customers.add(customer);
    }
    public void rentCar(Car car, Customer customer, int days) {
        if (car.isAvailable()) {
            car.rent();
            rentals.add(new Rental(car, customer, days));
        } else {
            JOptionPane.showMessageDialog(null, "Car is not available for rent.");
        }
    }
    public void returnCar(Car car) {
        car.returnCar();
        Rental rentalToRemove = null;
        for (Rental rental : rentals) {
            if (rental.getCar() == car) {
                rentalToRemove = rental;
                break;
            }
        }
        if (rentalToRemove != null) {
            rentals.remove(rentalToRemove);
        } else {
            JOptionPane.showMessageDialog(null, "Car was not rented.");
        }
    }
    public void showMenu() {
        JFrame frame = new JFrame("Car Rental System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());
        JPanel titlePanel = new JPanel();
        JLabel title = new JLabel("Welcome to Car Rental System");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        titlePanel.add(title);
        frame.add(titlePanel, BorderLayout.NORTH);
        JPanel buttonPanel = new JPanel(new GridLayout(0, 1, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        JButton viewCarsButton = new JButton("View All Cars");
        JButton rentCarButton = new JButton("Rent a Car");
        JButton returnCarButton = new JButton("Return a Car");
        JButton viewRentalsButton = new JButton("View Rental History");
        JButton exitButton = new JButton("Exit");
        viewCarsButton.setFont(new Font("Arial", Font.PLAIN, 18));
        rentCarButton.setFont(new Font("Arial", Font.PLAIN, 18));
        returnCarButton.setFont(new Font("Arial", Font.PLAIN, 18));
        viewRentalsButton.setFont(new Font("Arial", Font.PLAIN, 18));
        exitButton.setFont(new Font("Arial", Font.PLAIN, 18));
        viewCarsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewAllCars();
            }
        });
        rentCarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rentCarUI();
            }
        });
        returnCarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                returnCarUI();
            }
        });
        viewRentalsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewRentalHistory();
            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        buttonPanel.add(viewCarsButton);
        buttonPanel.add(rentCarButton);
        buttonPanel.add(returnCarButton);
        buttonPanel.add(viewRentalsButton);
        buttonPanel.add(exitButton);
        frame.add(buttonPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
    private void viewAllCars() {
        JFrame carsFrame = new JFrame("All Cars");
        carsFrame.setSize(600, 400);
        carsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        carsFrame.setLayout(new BorderLayout());
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel title = new JLabel("All Cars");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        titlePanel.add(title);
        carsFrame.add(titlePanel, BorderLayout.NORTH);
        JTextArea carsTextArea = new JTextArea();
        carsTextArea.setEditable(false);
        carsTextArea.setFont(new Font("Arial", Font.PLAIN, 16));
     // Append each car's details to the text area
        for (Car car : cars) {
            carsTextArea.append("ID: " + car.getCarId() + "\n");
            carsTextArea.append("Brand: " + car.getBrand() + "\n");
            carsTextArea.append("Model: " + car.getModel() + "\n");
            carsTextArea.append("Price per Day: $" + car.calculatePrice(1) + "\n");
            carsTextArea.append("Available: " + (car.isAvailable() ? "Yes" : "No") + "\n\n");
        }
        JScrollPane scrollPane = new JScrollPane(carsTextArea);
        carsFrame.add(scrollPane, BorderLayout.CENTER);
        // Create an "Exit" button to return to the main menu
        JButton exitButton = new JButton("Back");
        exitButton.setFont(new Font("Arial", Font.PLAIN, 18));
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carsFrame.dispose(); // Close the current frame
            }
        });   
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(exitButton);
        carsFrame.add(buttonPanel, BorderLayout.SOUTH);
    
        carsFrame.setVisible(true);
    }
    private void rentCarUI() {
        String customerName = JOptionPane.showInputDialog("Enter your name:");

        if (customerName == null || customerName.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Customer name cannot be empty.");
            return;
        }
        StringBuilder availableCars = new StringBuilder();
        for (Car car : cars) {
            if (car.isAvailable()) {
                availableCars.append(car.getCarId())
                        .append(" - ")
                        .append(car.getBrand())
                        .append(" ")
                        .append(car.getModel())
                        .append("\n");
            }
        }
        if (availableCars.length() == 0) {
            JOptionPane.showMessageDialog(null, "No cars available for rent.");
            return;
        }
        String carId = JOptionPane.showInputDialog("Available Cars:\n" + availableCars.toString() + "\nEnter the car ID you want to rent:");

        if (carId == null || carId.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Car ID cannot be empty.");
            return;
        }
        String rentalDaysStr = JOptionPane.showInputDialog("Enter the number of days for rental:");
        int rentalDays;
        try {
            rentalDays = Integer.parseInt(rentalDaysStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid number of days.");
            return;
        }
        Customer newCustomer = new Customer("CUS" + (customers.size() + 1), customerName);
        addCustomer(newCustomer);
        Car selectedCar = null;
        for (Car car : cars) {
            if (car.getCarId().equals(carId) && car.isAvailable()) {
                selectedCar = car;
                break;
            }
        }
        if (selectedCar != null) {
            double totalPrice = selectedCar.calculatePrice(rentalDays);
            int confirm = JOptionPane.showConfirmDialog(null, String.format
            ("Customer ID: %s\nCustomer Name: %s\nCar: %s %s\nRental Days: %d\nTotal Price: $%.2f\nConfirm rental?",
             newCustomer.getCustomerId(),
             newCustomer.getName(), selectedCar.getBrand(), selectedCar.getModel(), rentalDays, totalPrice));
            if (confirm == JOptionPane.YES_OPTION) {
                rentCar(selectedCar, newCustomer, rentalDays);
                JOptionPane.showMessageDialog(null, "Car rented successfully.");
            } else {
                JOptionPane.showMessageDialog(null, "Rental canceled.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Invalid car selection or car not available for rent.");
        }
    }
    private void returnCarUI() {
        String carId = JOptionPane.showInputDialog("Enter the car ID you want to return:");

        if (carId == null || carId.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Car ID cannot be empty.");
            return;
        }
        Car carToReturn = null;
        for (Car car : cars) {
            if (car.getCarId().equals(carId)) {
                carToReturn = car;
                break;
            }
        }
        if (carToReturn != null && !carToReturn.isAvailable()) {
            returnCar(carToReturn);
            JOptionPane.showMessageDialog(null, "Car returned successfully.");
        } else {
            JOptionPane.showMessageDialog(null, "Invalid car ID or car was not rented.");
        }
    }
    private void viewRentalHistory() {
        StringBuilder rentalList = new StringBuilder("Rental History:\n\n");
        for (Rental rental : rentals) {
            rentalList.append("Customer ID: ").append(rental.getCustomer().getCustomerId())
                    .append(", Customer Name: ").append(rental.getCustomer().getName())
                    .append(", Car: ").append(rental.getCar().getBrand())
                    .append(" ").append(rental.getCar().getModel())
                    .append(", Days: ").append(rental.getDays())
                    .append("\n");
        }
        JOptionPane.showMessageDialog(null, rentalList.toString());
    }
    public static void main(String[] args) {
        CarRentalSystem rentalSystem = new CarRentalSystem();
        rentalSystem.addCar(new Car("1", "Toyota", "Camry", 50));
        rentalSystem.addCar(new Car("2", "Honda", "Civic", 45));
        rentalSystem.addCar(new Car("3", "Ford", "Mustang", 80));
        rentalSystem.addCar(new Car("4", "Chevrolet", "Malibu", 55));

        rentalSystem.showMenu();
    }
}