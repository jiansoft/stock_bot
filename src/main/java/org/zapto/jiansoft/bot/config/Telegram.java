package org.zapto.jiansoft.bot.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@NoArgsConstructor
@Setter
@Getter
public class Telegram {

    @JsonProperty("token")
    private String token;

    @JsonProperty("allowed")
    private Map<Long, String> allowed;

    @Override
    public String toString() {
        return "Telegram{" +
                "token=" + token +
                ", allowed='" + allowed.toString() + '\'' +
                '}';
    }
}
