package com.example.testBackend.fill;

import com.example.testBackend.connection.DatabaseConnection;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;
import java.sql.Statement;

public class FillTheEmpty {
    public void furfill(JdbcTemplate jtm) throws SQLException {
        Statement stmt = null;
        try {
            stmt = (new DatabaseConnection().getConnection()).createStatement();
            if ((jtm.query("select * from miniuniversity", new ColumnMapRowMapper())).size()==0) {
                stmt.executeUpdate("insert into miniuniversity(NAME, AGE, GROUPS, TEACHER) values ('a',22,'b','c')");
                stmt.executeUpdate("insert into miniuniversity(NAME, AGE, GROUPS, TEACHER) values ('d',16,'b','c')");
                stmt.executeUpdate("insert into miniuniversity(NAME, AGE, GROUPS, TEACHER) values ('e',48,'f','c')");
                stmt.executeUpdate("insert into miniuniversity(NAME, AGE, GROUPS, TEACHER) values ('a',22,'b','g')");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if (stmt != null) { stmt.close(); }
        }
    }
}
