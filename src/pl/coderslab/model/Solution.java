package pl.coderslab.model;

import pl.coderslab.sql.DbManager;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Solution {

    private int id;
    private Date created;
    private Date updated;
    private String description;
    private int exercise_id;
    private long users_id;



    public Solution() {}

    public Solution(Date created, Date updated, String description, int exercise_id, long users_id) {
        this.created = created;
        this.updated = updated;
        this.description = description;
        this.exercise_id = exercise_id;
        this.users_id = users_id;
    }


    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }



    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }



    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



    public int getExercise_id() {
        return exercise_id;
    }

    public void setExercise_id(int exercise_id) {
        this.exercise_id = exercise_id;
    }



    public long getUsers_id() {
        return users_id;
    }

    public void setUsers_id(long users_id) {
        this.users_id = users_id;
    }



    public int getId() {
        return id;
    }


    public void saveToDB() {
        if(this.id  == 0) {
            try {
                String sql = "INSERT INTO solution(created, updated, description, exercise_id, users_id) " +
                             "VALUES (?,?,?,?,?)";
                String generatedColumns[] = {"ID"};
                PreparedStatement preparedStatement;
                preparedStatement = DbManager.getInstance().getConnection().prepareStatement(sql, generatedColumns);
                preparedStatement.setDate(1, this.created);
                preparedStatement.setDate(2, this.updated);
                preparedStatement.setString(3, this.description);
                preparedStatement.setInt(4, this.exercise_id);
                preparedStatement.setLong(5, this.users_id);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {e.printStackTrace(); }
        } else {
            try {
                String sql = "UPDATE solution SET created =?, updated =?, description =?, exercise_id =?, " +
                             "users_id =? WHERE id =?";
                PreparedStatement preparedStatement;
                preparedStatement = DbManager.getInstance().getConnection().prepareStatement(sql);
                preparedStatement.setDate(1, this.created);
                preparedStatement.setDate(2, this.updated);
                preparedStatement.setString(3, this.description);
                preparedStatement.setInt(4, this.exercise_id);
                preparedStatement.setLong(5, this.users_id);
                preparedStatement.setInt(6, this.id);
                preparedStatement.executeUpdate();
            } catch (SQLException e) { e.printStackTrace(); }
        }
    }

    public void delete() {
        if(this.id != 0) {
            try {
                String sql = "DELETE FROM solution WHERE id =?";
                PreparedStatement preparedStatement;
                preparedStatement = DbManager.getInstance().getConnection().prepareStatement(sql);
                preparedStatement.setInt(1, this.id);
                preparedStatement.executeUpdate();
                this.id = 0;
            } catch (SQLException e) { e.printStackTrace(); }
        }
    }

    public static Solution loadById(int id) {
        try {
            String sql = "SELECT * FROM solution WHERE id =?";
            PreparedStatement preparedStatement;
            preparedStatement = DbManager.getInstance().getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()) {
                Solution solution = new Solution();
                solution.id = rs.getInt("id");
                solution.created = rs.getDate("created");
                solution.updated = rs.getDate("updated");
                solution.description = rs.getString("description");
                solution.exercise_id = rs.getInt("exercise_id");
                solution.users_id = rs.getLong("users_id");
                return solution;
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public static ArrayList<Solution> loadAll() {
        try {
            ArrayList<Solution> solutions = new ArrayList<>();
            String sql = "SELECT * FROM solution";
            PreparedStatement preparedStatement;
            preparedStatement = DbManager.getInstance().getConnection().prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()) {
                Solution solution = new Solution();
                solution.id = rs.getInt("id");
                solution.created = rs.getDate("created");
                solution.updated = rs.getDate("updated");
                solution.description = rs.getString("description");
                solution.exercise_id = rs.getInt("exercise_id");
                solution.users_id = rs.getLong("users_id");
                solutions.add(solution);
            }
            return solutions;
        } catch (SQLException e) {  e.printStackTrace(); }
        return null;
    }

    public static ArrayList<Solution> loadAllByUserId(long userId) {
        try {
            ArrayList<Solution> solutions = new ArrayList<>();
            String sql = "SELECT * FROM solution WHERE users_id =?";
            PreparedStatement preparedStatement;
            preparedStatement = DbManager.getInstance().getConnection().prepareStatement(sql);
            preparedStatement.setLong(1, userId);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()) {
                Solution solution = new Solution();
                solution.id = rs.getInt("id");
                solution.created = rs.getDate("created");
                solution.updated = rs.getDate("updated");
                solution.description = rs.getString("description");
                solution.exercise_id = rs.getInt("exercise_id");
                solution.users_id = rs.getLong("users_id");
                solutions.add(solution);
            }
            return solutions;
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public static ArrayList<Solution> loadAllByExerciseId(int exerciseId) {
        try {
            ArrayList<Solution> solutions = new ArrayList<>();
            String sql = "SELECT * FROM solution WHERE exercise_id =? " +
                         "ORDER BY updated, created DESC";
            PreparedStatement preparedStatement;
            preparedStatement = DbManager.getInstance().getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, exerciseId);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()) {
                Solution solution = new Solution();
                solution.id = rs.getInt("id");
                solution.created = rs.getDate("created");
                solution.updated = rs.getDate("updated");
                solution.description = rs.getString("description");
                solution.exercise_id = rs.getInt("exercise_id");
                solution.users_id = rs.getLong("users_id");
                solutions.add(solution);
            }
            return solutions;
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }
}
