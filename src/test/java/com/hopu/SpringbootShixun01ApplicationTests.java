package com.hopu;

import com.hopu.entity.Factory_c;
import com.hopu.service.Factory_cService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SpringbootShixun01ApplicationTests {
@Autowired
private Factory_cService factory_cService;
    @Test
    void contextLoads() {
        List<Factory_c> lis = factory_cService.findfactoryAll();
        for (Factory_c li : lis) {
            System.out.println(li);
        }
    }

}
