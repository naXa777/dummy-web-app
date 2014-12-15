package by.naxa.dao.util;

import org.hibernate.dialect.MySQL5Dialect;

/**
 * Addresses a bug in Hibernate 4.1 when ddl.auto set to validate.
 *
 * @author alanhay
 *
 */
public class MySQL5BooleanDialect extends MySQL5Dialect {
	public MySQL5BooleanDialect() {
		super();
		registerColumnType(java.sql.Types.BOOLEAN, "bit");
	}
}
