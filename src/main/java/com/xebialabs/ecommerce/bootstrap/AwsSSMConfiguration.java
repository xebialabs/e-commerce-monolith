package com.xebialabs.ecommerce.bootstrap;

import com.amazonaws.services.simplesystemsmanagement.AWSSimpleSystemsManagement;
import com.amazonaws.services.simplesystemsmanagement.AWSSimpleSystemsManagementClientBuilder;
import com.amazonaws.services.simplesystemsmanagement.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.bootstrap.config.PropertySourceLocator;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;

import java.util.HashMap;
import java.util.Map;

@Configuration
// TODO: To be replaced with a constant once change has been made to https://github.com/jhipster/jhipster
@Profile("aws")
public class AwsSSMConfiguration implements PropertySourceLocator {
    private final Logger log = LoggerFactory.getLogger(AwsSSMConfiguration.class);

    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${cloud.aws.stack.name}")
    private String stackName;

    @Override
    public PropertySource<?> locate(Environment environment) {
        log.info("Retrieving configuration from AWS Simple Systems Management(SSM)");
        AWSSimpleSystemsManagement awsClient = AWSSimpleSystemsManagementClientBuilder.defaultClient();

        final String pathPrefix = String.format("/%s/%s/", stackName, applicationName);

        GetParametersByPathResult getParameterResults = awsClient.getParametersByPath(new GetParametersByPathRequest()
            .withPath(pathPrefix)
            .withRecursive(true)
            .withWithDecryption(true));
        Map<String, Object> config = new HashMap<>();

        getParameterResults.getParameters()
            .forEach(parameter -> {
                final String paramName = parameter.getName().replaceFirst(pathPrefix, "");
                log.debug("Found parameter within SSM : {}", paramName);
                config.put(paramName, parameter.getValue());
            });

        return new MapPropertySource(this.getClass().getCanonicalName(), config);
    }
}
