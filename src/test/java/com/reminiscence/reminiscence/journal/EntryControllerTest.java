package com.reminiscence.reminiscence.journal;

import com.reminiscence.reminiscence.account.AccountRepo;
import com.reminiscence.reminiscence.account.UserAccount;
import org.apache.catalina.User;
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

import java.nio.channels.AcceptPendingException;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EntryControllerTest {

    private UserAccount user1;

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    AccountRepo accountRepo;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    EntryController entryController;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
        user1 = new UserAccount("TEST", "password", this.encoder, "2019-05-16");
    }

    @Test
    @WithMockUser
    public void testCreateEntry() throws Exception{
        String body = "test journal entry.";
        String date = "2019-05-16";

        mockMvc.perform(MockMvcRequestBuilders.post("/entry")
                .param("body", body)
                .param("date", date))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/home"));
    }

    @Test
    public void testViewSingleEntry() throws Exception {
        String body = "test journal entry.";
        String date = "2019-05-16";

        mockMvc.perform(MockMvcRequestBuilders.post("/entry/{id}")
                .param("body", body)
                .param("date", date))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/home"));
    }

    @Test
    public void testUpdateEntry() throws Exception {
        String body = "test journal entry.";
        String date = "2019-05-16";

        mockMvc.perform(MockMvcRequestBuilders.post("/entry/{id}/update")
                .param("body", body)
                .param("date", date))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/home"));
    }

    @Test
    public void testDeleteEntry() throws Exception {
        String body = "test journal entry.";
        String date = "2019-05-16";

        mockMvc.perform(MockMvcRequestBuilders.post("/entry/{id}/update")
                .param("body", body)
                .param("date", date))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/home"));
    }
}