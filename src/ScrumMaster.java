import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class ScrumMaster extends TeamMember implements Runnable {
    public ScrumMaster(String name, int id) {
        super(name, id);
    }

    @Override
    public void run() {
        // Örnek: Sprint backlog'a görev ekleme
        addTaskToSprintBacklog(1, "Görev 1", 1, 1, 5);
    }

private void addTaskToSprintBacklog(int taskId, String taskName, int backlogId, int sprintId, int priority) {
    String sql = "INSERT INTO sprint_backlog (taskId, taskname, backlogId, sprintId, priority) VALUES (?, ?, ?, ?, ?) ON DUPLICATE KEY UPDATE taskname=?, backlogId=?, sprintId=?, priority=?";
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setInt(1, taskId);
        pstmt.setString(2, taskName);
        pstmt.setInt(3, backlogId);
        pstmt.setInt(4, sprintId);
        pstmt.setInt(5, priority);

        // Update için aynı değerler
        pstmt.setString(6, taskName);
        pstmt.setInt(7, backlogId);
        pstmt.setInt(8, sprintId);
        pstmt.setInt(9, priority);

        pstmt.executeUpdate();
        System.out.println("Scrum Master Görevleri Dağıttı");
    } catch (SQLException e) {
        System.out.println("SQL Exception: " + e.getMessage());
    }
}


    @Override
    public void dailyUpdate() {
        System.out.println(getName() + " daily update.");
    }
}
