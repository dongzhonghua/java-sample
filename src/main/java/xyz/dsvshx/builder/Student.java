package xyz.dsvshx.builder;

import lombok.Builder;
import lombok.Data;

/**
 * @author dongzhonghua
 * Created on 2021-01-27
 */
@Builder
@Data
public class Student {
    private String name;
    private String sex;
}
