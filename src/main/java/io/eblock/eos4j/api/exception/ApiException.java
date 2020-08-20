package io.eblock.eos4j.api.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author espritblock http://eblock.io
 */
public class ApiException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private ApiError error;

    public ApiException(ApiError apiError) {
        this.error = apiError;
    }

    public ApiException(Throwable cause) {
        super(cause);
    }

    public ApiError getError() {
        return error;
    }

    public void setError(ApiError error) {
        this.error = error;
    }

    @Override
    public String getMessage() {
        if (error != null) {
            ObjectMapper mapper = new ObjectMapper();
            String s = "";
            try {
                s = mapper.writeValueAsString(error);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return error.getMessage() + ": " + s;
        }
        return super.getMessage();
    }
}
