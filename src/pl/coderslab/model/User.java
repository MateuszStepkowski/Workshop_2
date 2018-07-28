package pl.coderslab.model;

import org.mindrot.BCrypt;
import pl.coderslab.sql.DbManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class User {
    private int id;
    private String name;
    private String username;
    private String password;
    private String email;

    public User(String name, String username, String password, String email) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public User() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = BCrypt.hashpw(password,BCrypt.gensalt());
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public void saveToDB(){

        if(this.id==0){
            try {
                String sql = "INSERT INTO users(username, email, password) VALUES (?, ?, ?)";
                String generatedColumns[] = {"ID"};
                PreparedStatement preparedStatement;
                preparedStatement = DbManager.getInstance().getConnection().prepareStatement(sql, generatedColumns);
                preparedStatement.setString(1, this.username);
                preparedStatement.setString(2, this.email);
                preparedStatement.setString(3, this.password);
                preparedStatement.executeUpdate();
                ResultSet rs = preparedStatement.getGeneratedKeys();
                if (rs.next()) {
                    this.id = rs.getInt(1);
                }
            }catch (SQLException e){e.printStackTrace();}

        }else{
            try{
                String sql = "UPDATE users SET username=?, email=?, password=? where id = ?";
                PreparedStatement preparedStatement;
                preparedStatement = DbManager.getInstance().getConnection().prepareStatement(sql);
                preparedStatement.setString(1, this.username);
                preparedStatement.setString(2, this.email);
                preparedStatement.setString(3, this.password);
                preparedStatement.setInt(4, this.id);
                preparedStatement.executeUpdate();
            }catch (SQLException e){e.printStackTrace();}
        }
    }
    public void delete(){
        try {
            String sql = "DELETE FROM users WHERE id = ?";
            PreparedStatement preparedStatement;
            preparedStatement = DbManager.getInstance().getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, this.id);
            preparedStatement.executeUpdate();
            this.id=0;
        }catch (SQLException e){e.printStackTrace();}
    }

    public static User loadById(int id){
        try {
            String sql = "SELECT * FROM users WHERE id = ?";
            PreparedStatement preparedStatement;
            preparedStatement = DbManager.getInstance().getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                User user = new User();
                user.id = rs.getInt("id");
                user.username = rs.getString("username");
                user.email = rs.getString("email");
                user.password = rs.getString("password");
                return user;
            }
        }catch (SQLException e){e.printStackTrace();}
        return null;
    }
    public static ArrayList<User> loadAll(){
        try {
            ArrayList<User> users = new ArrayList<>();
            String sql = "SELECT * FROM users";
            PreparedStatement preparedStatement;
            preparedStatement = DbManager.getInstance().getConnection().prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                User user = new User();
                user.id = rs.getInt("id");
                user.username = rs.getString("username");
                user.email = rs.getString("email");
                user.password = rs.getString("password");
                users.add(user);
            }
            return users;
        }catch (SQLException e){e.printStackTrace();}
        return null;
    }
}
