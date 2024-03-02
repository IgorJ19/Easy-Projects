package com.example.zadanie1;

import org.springframework.context.annotation.Configuration;

@Configuration
//@ConstructorProperties("task")
public class WlasciwosciKonfiguracjiZadan {
    private boolean allowMultipleTaskFromTemplate;

    public boolean isAllowMultipleTaskFromTemplate() {
        return allowMultipleTaskFromTemplate;
    }

    public void setAllowMultipleTaskFromTemplate(boolean allowMultipleTaskFromTemplate) {
        this.allowMultipleTaskFromTemplate = allowMultipleTaskFromTemplate;
    }
}
