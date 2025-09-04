package br.edu.utfpr.pb.tcc.server.shared;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GenericResponse {

    private String message;

    public static GenericResponse of(String message) {
        return new GenericResponse(message);
    }
}
