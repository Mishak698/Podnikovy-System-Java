import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

class CompanySystem {
    private static CompanySystem instance;
    private List<Employee> employees = new ArrayList<>();
    private List<Order> orders = new ArrayList<>();
    private List<Inventory> inventories = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    private CompanySystem() {}

    public static CompanySystem getInstance() {
        if (instance == null) {
            instance = new CompanySystem();
        }
        return instance;
    }

    public void menu() {
        while (true) {
            System.out.println("\n--- Menu Podnikového systému---");
            System.out.println("1. Zaměstnanci");
            System.out.println("2. Objednávky");
            System.out.println("3. Sklad");
            System.out.println("4. Exit");
            System.out.print("Výběr: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> manageEmployees();
                case 2 -> manageOrders();
                case 3 -> manageInventory();
                case 4 -> {
                    System.out.println("Exit...");
                    return;
                }
                default -> System.out.println("Špatný rozkaz, zkuste to znovu");
            }
        }
    }

    private void manageEmployees() {
        while (true) {
            System.out.println("\n--- Zaměstanci ---");
            System.out.println("1. Přidat Zaměstnance");
            System.out.println("2. Zobrazit Zaměstnance");
            System.out.println("3. Aktualizovat Zaměstnance");
            System.out.println("4. Smazat Zaměstnance");
            System.out.println("5. Zpět do menu");
            System.out.print("Výběr: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addEmployee();
                case 2 -> viewEmployees();
                case 3 -> updateEmployee();
                case 4 -> deleteEmployee();
                case 5 -> {
                    return;
                }
                default -> System.out.println("Špatný rozkaz, zkuste to znovu");
            }
        }
    }

    private void addEmployee() {
        System.out.print("Zadejte Jméno: ");
        String firstName = scanner.nextLine();
        System.out.print("Zadejte Příjmění: ");
        String lastName = scanner.nextLine();
        System.out.print("Zadejte ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Zadejte Pracovní pozici: ");
        String position = scanner.nextLine();
        System.out.print("Zadejte Mzdu: ");
        double salary = scanner.nextDouble();
        scanner.nextLine();

        Employee employee = new Employee(firstName, lastName, id, position, salary);
        employees.add(employee);
        System.out.println("Zaměstnanec Přidán: " + employee);
    }

    private void viewEmployees() {
        if (employees.isEmpty()) {
            System.out.println("Žádný Zaměstnanec v Databázi.");
        } else {
            employees.forEach(System.out::println);
        }
    }

    private void updateEmployee() {
        System.out.print("Zadejte zaměstnancovo ID pro aktualizaci: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Optional<Employee> employeeOptional = employees.stream().filter(e -> e.getId() == id).findFirst();

        if (employeeOptional.isPresent()) {
            Employee employee = employeeOptional.get();
            System.out.print("Zadejte Pracovní Pozici: ");
            String position = scanner.nextLine();
            System.out.print("Zadejte Novou Mzdu: ");
            double salary = scanner.nextDouble();
            scanner.nextLine();

            employee.setPosition(position);
            employee.setSalary(salary);
            System.out.println("Zaměstnanec Aktualizován: " + employee);
        } else {
            System.out.println("Zaměstnanec Nebyl Nalezen.");
        }
    }

    private void deleteEmployee() {
        System.out.print("Zadejte Zaměstnancovo ID k Smazání: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        if (employees.removeIf(e -> e.getId() == id)) {
            System.out.println("Zaměstnanec Smazán.");
        } else {
            System.out.println("Zaměstnanec Nebyl Nalezen.");
        }
    }

    private void manageOrders() {
        while (true) {
            System.out.println("\n--- Objednávky ---");
            System.out.println("1. Přidat Objednávku");
            System.out.println("2. Zobrazit Objednávky");
            System.out.println("3. Aktualizace Stavu Objednávky");
            System.out.println("4. Smazat Objednávku");
            System.out.println("5. Zpět do menu");
            System.out.print("Výběr: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addOrder();
                case 2 -> viewOrders();
                case 3 -> updateOrder();
                case 4 -> deleteOrder();
                case 5 -> {
                    return;
                }
                default -> System.out.println("Špatný rozkaz, zkuste to znovu");
            }
        }
    }

    private void addOrder() {
        System.out.print("Zadejte ID Objednávky: ");
        int orderId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Zadejte Název Objednávky: ");
        String title = scanner.nextLine();
        System.out.print("Zadejte Popisek: ");
        String description = scanner.nextLine();
        System.out.print("Zadejte Stav Objednávky (Přijata/Probíhá/Dokončena): ");
        String status = scanner.nextLine();
        System.out.print("Zadejte Počet Dní do Konce Termínu: ");
        int daysToDeadline = scanner.nextInt();
        scanner.nextLine();

        Order order = new Order(orderId, title, description, status, LocalDate.now(), LocalDate.now().plusDays(daysToDeadline));
        orders.add(order);
        System.out.println("Objednávky Přidána: " + order);
    }

    private void viewOrders() {
        if (orders.isEmpty()) {
            System.out.println("Žádné Objednávky Nebyly Nalezeny.");
        } else {
            orders.forEach(System.out::println);
        }
    }

    private void updateOrder() {
        System.out.print("Zadejte ID Objednávky Kterou Chcete změnit: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Optional<Order> orderOptional = orders.stream().filter(o -> o.getOrderId() == id).findFirst();

        if (orderOptional.isPresent()) {
            Order order = orderOptional.get();
            System.out.print("Zadejte Nový Stav Objednávky (Přijata/Probíhá/Dokončena): ");
            String status = scanner.nextLine();
            order.setStatus(status);
            System.out.println("Objednávka Aktualizována: " + order);
        } else {
            System.out.println("Objednávka nebyla nalezena.");
        }
    }

    private void deleteOrder() {
        System.out.print("Zadejte ID Objednávky Kterou Chcete Smazat: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        if (orders.removeIf(o -> o.getOrderId() == id)) {
            System.out.println("Objednávka Smazána.");
        } else {
            System.out.println("Objednávka nebyla nalezena.");
        }
    }

    private void manageInventory() {
        while (true) {
            System.out.println("\n--- Sklad ---");
            System.out.println("1. Přidat Položku do Skladu");
            System.out.println("2. Zobrazit Sklad");
            System.out.println("3. Aktualizovat Sklad");
            System.out.println("4. Smazat Skladovou Položku");
            System.out.println("5. Zpět do Menu");
            System.out.print("Výběr: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addInventory();
                case 2 -> viewInventory();
                case 3 -> updateInventory();
                case 4 -> deleteInventory();
                case 5 -> {
                    return;
                }
                default -> System.out.println("Špatný rozkaz, zkuste to znovu.");
            }
        }
    }

    private void addInventory() {
        System.out.print("Zadejte Název Položky ve Skladu: ");
        String name = scanner.nextLine();
        System.out.print("Zadejte ID Položky: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Zadejte Počet Kusů na Skladě: ");
        int stock = scanner.nextInt();
        System.out.print("Zadejte Minimální Počet Kusů na Skladě: ");
        int minStock = scanner.nextInt();
        scanner.nextLine();

        Inventory inventory = new Inventory(name, id, stock, minStock);
        inventories.add(inventory);
        System.out.println("Položka Byla Přidána: " + inventory);
    }

    private void viewInventory() {
        if (inventories.isEmpty()) {
            System.out.println("Ve Skladu nebylo nic nalezeno.");
        } else {
            inventories.forEach(System.out::println);
        }
    }

    private void updateInventory() {
        System.out.print("Zadejte ID Položky ve Skladu Kterou Chcete Aktualizovat: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Optional<Inventory> inventoryOptional = inventories.stream().filter(i -> i.getItemId() == id).findFirst();

        if (inventoryOptional.isPresent()) {
            Inventory inventory = inventoryOptional.get();
            System.out.print("Zadejte Změno Stavu Položky(+/-): ");
            int stockChange = scanner.nextInt();
            scanner.nextLine();
            inventory.updateStock(stockChange);
            System.out.println("Sklad Aktualizován: " + inventory);
        } else {
            System.out.println("Skladová Položka Nebyla nalezena.");
        }
    }

    private void deleteInventory() {
        System.out.print("Zadejte ID Skladové Položky Kterou Chcete Smazat: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        if (inventories.removeIf(i -> i.getItemId() == id)) {
            System.out.println("Skladová Položka Smazána.");
        } else {
            System.out.println("Skladová Položka Nebyla Nalezena.");
        }
    }

    public static void main(String[] args) {
        CompanySystem.getInstance().menu();
    }
}
