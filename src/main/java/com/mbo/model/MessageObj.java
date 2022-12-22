package com.mbo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageObj implements Serializable {

    static final long serialVersionUID = -1840413283450911036L;

    private UUID id;
    private String message;

}
