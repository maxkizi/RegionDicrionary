package org.maxkizi.regiondictionary.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Response {
    @JsonProperty(value = "messages")
    private List<String> messages;

    @Override
    public String toString() {
        return "Response{" +
                "messages=" + messages +
                '}';
    }
}

