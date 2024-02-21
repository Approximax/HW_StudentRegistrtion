package demoqa.config;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:config/links.properties")
public interface WebLinks extends Config {

    @Key("base.url")
    String baseUrl();

    @Key("selenoid.url")
    String selenoidUrl();
}
