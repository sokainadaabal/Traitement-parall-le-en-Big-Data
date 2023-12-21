package org.sid.customerqueryside.configs;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;

import org.axonframework.serialization.xml.XStreamSerializer;
import org.springframework.context.annotation.Bean;

public class AxonConfig {

    @Bean
    public XStream xStream() {
        XStream xStream = new XStream();
        xStream.allowTypesByWildcard(new String[] {
                "org.sid.coreapi.**","org.sid.customerqueryside.**"
        });
        return xStream;
    }

}
