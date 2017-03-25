package kwang.experimentals.jersey.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.wadl.internal.WadlResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

/**
 * @author kevin.wang.cy@gmail.com
 */

@Configuration
@ApplicationPath("/api")
public class JerseyAPIConfiguration extends ResourceConfig {
    private static final Logger LOG = LoggerFactory.getLogger(JerseyAPIConfiguration.class);

    @Autowired
    public JerseyAPIConfiguration(ObjectMapper objectMapper) {

        this.register(WadlResource.class);

        // register endpoints
        packages("kwang.experimentals.jersey.resource");

        // register jackson for json
        register(new ObjectMapperContextResolver(objectMapper));
    }

    @Provider
    public static class ObjectMapperContextResolver implements ContextResolver<ObjectMapper> {

        private final ObjectMapper mapper;

        public ObjectMapperContextResolver(ObjectMapper mapper) {
            this.mapper = mapper;

            mapper.findAndRegisterModules();

            mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

            mapper.enable(JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN);
            mapper.enable(SerializationFeature.INDENT_OUTPUT, SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, SerializationFeature.CLOSE_CLOSEABLE);
            mapper.disable(SerializationFeature.WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS, /*SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,*/ SerializationFeature.WRITE_NULL_MAP_VALUES, SerializationFeature.FAIL_ON_EMPTY_BEANS);
        }

        @Override
        public ObjectMapper getContext(Class<?> type) {
            return mapper;
        }
    }
}