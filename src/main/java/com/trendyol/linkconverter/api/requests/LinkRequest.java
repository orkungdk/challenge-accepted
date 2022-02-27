package com.trendyol.linkconverter.api.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author orkungedik
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Validated
public class LinkRequest {

    @NotNull
    @NotEmpty
    private String link;

}
