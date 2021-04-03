package ch.zhaw.iwi.devops.service;

import org.junit.BeforeClass;

import ch.zhaw.iwi.devops.server.Database;

public abstract class AbstractDatabaseUnitTest extends AbstractUnitTest {

	@BeforeClass
	public static void beforeClass() throws Exception {
		getInjector().getInstance(Database.class).init("9996", "9997", false);
	}

}
