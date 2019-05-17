package com.reminiscence.reminiscence.userAccount;

import com.reminiscence.reminiscence.account.AccountController;
import com.reminiscence.reminiscence.account.AccountRepo;
import com.reminiscence.reminiscence.account.UserAccount;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.*;
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
    public void testDefaultConstructor() {
        UserAccount user = new UserAccount();
        String actualUsername = user.getUsername();
        String actualPassword = user.getPassword();
        String actualJoinDate = user.getJoinDate();

        assertNull("Username had an unexpected value", actualUsername);
        assertNull("Password had an unexpected value", actualPassword);
        assertNull("Joindate had an unexpected value", actualJoinDate);
    }

    @Test
    public void testLoadedConstructor() {
        UserAccount user = new UserAccount("testUser", "testPassword", this.encoder, "2019-05-14");
        String actualUsername = user.getUsername();
        String actualPassword = user.getPassword();
        String actualJoinDate = user.getJoinDate();

        assertEquals("Username had an unexpected value", "testUser", actualUsername);
        assertNotEquals("Password had an unexpected value", "testPassword", actualPassword);
        assertEquals("Joindate had an unexpected value", "2019-05-14", actualJoinDate);
    }

    @Test
    public void testMVCAccountCreation() throws Exception {
        String username = "testy";
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
            System.out.println("Exception successfully thrown");
        }
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
}
