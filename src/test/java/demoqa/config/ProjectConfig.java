package demoqa.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources("classpath:config/${environment}.properties")
public interface ProjectConfig extends Config {

    @Key("first")
    String firstName();

    @Key("last")
    String lastName();
}
