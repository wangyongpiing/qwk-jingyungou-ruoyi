package com.ruoyi.web.controller.tool;
/**
 * @Description:
 * @author cjf
 * @date 2023-08-02
 */

import cn.hutool.core.util.RandomUtil;
import com.ruoyi.common.utils.StringUtils;
import org.apache.http.client.utils.URLEncodedUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

/**
 * @author cjf
 * @date 2023/8/2
 */
@SpringBootTest
@DisplayName("单元测试类")
public class TestDemo {

    @Test
    public void test1() {


    }

    private List<Integer> getIdList(){
        ArrayList<Integer> integers = new ArrayList<>();
        for (int i = 1; i <= 13 ; i++) {
            integers.add(i);
        }
        return integers;
    }


}
