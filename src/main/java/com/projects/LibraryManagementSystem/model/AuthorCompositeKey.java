package com.projects.LibraryManagementSystem.model;

import jakarta.websocket.server.ServerEndpoint;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class AuthorCompositeKey {

    private String id;
    private String email;
}
