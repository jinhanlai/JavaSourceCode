package reflection;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author laijinhan
 * @date 2020/9/22 11:37 下午
 */

@AllArgsConstructor
@Data
public class Student implements Serializable {
    private Integer id;
    private String name;
}
