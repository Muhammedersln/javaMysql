import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;



public class ProductOwner extends TeamMember implements Runnable {
    public ProductOwner(String name, int id) {
        super(name, id);
    }

    @Override
    public void run() {
        // Örnek: Yeni bir görev ekleme
        addTaskToProductBacklog("Yeni Görev", 1, 5);
    }

    private void addTaskToProductBacklog(String taskName, int backlogId, int priority) {
        String sql = "INSERT INTO product_backlog (taskname, backlogId, priority) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, taskName);
            pstmt.setInt(2, backlogId);
            pstmt.setInt(3, priority);
            pstmt.executeUpdate();
            System.out.println("Product Owner Görev Ekledi.");
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
        }
    }

    @Override
    public void dailyUpdate() {
        // Günlük güncelleme
        System.out.println(getName() + " daily update.");
    }
}
