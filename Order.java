import java.time.LocalDate;

class Order {
    private int orderId;
    private String title;
    private String description;
    private String status;
    private LocalDate dateReceived;
    private LocalDate deadline;

    public Order(int orderId, String title, String description, String status, LocalDate dateReceived, LocalDate deadline) {
        this.orderId = orderId;
        this.title = title;
        this.description = description;
        this.status = status;
        this.dateReceived = dateReceived;
        this.deadline = deadline;
    }

    public int getOrderId() {
        return orderId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Objednávka [ID: " + orderId + ", Název: " + title + ", Stav: " + status + ", Přijato: " + dateReceived + ", Termín: " + deadline + "]";
    }
}
