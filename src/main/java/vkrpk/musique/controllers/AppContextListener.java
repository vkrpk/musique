// package vkrpk.musique.controllers;

// import java.sql.Driver;
// import java.sql.DriverManager;
// import java.sql.SQLException;
// import java.util.Enumeration;

// import com.mysql.cj.jdbc.AbandonedConnectionCleanupThread;

// @jakarta.servlet.annotation.WebListener
// public class AppContextListener implements jakarta.servlet.ServletContextListener {

//     @Override
//     public void contextDestroyed(jakarta.servlet.ServletContextEvent sce) {
//         AbandonedConnectionCleanupThread.checkedShutdown();

//         // Enumeration<Driver> drivers = DriverManager.getDrivers();
//         // while (drivers.hasMoreElements()) {
//         //     Driver driver = drivers.nextElement();
//         //     try {
//         //         DriverManager.deregisterDriver(driver);
//         //         sce.getServletContext().log(String.format("Deregistering JDBC driver %s", driver));
//         //     } catch (SQLException e) {
//         //         sce.getServletContext().log(String.format("Error deregistering JDBC driver %s", driver), e);
//         //     }
//         // }
//     }
// }
