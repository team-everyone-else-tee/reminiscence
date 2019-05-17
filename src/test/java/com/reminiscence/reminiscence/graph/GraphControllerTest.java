package com.reminiscence.reminiscence.graph;

import com.reminiscence.reminiscence.account.AccountController;
import com.reminiscence.reminiscence.account.AccountRepo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GraphControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    AccountController accountController;

    @Autowired
    AccountRepo accountRepo;

    @Autowired
    PasswordEncoder encoder;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
    }

    @Test
    @WithMockUser
    public void testPieGraph() {

        try {
            mockMvc.perform(MockMvcRequestBuilders.get("/pieGraph"))
                    .andExpect(status().is3xxRedirection())
                    .andExpect(view().name("pieGraph"))
                    .andExpect(view().name(containsString("pieGraph")))
                    .andExpect(model().size(6));
        } catch (Exception e) {
            System.out.println("I could not hit the Pie Graph Route");
            e.printStackTrace();
        }
    }


}