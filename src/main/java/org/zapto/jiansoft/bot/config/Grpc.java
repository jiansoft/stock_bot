package org.zapto.jiansoft.bot.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Grpc {

    @JsonProperty("target")
    private String target;

    @JsonProperty("tls_cert_file")
    private String tlsCertFile;

    @JsonProperty("tls_key_file")
    private String tlsKeyFile;

    @JsonProperty("domain_name")
    private String domainName;

    public Grpc(){

    }

    @Override
    public String toString() {
        return "Grpc{" +
                "target='" + target + '\'' +
                ", tlsCertFile='" + tlsCertFile + '\'' +
                ", tlsKeyFile='" + tlsKeyFile + '\'' +
                ", domainName='" + domainName + '\'' +
                '}';
    }
}
