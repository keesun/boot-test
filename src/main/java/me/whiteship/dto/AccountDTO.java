package me.whiteship.dto;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;

/**
 * @author Keeun Baik
 */
public class AccountDTO {

    @Data
    public static class Request {
        @NotBlank
        @Size(min = 6)
        private String username;
        @NotBlank
        private String password;
    }

    @Data
    public static class Response {
        private int id;
        private String username;
    }

    @Data
    public static class Update {
        private String username;
        private String password;
    }

}
