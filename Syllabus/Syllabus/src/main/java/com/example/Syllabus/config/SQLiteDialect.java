// package com.example.Syllabus.config;

// import java.sql.Types;
// import org.hibernate.boot.model.TypeContributions;
// import org.hibernate.dialect.Dialect;
// import org.hibernate.dialect.identity.IdentityColumnSupport;
// import org.hibernate.dialect.identity.IdentityColumnSupportImpl;
// import org.hibernate.engine.jdbc.dialect.spi.DialectResolutionInfo;
// import org.hibernate.engine.jdbc.dialect.spi.StandardDialectResolver;
// import org.hibernate.service.ServiceRegistry;
// import org.hibernate.type.SqlTypes;
// import org.hibernate.type.spi.TypeConfiguration;

// public class SQLiteDialect extends Dialect {

//     public SQLiteDialect() {
//         super();
//     }

//     @Override
//     public void contributeTypes(TypeContributions typeContributions, ServiceRegistry serviceRegistry) {
//         TypeConfiguration typeConfiguration = typeContributions.getTypeConfiguration();
        
//         typeConfiguration.getBasicTypeRegistry().register(SqlTypes.INTEGER, "integer");
//         typeConfiguration.getBasicTypeRegistry().register(SqlTypes.VARCHAR, "varchar");
//         typeConfiguration.getBasicTypeRegistry().register(SqlTypes.BOOLEAN, "integer");
//         typeConfiguration.getBasicTypeRegistry().register(SqlTypes.TIMESTAMP, "timestamp");
//         typeConfiguration.getBasicTypeRegistry().register(SqlTypes.DATE, "date");
//         typeConfiguration.getBasicTypeRegistry().register(SqlTypes.FLOAT, "float");
//         typeConfiguration.getBasicTypeRegistry().register(SqlTypes.DOUBLE, "double");
//         typeConfiguration.getBasicTypeRegistry().register(SqlTypes.BLOB, "blob");
//     }

//     @Override
//     public IdentityColumnSupport getIdentityColumnSupport() {
//         return new SQLiteIdentityColumnSupport();
//     }

//     @Override
//     public boolean supportsIdentityColumns() {
//         return true;

//     }

//     @Override
//     public String getIdentityColumnString() {
//         return "integer";
//     }

//     @Override
//     public String getIdentitySelectString() {
//         return "select last_insert_rowid()";
//     }

//     @Override
//     public boolean supportsLimit() {
//         return true;
//     }

//     @Override
//     protected String getLimitString(String query, boolean hasOffset) {
//         return query + (hasOffset ? " limit ? offset ?" : " limit ?");
//     }

//     @Override
//     public boolean supportsTemporaryTables() {
//         return true;
//     }

//     @Override
//     public String getCreateTemporaryTableString() {
//         return "create temporary table if not exists";
//     }

//     @Override
//     public boolean dropTemporaryTableAfterUse() {
//         return false;
//     }

//     @Override
//     public boolean supportsCurrentTimestampSelection() {
//         return true;
//     }

//     @Override
//     public String getCurrentTimestampSelectString() {
//         return "select current_timestamp";
//     }

//     @Override
//     public boolean supportsUnionAll() {
//         return true;
//     }

//     @Override
//     public boolean hasAlterTable() {
//         return false;
//     }

//     @Override
//     public boolean dropConstraints() {
//         return false;
//     }

//     @Override
//     public String getAddColumnString() {
//         return "add column";
//     }

//     @Override
//     public String getForUpdateString() {
//         return "";
//     }

//     @Override
//     public boolean supportsOuterJoinForUpdate() {
//         return false;
//     }

//     @Override
//     public String getDropForeignKeyString() {
//         throw new UnsupportedOperationException("SQLite ne supporte pas les foreign keys");
//     }

//     @Override
//     public String getAddForeignKeyConstraintString(String constraintName,
//                                                    String[] foreignKey, String referencedTable,
//                                                    String[] primaryKey, boolean referencesPrimaryKey) {
//         throw new UnsupportedOperationException("SQLite ne supporte pas les foreign keys");
//     }

//     @Override
//     public String getAddPrimaryKeyConstraintString(String constraintName) {
//         throw new UnsupportedOperationException("SQLite ne supporte pas les primary keys sur les colonnes existantes");
//     }

//     @Override
//     public boolean supportsIfExistsBeforeTableName() {
//         return true;
//     }

//     @Override
//     public boolean supportsCascadeDelete() {
//         return false;
//     }
// }

// /**
//  * Support des colonnes d'identité pour SQLite.
//  */
// class SQLiteIdentityColumnSupport extends IdentityColumnSupportImpl {

//     @Override
//     public boolean supportsIdentityColumns() {
//         return true;
//     }

//     @Override
//     public String getIdentitySelectString(String table, String column, int type) {
//         return "select last_insert_rowid()";
//     }

//     @Override
//     public String getIdentityColumnString(int type) {
//         return "integer";
//     }
// }
