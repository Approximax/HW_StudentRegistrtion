package demoqa.config;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:config/links.properties")
public interface WebLinks extends Config {

    @Key("base.url")
    @DefaultValue("https://demoqa.com/")
    String baseUrl();

    @Key("selenoid.url")
    @DefaultValue("https://selenoid.autotests.cloud/#/")
    String selenoidUrl();
}
