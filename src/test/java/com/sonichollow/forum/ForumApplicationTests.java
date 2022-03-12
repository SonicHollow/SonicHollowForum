package com.sonichollow.forum;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootTest
class ForumApplicationTests {

    @Autowired //自动装配
    private DataSource dataSource;

    @Test
    void contextLoads() {
    }

    /**
     * 数据库连接池：
     * 1. DBCP
     * 2. C3P0
     * 3. Hikari
     * HikariProxyConnection@1509860853 wrapping com.mysql.cj.jdbc.ConnectionImpl@78eafad
     *
     * @throws SQLException
     */
    @Test
    void getConnect() throws SQLException {
        System.out.println(dataSource.getConnection());
    }
}
