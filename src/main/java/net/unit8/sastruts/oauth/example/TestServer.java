package net.unit8.sastruts.oauth.example;

import org.apache.catalina.Context;
import org.apache.catalina.core.AprLifecycleListener;
import org.apache.catalina.core.StandardServer;
import org.apache.catalina.startup.Tomcat;
import org.apache.commons.io.FileUtils;
import org.h2.tools.Server;
import org.seasar.extension.jdbc.gen.internal.command.GenerateDdlCommand;
import org.seasar.extension.jdbc.gen.internal.command.MigrateCommand;
import org.seasar.framework.container.hotdeploy.HotdeployClassLoader;
import org.seasar.framework.convention.impl.NamingConventionImpl;
import org.seasar.framework.exception.IORuntimeException;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

/**
 * The standalone server for an example.
 *
 * @author kawasima
 */
public class TestServer {
    public static void startDatabase() throws SQLException {
        Server h2Server = Server.createTcpServer(new String[]{"-tcpPort", "8094"});
        h2Server.start();
    }

    public static void migrateSchema() {
        try {
            if (new File("db").exists())
                FileUtils.forceDelete(new File("db"));
        } catch (IOException e) {
            throw new IORuntimeException(e);
        }
        ClassLoader originalClassLoader = Thread.currentThread().getContextClassLoader();
        NamingConventionImpl namingConvention = new NamingConventionImpl();
        namingConvention.addRootPackageName("net.unit8.sastruts.oauth.example");
        HotdeployClassLoader temporalClassLoader = new HotdeployClassLoader(
                originalClassLoader, namingConvention);
        Thread.currentThread().setContextClassLoader(temporalClassLoader);

        GenerateDdlCommand generateDdlCommand = new GenerateDdlCommand();
        generateDdlCommand.setClasspathDir(new File("src/main/webapp/WEB-INF/classes"));
        generateDdlCommand.setRootPackageName("net.unit8.sastruts.oauth.example");
        generateDdlCommand.setIgnoreEntityClassNamePattern("(ResourceOwner)");
        generateDdlCommand.execute();

        MigrateCommand migrateCommand = new MigrateCommand();
        migrateCommand.setClasspathDir(new File("src/main/webapp/WEB-INF/classes"));
        migrateCommand.setRootPackageName("net.unit8.sastruts.oauth.example");
        migrateCommand.setIgnoreEntityClassNamePattern("(ResourceOwner|AccessTokenImpl|Oauth2TokenImpl|Oauth2VerifierImpl|RequestTokenImpl)");
        migrateCommand.execute();

        Thread.currentThread().setContextClassLoader(originalClassLoader);
    }
    public static void main(String[] args) throws Exception {
        String appBase = new File("src/main/webapp").getAbsolutePath();
        Integer port = 8093;

        String contextPath = "";

        startDatabase();
        migrateSchema();

        Tomcat tomcat = new Tomcat();
        tomcat.setPort(port);

        tomcat.setBaseDir(".");
        tomcat.getHost().setAppBase(appBase);

        StandardServer server = (StandardServer) tomcat.getServer();
        AprLifecycleListener listener = new AprLifecycleListener();
        server.addLifecycleListener(listener);

        Context context = tomcat.addWebapp(contextPath, appBase);
        tomcat.start();
        tomcat.getServer().await();
    }
}
