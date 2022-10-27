package com.humana.pharmacy.generator;

import com.querydsl.codegen.BeanSerializer;
import com.querydsl.sql.Configuration;
import com.querydsl.sql.SQLServer2012Templates;
import com.querydsl.sql.codegen.MetaDataExporter;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * The EntityGenerator is used to generate models and entities classes from database metadata.
 *
 * <p>The EntityGenerator should be configured in pom.xml so that it can be easily executed by using maven command "mvn exec:java@generate", for example:
 * <blockquote><pre>
 * &lt;plugin&gt;
 *     &lt;groupId&gt;org.codehaus.mojo&lt;/groupId&gt;
 *     &lt;artifactId&gt;exec-maven-plugin&lt;/artifactId&gt;
 *     &lt;version&gt;3.0.0&lt;/version&gt;&lt;executions&gt;
 *         &lt;execution&gt;
 *             &lt;id&gt;generate&lt;/id&gt;
 *             &lt;configuration&gt;
 *                 &lt;mainClass&gt;com.humana.pharmacy.generator.EntityGenerator&lt;/mainClass&gt;
 *             &lt;/configuration&gt;
 *         &lt;/execution&gt;
 *     &lt;/executions&gt;
 * &lt;/plugin&gt;
 * </pre></blockquote>
 */
public class EntityGenerator {
    /**
     * The SQL driver.
     */
    private static final String SQL_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    /**
     * The target folder.
     */
    private static final String TARGET_FOLDER = "./src/main/java";
    /**
     * The model package.
     */
    private static final String PACKAGE_MODEL = "com.humana.pharmacy.common.model";
    /**
     * The entity package.
     */
    private static final String PACKAGE_ENTITY = "com.humana.pharmacy.common.entity";
    /**
     * The config file key 'epostrx.jdbc.url'.
     */
    private static final String KEY_URL_EPOSTRX = "epostrx.jdbc.url";
    /**
     * The config file key 'epostrx.jdbc.user'.
     */
    private static final String KEY_USER_EPOSTRX = "epostrx.jdbc.user";
    /**
     * The config file key 'epostrx.jdbc.password'.
     */
    private static final String KEY_PASSORD_EPOSTRX = "epostrx.jdbc.password";
    /**
     * The config file key 'epostrx.jdbc.schema'.
     */
    private static final String KEY_SCHEMA_EPOSTRX = "epostrx.jdbc.schema";
    /**
     * The config file key 'epostrx_workflow.jdbc.url'.
     */
    private static final String KEY_URL_EPOSTRX_WORKFLOW = "epostrx_workflow.jdbc.url";
    /**
     * The config file key 'epostrx_workflow.jdbc.user'.
     */
    private static final String KEY_USER_EPOSTRX_WORKFLOW = "epostrx_workflow.jdbc.user";
    /**
     * The config file key 'epostrx_workflow.jdbc.password'.
     */
    private static final String KEY_PASSORD_EPOSTRX_WORKFLOW = "epostrx_workflow.jdbc.password";
    /**
     * The config file key 'epostrx_workflow.jdbc.schema'.
     */
    private static final String KEY_SCHEMA_EPOSTRX_WORKFLOW = "epostrx_workflow.jdbc.schema";

    /**
     * Load JDBC configuration from properties configuration file, then generate models and entities classes.
     *
     * @param args The arguments contain the path to configuration file
     * @throws RuntimeException if the path to configuration file is invalid or any error occurs
     */
    public static void main(String[] args) {
        if (args == null || args.length == 0 || args[0].trim().length() == 0) {
            throw new RuntimeException("Expecting a parameter: the path to a Properties config file");
        }
        try {
            // args[0] should be the path to a Properties config file
            String configFile = args[0];

            // Load jdbcUrl, jdbcUser, jdbcPassword and schema from configFile for epostrx and epostrx_workflow databases
            Properties props = new Properties();
            try (InputStream inputStream = new FileInputStream(configFile)) {
                props.load(inputStream);
            }

            String urlEpostrx = getProperty(props, KEY_URL_EPOSTRX);
            String userEpostrx = getProperty(props, KEY_USER_EPOSTRX);
            String passwordEpostrx = getProperty(props, KEY_PASSORD_EPOSTRX);
            String schemaEpostrx = getProperty(props, KEY_SCHEMA_EPOSTRX);

            String urlEpostrxWorkflow = getProperty(props, KEY_URL_EPOSTRX_WORKFLOW);
            String userEpostrxWorkflow = getProperty(props, KEY_USER_EPOSTRX_WORKFLOW);
            String passwordEpostrxWorkflow = getProperty(props, KEY_PASSORD_EPOSTRX_WORKFLOW);
            String schemaEpostrxWorkflow = getProperty(props, KEY_SCHEMA_EPOSTRX_WORKFLOW);

            // Call generate() for each database:
            generate(urlEpostrx, userEpostrx, passwordEpostrx, schemaEpostrx);
            generate(urlEpostrxWorkflow, userEpostrxWorkflow, passwordEpostrxWorkflow, schemaEpostrxWorkflow);
        } catch (Exception e) {
            throw new RuntimeException("An error occurred during generating classes.", e);
        }
    }

    /**
     * Gets a property value.
     *
     * @param props the Properties object
     * @param key   the property key
     * @return the property value
     * @throws RuntimeException if the property is missing
     */
    private static String getProperty(Properties props, String key) {
        String value = props.getProperty(key);
        if (value == null || value.trim().length() == 0) {
            throw new RuntimeException("The property '" + key + "' is required.");
        }
        return value;
    }

    /**
     * Generate models in com.humana.pharmacy.model package, generate entities in com.humana.pharmacy.entity package.
     *
     * @param schema       Schema to export
     * @param jdbcUser     JDBC username
     * @param jdbcUrl      JDBC url
     * @param jdbcPassword JDBC password
     * @throws ClassNotFoundException if the diver class cannot be located
     * @throws SQLException           if a database access error occurs
     */
    private static void generate(String jdbcUrl, String jdbcUser, String jdbcPassword, String schema)
            throws ClassNotFoundException, SQLException {
        Class.forName(SQL_DRIVER);

        MetaDataExporter exporter = new MetaDataExporter();
        exporter.setConfiguration(new Configuration(SQLServer2012Templates.DEFAULT));

        exporter.setSchemaPattern(schema);
        exporter.setExportForeignKeys(false);
        exporter.setExportPrimaryKeys(false);

        exporter.setTargetFolder(new File(TARGET_FOLDER));
        exporter.setBeanSerializer(new BeanSerializer());

        exporter.setBeanPackageName(PACKAGE_MODEL);
        exporter.setPackageName(PACKAGE_ENTITY);

        try (Connection dbConn = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword)) {
            exporter.export(dbConn.getMetaData());
        }
    }
}

