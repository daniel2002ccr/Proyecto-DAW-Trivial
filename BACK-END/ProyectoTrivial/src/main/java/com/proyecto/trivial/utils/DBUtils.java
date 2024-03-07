package com.proyecto.trivial.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DBUtils {

	private static final Logger logger = LoggerFactory.getLogger(DBUtils.class);
	private static SessionFactory sessionFactory;

	public static SessionFactory creadorSessionFactory() {
		if (sessionFactory == null) {
			sessionFactory = new Configuration().configure().buildSessionFactory();
		}
		return sessionFactory;
	}

	public static Connection conectaBBDD() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		String urlDB = "jdbc:mysql://localhost:3306/trivial_BBDD?serverTimezone=UTC";
		String user = "root";
		String password = "PracticaRoot";
		Connection connection = DriverManager.getConnection(urlDB, user, password);
		logger.info("Se ha establecido la conexión a la BBDD.");
		return connection;
	}
}