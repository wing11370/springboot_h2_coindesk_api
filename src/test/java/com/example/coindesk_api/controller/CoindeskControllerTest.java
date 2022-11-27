package com.example.coindesk_api.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class CoindeskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void beforeEach() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/")).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void create() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/{currency}", "usd"))
                .andExpect(MockMvcResultMatchers.status().isOk());
        RequestBuilder usdRequestBuilder = MockMvcRequestBuilders
                .get("/{currency}/{id}", "usd", 2);
        mockMvc.perform(usdRequestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.equalTo(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.equalTo("美金")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.currency", Matchers.equalTo("usd")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.date", Matchers.notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.rate", Matchers.notNullValue()))
                .andDo(MockMvcResultHandlers.print());

        mockMvc.perform(MockMvcRequestBuilders.post("/{currency}", "eur"))
                .andExpect(MockMvcResultMatchers.status().isOk());
        RequestBuilder eurRequestBuilder = MockMvcRequestBuilders
                .get("/{currency}/{id}", "eur", 2);
        mockMvc.perform(eurRequestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.equalTo(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.equalTo("歐元")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.currency", Matchers.equalTo("eur")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.date", Matchers.notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.rate", Matchers.notNullValue()))
                .andDo(MockMvcResultHandlers.print());

        mockMvc.perform(MockMvcRequestBuilders.post("/{currency}", "gbp"))
                .andExpect(MockMvcResultMatchers.status().isOk());
        RequestBuilder gbpRequestBuilder = MockMvcRequestBuilders
                .get("/{currency}/{id}", "gbp", 2);
        mockMvc.perform(gbpRequestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.equalTo(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.equalTo("英鎊")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.currency", Matchers.equalTo("gbp")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.date", Matchers.notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.rate", Matchers.notNullValue()))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void findAll() throws Exception {

        RequestBuilder usdRequestBuilder = MockMvcRequestBuilders
                .get("/{currency}", "usd");
        mockMvc.perform(usdRequestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
        RequestBuilder eurRequestBuilder = MockMvcRequestBuilders
                .get("/{currency}", "eur");
        mockMvc.perform(eurRequestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
        RequestBuilder gbpRequestBuilder = MockMvcRequestBuilders
                .get("/{currency}", "gbp");
        mockMvc.perform(gbpRequestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void findById() throws Exception {
        RequestBuilder usdRequestBuilder = MockMvcRequestBuilders
                .get("/{currency}/{id}", "usd", 1);
        mockMvc.perform(usdRequestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.equalTo(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.equalTo("美金")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.currency", Matchers.equalTo("usd")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.date", Matchers.notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.rate", Matchers.notNullValue()))
                .andDo(MockMvcResultHandlers.print());
        RequestBuilder eurRequestBuilder = MockMvcRequestBuilders
                .get("/{currency}/{id}", "eur", 1);
        mockMvc.perform(eurRequestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.equalTo(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.equalTo("歐元")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.currency", Matchers.equalTo("eur")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.date", Matchers.notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.rate", Matchers.notNullValue()))
                .andDo(MockMvcResultHandlers.print());
        RequestBuilder gbpRequestBuilder = MockMvcRequestBuilders
                .get("/{currency}/{id}", "gbp", 1);
        mockMvc.perform(gbpRequestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.equalTo(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.equalTo("英鎊")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.currency", Matchers.equalTo("gbp")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.date", Matchers.notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.rate", Matchers.notNullValue()))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void update() throws Exception {

        // mockMvc.perform(MockMvcRequestBuilders.post("/{currency}", "usd"))
        // .andExpect(MockMvcResultMatchers.status().isOk());

        String usdDate = "20221126";
        Double usdRate = 1.0;
        RequestBuilder usdRequestBuilder = MockMvcRequestBuilders
                .patch("/{currency}/{id}", "usd", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"date\": \"" + usdDate + "\"," +
                        "    \"rate\": " + usdRate +
                        "}");
        mockMvc.perform(usdRequestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.equalTo(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.equalTo("美金")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.currency", Matchers.equalTo("usd")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.date", Matchers.equalTo(usdDate)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.rate", Matchers.equalTo(usdRate)))
                .andDo(MockMvcResultHandlers.print());

        // mockMvc.perform(MockMvcRequestBuilders.post("/{currency}", "eur"))
        // .andExpect(MockMvcResultMatchers.status().isOk());
        String eurDate = "20221127";
        Double eurRate = 2.0;
        RequestBuilder eurRequestBuilder = MockMvcRequestBuilders
                .patch("/{currency}/{id}", "eur", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"date\": \"" + eurDate + "\"," +
                        "    \"rate\": " + eurRate +
                        "}");
        mockMvc.perform(eurRequestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.equalTo(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.equalTo("歐元")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.currency", Matchers.equalTo("eur")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.date", Matchers.equalTo(eurDate)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.rate", Matchers.equalTo(eurRate)))
                .andDo(MockMvcResultHandlers.print());

        // mockMvc.perform(MockMvcRequestBuilders.post("/{currency}", "gbp"))
        // .andExpect(MockMvcResultMatchers.status().isOk());
        String gbpDate = "20221128";
        Double gbpRate = 3.0;
        RequestBuilder gbpRequestBuilder = MockMvcRequestBuilders
                .patch("/{currency}/{id}", "gbp", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"date\": \"" + gbpDate + "\"," +
                        "    \"rate\": " + gbpRate +
                        "}");
        mockMvc.perform(gbpRequestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.equalTo(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.equalTo("英鎊")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.currency", Matchers.equalTo("gbp")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.date", Matchers.equalTo(gbpDate)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.rate", Matchers.equalTo(gbpRate)))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void delete() throws Exception {

        // mockMvc.perform(MockMvcRequestBuilders.post("/{currency}", "usd"))
        // .andExpect(MockMvcResultMatchers.status().isOk());

        RequestBuilder usdRequestBuilder = MockMvcRequestBuilders
                .delete("/{currency}/{id}", "usd", 1);
        mockMvc.perform(usdRequestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        // mockMvc.perform(MockMvcRequestBuilders.post("/{currency}", "eur"))
        // .andExpect(MockMvcResultMatchers.status().isOk());
        RequestBuilder eurRequestBuilder = MockMvcRequestBuilders
                .delete("/{currency}/{id}", "eur", 1);
        mockMvc.perform(eurRequestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        // mockMvc.perform(MockMvcRequestBuilders.post("/{currency}", "gbp"))
        // .andExpect(MockMvcResultMatchers.status().isOk());
        RequestBuilder gbpRequestBuilder = MockMvcRequestBuilders
                .delete("/{currency}/{id}", "gbp", 1);
        mockMvc.perform(gbpRequestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void fetchCoindesk() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/fetchCoindesk");
        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void dataTranform() throws Exception {
        // mockMvc.perform(MockMvcRequestBuilders.post("/{currency}", "usd"))
        // .andExpect(MockMvcResultMatchers.status().isOk());

        String usdContentDate = "2022/11/27 10:39:00";
        double usdContentRate = 16062.6383;
        String usdContent = "{\n" +
                "    \"id\": 1," +
                "    \"date\": \"" + usdContentDate + "\"," +
                "    \"rate\": " + usdContentRate +
                "}";
        RequestBuilder usdRequestBuilder = MockMvcRequestBuilders
                .post("/dataTransform/{currency}", "usd")
                .contentType(MediaType.APPLICATION_JSON)
                .content(usdContent);
        mockMvc.perform(usdRequestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.equalTo(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.equalTo("美金")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.currency", Matchers.equalTo("usd")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.date", Matchers.equalTo(usdContentDate)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.rate", Matchers.equalTo(usdContentRate)))
                .andDo(MockMvcResultHandlers.print());

        // mockMvc.perform(MockMvcRequestBuilders.post("/{currency}", "eur"))
        // .andExpect(MockMvcResultMatchers.status().isOk());

        String eurContentDate = "2022/11/27 10:39:00";
        double eurContentRate = 16062.6383;
        String eurContent = "{\n" +
                "    \"id\": 1," +
                "    \"date\": \"" + eurContentDate + "\"," +
                "    \"rate\": " + eurContentRate +
                "}";
        RequestBuilder eurRequestBuilder = MockMvcRequestBuilders
                .post("/dataTransform/{currency}", "eur")
                .contentType(MediaType.APPLICATION_JSON)
                .content(eurContent);
        mockMvc.perform(eurRequestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.equalTo(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.equalTo("歐元")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.currency", Matchers.equalTo("eur")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.date", Matchers.equalTo(eurContentDate)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.rate", Matchers.equalTo(eurContentRate)))
                .andDo(MockMvcResultHandlers.print());

        // mockMvc.perform(MockMvcRequestBuilders.post("/{currency}", "gbp"))
        // .andExpect(MockMvcResultMatchers.status().isOk());
        String gbpContentDate = "2022/11/27 10:39:00";
        double gbpContentRate = 16062.6383;
        String gbpContent = "{\n" +
                "    \"id\": 1," +
                "    \"date\": \"" + gbpContentDate + "\"," +
                "    \"rate\": " + gbpContentRate +
                "}";
        RequestBuilder gbpRequestBuilder = MockMvcRequestBuilders
                .post("/dataTransform/{currency}", "gbp")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gbpContent);
        mockMvc.perform(gbpRequestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.equalTo(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.equalTo("英鎊")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.currency", Matchers.equalTo("gbp")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.date", Matchers.equalTo(gbpContentDate)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.rate", Matchers.equalTo(gbpContentRate)))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

}
