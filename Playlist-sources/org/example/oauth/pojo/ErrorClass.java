
package org.example.oauth.pojo;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "error"
})
@Generated("jsonschema2pojo")
public class ErrorClass {

    @JsonProperty("error")
    private Error error;

    @JsonProperty("error")
    public Error getError() {
        return error;
    }

    @JsonProperty("error")
    public void setError(Error error) {
        this.error = error;
    }

    public ErrorClass withError(Error error) {
        this.error = error;
        return this;
    }

}
