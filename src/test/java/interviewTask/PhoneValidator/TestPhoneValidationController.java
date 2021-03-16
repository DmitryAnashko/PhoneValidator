package interviewTask.PhoneValidator;

import interviewTask.PhoneValidator.controllers.PhoneValidationController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


@RunWith(SpringJUnit4ClassRunner.class)
public class TestPhoneValidationController {
    private static final String COUNTRY_NAME = "[\"Latvia\"]";
    private static final String PHONE_NUMBER = "{\"phone\":\"+3717737156\"}";
    private static final String PHONE_NUMBER_WRONG_FORMAT = "{\"phone\":\"+3717737156a\"}";
    private static final String COUNTRY_NAME_WRONG_FORMAT = "[\"Wrong phone number format\"]";
    private static final String PHONE_NUMBER_NO_COUNTRY = "{\"phone\":\"+999999999\"}";
    private static final String COUNTRY_NAME_NO_COUNTRY = "[\"Country not found\"]";

    private MockMvc mockMvc;

    @InjectMocks
    private PhoneValidationController phoneValidationController;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(phoneValidationController)
                .build();
    }

    @Test
    public void testCorrect() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/phone")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(PHONE_NUMBER))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(COUNTRY_NAME));

    }

    @Test
    public void testWrongNumberFormat() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/phone")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(PHONE_NUMBER_WRONG_FORMAT))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(COUNTRY_NAME_WRONG_FORMAT));

    }

    @Test
    public void testCountryNotFound() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/phone")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(PHONE_NUMBER_NO_COUNTRY))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(COUNTRY_NAME_NO_COUNTRY));

    }


}
