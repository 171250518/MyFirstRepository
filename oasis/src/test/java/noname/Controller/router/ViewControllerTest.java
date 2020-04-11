package noname.Controller.router;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class ViewControllerTest {
    private ViewController viewController;

    @Before
    public void setUp() {
        viewController = new ViewController();
    }

    @After
    public void tearDown() {
        viewController = null;
    }

    @Test
    public void login() throws Exception {
        MockMvc mockMvc = standaloneSetup(viewController).build();
        mockMvc.perform(get("/login"))
                .andExpect(view().name("/Account/login"));
    }

    @Test
    public void signUp() throws Exception  {
        MockMvc mockMvc = standaloneSetup(viewController).build();
        mockMvc.perform(get("/signUp"))
                .andExpect(view().name("Account/signUp"));
    }

    @Test
    public void index() throws Exception  {
        MockMvc mockMvc = standaloneSetup(viewController).build();
        mockMvc.perform(get("/index"))
                .andExpect(view().name("Index/index"));
    }

    @Test
    public void paper() throws Exception {
        MockMvc mockMvc = standaloneSetup(viewController).build();
        mockMvc.perform(get("/paper"))
                .andExpect(view().name("Paper/paper"));
    }

    @Test
    public void searchResult() throws Exception {
        MockMvc mockMvc = standaloneSetup(viewController).build();
        mockMvc.perform(get("/searchResult"))
                .andExpect(view().name("SearchResult/searchResult"));
    }
}