package com.ame;

import lombok.*;

import java.io.Serializable;

/**
 * FileName: Hello
 * Author:   AmeGong
 * Date:     2020/12/30 19:23
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Hello implements Serializable {
    private String message;
    private String description;
}
