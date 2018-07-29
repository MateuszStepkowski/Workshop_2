package pl.coderslab.sql;

public class servicedTables {

    String user_group_Table =
            "CREATE TABLE user_group(" +
                    "  id INT(11) NOT NULL AUTO_INCREMENT," +
                    "  name VARCHAR(255)," +
                    "  PRIMARY KEY(id));";
    String users_Table =
            "CREATE TABLE users("+
            "  id BIGINT(20) NOT NULL AUTO_INCREMENT,"+
            "  username VARCHAR(255),"+
            "  email VARCHAR(255) UNIQUE,"+
            "  password VARCHAR(255),"+
            "  user_group_id INT(11),"+
            "  PRIMARY KEY(id),"+
            "  FOREIGN KEY(user_group_id) REFERENCES user_group(id));";
    String exercise_Table =
            "CREATE TABLE exercise(" +
                    "  id INT(11) NOT NULL AUTO_INCREMENT," +
                    "  title VARCHAR(255)," +
                    "  description TEXT," +
                    "  PRIMARY KEY(id));";
    String solution_Table =
            "CREATE TABLE solution(" +
                    "  id INT(11) NOT NULL AUTO_INCREMENT," +
                    "  created DATETIME," +
                    "  updated DATETIME," +
                    "  description TEXT," +
                    "  exercise_id INT(11)," +
                    "  users_id BIGINT(20)," +
                    "  PRIMARY KEY(id)," +
                    "  FOREIGN KEY(exercise_id) REFERENCES exercise(id)," +
                    "  FOREIGN KEY(users_id) REFERENCES users(id));";
}
