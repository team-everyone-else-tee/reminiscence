package com.reminiscence.reminiscence.journal;

import com.reminiscence.reminiscence.account.UserAccount;
import org.hamcrest.Matchers;
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

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EntryControllerTest {

    private UserAccount user1;

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    EntryController entryController;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    EntryRepo entryRepo;

    @Before
    public void setUpNotSignedIn() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
        user1 = new UserAccount("TEST", "password", this.encoder, "2019-05-16");
    }

    @Test
    public void testNotSignedInRedirect() throws Exception{
        String body = "test journal entry.";
        String date = "2019-05-16";

        mockMvc.perform(MockMvcRequestBuilders.post("/entry")
                .param("body", body)
                .param("date", date))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    }

    @Test
    @WithMockUser
    public void testSignedInRedirect() {
    }

    @Test
    public void testSaveEntry() throws Exception{
        Entry entry1 = new Entry();
        entry1.setBody("Save entry test One.");
        entryRepo.save(entry1);

        Entry entry2 = new Entry();
        entry2.setBody("Save entry test Two.");
        entryRepo.save(entry2);

        Entry entry3 = new Entry();
        entry3.setBody("Save entry test Three.");
        entryRepo.save(entry3);


        mockMvc.perform(MockMvcRequestBuilders.get("/home"))
                .andExpect(content().string(Matchers.containsString("Save entry test One.")))
                .andExpect(content().string(Matchers.containsString("Save entry test Two.")))
                .andExpect(content().string(Matchers.containsString("Save entry test Three.")));

    }

}