package com.reminiscence.reminiscence;

import com.reminiscence.reminiscence.account.AccountController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class testAccountController {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    AccountController accountController;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
    }

    @Test
    public void testAccountCreation() throws Exception {
        String username = "test";
        String password = "1234";
        String date = "2019-05-14";

        mockMvc.perform(MockMvcRequestBuilders.post("/signup")
                .param("username", username)
                .param("password", password)
                .param("date", date))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/home"));
    }

    @Test
    public void testAccountCreationUsernameTaken() throws Exception {
        String username = "test";
        String password = "1234";
        String date = "2019-05-14";

        mockMvc.perform(MockMvcRequestBuilders.post("/signup")
                .param("username", username)
                .param("password", password)
                .param("date", date))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/home"));
        String username2 = "test";
        String password2 = "1234";
        String date2 = "2019-05-14";

        try {
            mockMvc.perform(MockMvcRequestBuilders.post("/signup")
                    .param("username", username2)
                    .param("password", password2)
                    .param("date", date2))
                    .andExpect(status().isInternalServerError())
                    .andExpect(redirectedUrl("/home"));
        } catch (Exception E) {
            System.out.println("grats");
        }
        // Need to test that if a user is created with the same name, everything hard stops
    }

    @Test
    public void testRedirect() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/home"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
        mockMvc.perform(MockMvcRequestBuilders.get("/home/entry"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
        mockMvc.perform(MockMvcRequestBuilders.get("/perform_logout"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"));
    }

    ;
}
