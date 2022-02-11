package org.example.oauth.pojo.playlist;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Setter
@Jacksonized
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Tracks {

    @JsonProperty("href")
    private String href;
    @JsonProperty("items")
    private List<Object> items = null;
    @JsonProperty("limit")
    private Integer limit;
    @JsonProperty("next")
    private Object next;
    @JsonProperty("offset")
    private Integer offset;
    @JsonProperty("previous")
    private Object previous;
    @JsonProperty("total")
    private Integer total;
}
