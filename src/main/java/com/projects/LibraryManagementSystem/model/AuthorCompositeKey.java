package com.projects.LibraryManagementSystem.model;

import jakarta.websocket.server.ServerEndpoint;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class AuthorCompositeKey {

    private String id;
    private String email;
}
