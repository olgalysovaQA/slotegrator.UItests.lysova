package Config;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:config.properties"})
public interface ProjectConfig extends Config {

    @Key("password")
    String password();

    @Key("username")
    String username();

    @Key("url")
    String url();

}
