
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Developer extends TeamMember implements Runnable {

    public Developer(String name, int id) {
        super(name, id);
    }

    @Override
    public void run() {
        // Örnek: Görevi tamamlayıp board'a ekleme
        completeTaskAndAddToBoard(1, "Görev 1", 1, 1, getName(), 5);
    }

  private void completeTaskAndAddToBoard(int taskId, String taskName, int backlogId, int sprintId, String developerName, int priority) {
    String sqlInsert = "INSERT INTO board (taskId, taskname, backlogId, sprintId, developerName, priority) VALUES (?, ?, ?, ?, ?, ?) ON DUPLICATE KEY UPDATE taskname=?, backlogId=?, sprintId=?, developerName=?, priority=?";
    try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sqlInsert)) {
        pstmt.setInt(1, taskId);
        pstmt.setString(2, taskName);
        pstmt.setInt(3, backlogId);
        pstmt.setInt(4, sprintId);
        pstmt.setString(5, developerName);
        pstmt.setInt(6, priority);
        
        // Update için aynı değerler
        pstmt.setString(7, taskName);
        pstmt.setInt(8, backlogId);
        pstmt.setInt(9, sprintId);
        pstmt.setString(10, developerName);
        pstmt.setInt(11, priority);

        pstmt.executeUpdate();
        System.out.println("Developer " + getName() + " görevini tamamladı.");
    } catch (SQLException e) {
        System.out.println("SQL Exception: " + e.getMessage());
    }
}


    @Override
    public void dailyUpdate() {
        System.out.println(getName() + " daily update.");
    }
}
