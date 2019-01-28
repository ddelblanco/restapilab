package com.diegodelblanco.restapilab.config;

import com.diegodelblanco.restapilab.controller.JerseyContactController;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfig extends ResourceConfig
{
    public JerseyConfig()
    {
        register(JerseyContactController.class);
    }
}
