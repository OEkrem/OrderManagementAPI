package com.oekrem.SpringMVCBackEnd.email;

import lombok.Builder;

@Builder
public record EMailRequest(
        String to,
        String subject,
        String content
) {
}
