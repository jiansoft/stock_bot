package org.zapto.jiansoft.bot.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Rpc {
    public Rpc(){

    }

    @JsonProperty("go_service")
    private Grpc goService ;

    @JsonProperty("rust_service")
    private Grpc rustService ;
}
