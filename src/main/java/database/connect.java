package database;

import io.requery.cache.EntityCacheBuilder;
import io.requery.meta.EntityModel;
import io.requery.sql.Configuration;
import io.requery.sql.ConfigurationBuilder;
import org.sqlite.SQLiteConfig;
import org.sqlite.SQLiteDataSource;

import sqlite.connect.SQLiteConnector;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class connect {


/*   private DataSource createDataSource() {
        SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setUrl("jdbc:sqlite:jdbc:sqlite:/media/sysadmin/projects/Java/0Learn/advisor/db/demo.db");
        SQLiteConfig config = new SQLiteConfig();
        config.setDateClass("TEXT");
        dataSource.setConfig(config);
        try (Statement statement = dataSource.getConnection().createStatement()) {
            statement.execute("PRAGMA foreign_keys = ON");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return dataSource;
    }



    DataSource dataSource = createDataSource();
    //EntityModel model = Models.ALL_STOCKS;
    EntityModel model = new Stock();
    Configuration configuration = new ConfigurationBuilder(dataSource, model)
            .useDefaultLogging()
            .setEntityCache(new EntityCacheBuilder(model)
                    .useReferenceCache(true)
                    .useSerializableCache(true)
                    //.useCacheManager(cacheManager)
                    .build())
            .build();
   // data = new EntityDataStore<>(configuration);*/
}
