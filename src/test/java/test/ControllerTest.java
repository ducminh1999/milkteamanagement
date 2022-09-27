/**
 * (C) Copyright of Fresher FPT Software Academy. All Rights Reserved
 *
 * @author Minh
 * @date Jan 12, 2022
 * @version 1.0
 */
package test;

import org.aspectj.lang.annotation.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import junit.framework.Assert;
import spring.mvc.controllers.admin.UserController;
import spring.mvc.services.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-severlet.xml"})
public class ControllerTest {
	@Before(value = "")
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }
 
    private MockMvc mockMvc;
     
    @InjectMocks
    private UserController userController;
     
    @Mock
    private UserService userService;
    
    @Test
    public void testLoginView() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/accounts/login"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.view().name("accounts/login"))
            .andExpect(MockMvcResultMatchers.forwardedUrl("accounts/login"))
            .andReturn();
        Assert.assertNotNull(result.getModelAndView());
    }
}

