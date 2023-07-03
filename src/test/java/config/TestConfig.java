package config;

import org.aeonbits.owner.Config;

    @Config.Sources({
            "classpath:config/app.properties"
    })

    public interface TestConfig extends Config {

        @Config.Key("login")
        String getLogin();

        @Key("password")
        String getPassword();

        @Key("baseUrl")
        String getBaseUrl();

    }

