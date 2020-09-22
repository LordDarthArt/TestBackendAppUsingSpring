package com.example.testBackend.fill;

import com.example.testBackend.connection.DatabaseConnection;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;
import java.sql.Statement;

public class FillTheEmpty {
    public static void furfill(JdbcTemplate jtm) throws SQLException {
        Statement stmt = null;
        try {
            stmt = (new DatabaseConnection().getConnection()).createStatement();
            if ((jtm.query("select * from news", new ColumnMapRowMapper())).size()==0) {
                stmt.executeUpdate("insert into news(TITLE, DESC, DATE, PIC) values ('Опубликовано сравнение скриншотов из Microsoft Flight Simulator с настоящими пейзажами','Противники видеоигр нередко говорят о размытии границ между " +
                        "виртуальным и реальным миром у геймеров. Пожалуй, даже с этим неоднозначным высказыванием " +
                        "можно согласиться в контексте ультрареалистичных игр вроде Microsoft Flight Simulator 2020. Бета-тестеры " +
                        "тайтла показали скриншоты, которые трудно отличить от настоящих " +
                        "фотографий.','11.05.20','http://s.4pda.to/04GXMmz2rsJnc4iYLTCU28HbK3niKr1dae2Bh.jpg')");
                stmt.executeUpdate("insert into news(TITLE, DESC, DATE, PIC) values ('Xiaomi выпустила универсальный фильтр для воды за $17','Xiaomi объявила о выпуске нового продукта для дома в рамках экосистемы Mijia — водного фильтра Faucet Water Purifier. Он обеспечивает четырёхступечатую очистку и предусматривает максимально простую установку.','11.05.20','http://s.4pda.to/04GXPlFDwjIGJ8OyQWGPou28rc8kScR2toJt.png')");
                stmt.executeUpdate("insert into news(TITLE, DESC, DATE, PIC) values ('«Семья». Официальный Twitter-аккаунт Mafia неожиданно «ожил» после почти двух лет молчания','Судя по всему, слухи о развитии серии Mafia могут оказаться правдивыми. Недавно в Twitter-аккаунте франшизы появилось первое\n" +
                        "сообщение за два года. Разработчики игры были немногословными, но и этого хватило, чтобы раззадорить фанатов.','11.05.20','http://s.4pda.to/04GXNpvwklNP4i2b5G8bqNgCz2NJKr17Kmz1jq.jpg')");
                stmt.executeUpdate("insert into news(TITLE, DESC, DATE, PIC) values ('В Google Play появился загадочный смартфон Redmi','Ранее в сети обнаружили информацию о Redmi 10X, который позже был представлен как Redmi Note 9. Теперь же упоминание этого загадочного аппарата с другими техническими характеристиками появилось в консоли для разработчиков Google Play.','11.05.20','http://s.4pda.to/04GXKqraTHKz2RSQP3VGPIegCz2Np4T5D5xi0v.png')");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if (stmt != null) { stmt.close(); }
        }
    }
}
