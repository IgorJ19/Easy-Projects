package com.epam.rd.autotasks;

import org.springframework.context.annotation.Configuration;


@Configuration
public class ConfigurationProperies {

    public ConfigurationProperies(){}
    private boolean allowMultipleTaskFromTemplate;

    public boolean isAllowMultipleTaskFromTemplate() {
        return allowMultipleTaskFromTemplate;
    }

    public void setAllowMultipleTaskFromTemplate(boolean allowMultipleTaskFromTemplate) {
        this.allowMultipleTaskFromTemplate = allowMultipleTaskFromTemplate;
    }
}
