package org.zapto.jiansoft.bot.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class System {

    @JsonProperty("grpc_use_port")
    public int grpcUsePort;

    @JsonProperty("ssl_cert_file")
    public String sslCertFile;

    @JsonProperty("ssl_key_file")
    public String sslKeyFile;

    @Override
    public String toString() {
        return "System{" +
                "grpcUsePort=" + grpcUsePort +
                ", sslCertFile='" + sslCertFile + '\'' +
                ", sslKeyFile='" + sslKeyFile + '\'' +
                '}';
    }
}
