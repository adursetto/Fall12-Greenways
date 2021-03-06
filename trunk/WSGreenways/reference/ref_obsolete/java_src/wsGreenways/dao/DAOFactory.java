package wsGreenways.dao;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * The central mediator for all Database Access Objects. The production instance uses the database connection
 * pool provided by Tomcat (so use the production instance when doing stuff from JSPs in the "real code").
 * Both the production and the test instance parses the context.xml file to get the JDBC connection.
 * 
 * Also, @see {@link EvilDAOFactory} and @see {@link TestDAOFactory}.
 * 
 * Any DAO that is added to the system should be added in this class, in the same way that all other DAOs are.
 * 
 * @author Andy
 * 
 */
public class DAOFactory {
	private static DAOFactory productionInstance = null;
	private IConnectionDriver driver;

	/**
	 * 
	 * @return A production instance of the DAOFactory, to be used in deployment (by Tomcat).
	 */
	public static DAOFactory getProductionInstance() {
		if (productionInstance == null)
			productionInstance = new DAOFactory();
		return productionInstance;
	}

	/**
	 * Protected constructor. Call getProductionInstance to get an instance of the DAOFactory
	 */
	protected DAOFactory() {
		this.driver = new ProductionConnectionDriver();
	}

	/**
	 * 
	 * @return this DAOFactory's Connection
	 * @throws SQLException
	 */
	public Connection getConnection() throws SQLException {
		return driver.getConnection();
	}
	
}
