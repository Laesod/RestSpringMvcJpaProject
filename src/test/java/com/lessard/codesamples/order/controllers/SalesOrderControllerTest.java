package com.lessard.codesamples.order.controllers;


import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.lessard.codesamples.order.domain.SalesOrder;
import com.lessard.codesamples.order.services.SalesOrderService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
 * Unit test for simple OrderController.
 */

public class SalesOrderControllerTest {

    private MockMvc mockMvc;

    @Mock
    private SalesOrderService salesOrderService;

    private Date today = Calendar.getInstance().getTime();

    @Before
    public void setup() {

        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(new SalesOrderController(salesOrderService)).build();
        Mockito.when(salesOrderService.getSalesOrder(0)).
                thenReturn(new SalesOrder(0, "SalesOrder 0", today, new BigDecimal(10.00)));

        List<SalesOrder> salesOrderList = new ArrayList<SalesOrder>();

        salesOrderList.add(new SalesOrder(0, "SalesOrder 0", today, new BigDecimal(10.00)));
        salesOrderList.add(new SalesOrder(1, "SalesOrder 1", today, new BigDecimal(10.00)));
        salesOrderList.add(new SalesOrder(2, "SalesOrder 2", today, new BigDecimal(10.00)));

        Mockito.when(salesOrderService.getAllSalesOrder()).thenReturn(salesOrderList);
    }

    @Test
    public void testHello() throws Exception {

        mockMvc.perform(get("/hello")).andExpect(status().isOk())
                .andExpect(content().string("Hello World"));
    }


    @Test
    public void testGetSalesOrder() throws Exception {

        mockMvc.perform(get("/salesorders/0")).andExpect(status().isOk()).
                andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)).
                andExpect(jsonPath("$.id").value(new Integer(0))).
                andExpect(jsonPath("$.description").value("SalesOrder 0"));
    }

    @Test
    public void testGetSalesOrders() throws Exception {

        mockMvc.perform(get("/salesorders")).andExpect(status().isOk()).
                andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)).
                andExpect(jsonPath("$", hasSize(3))).
                andExpect(jsonPath("$.[0].id").value(new Integer(0))).
                andExpect(jsonPath("$.[0].description").value("SalesOrder 0")).
                andExpect(jsonPath("$.[1].id").value(new Integer(1))).
                andExpect(jsonPath("$.[1].description").value("SalesOrder 1")).
                andExpect(jsonPath("$.[2].id").value(new Integer(2))).
                andExpect(jsonPath("$.[2].description").value("SalesOrder 2"));
    }

    @Test
    public void testDeleteSalesOrder() throws Exception {

        mockMvc.perform(delete("/salesorders/0")).andExpect(status().isOk());
    }

    //@Test
    public void testCreateSalesOrder() throws Exception {

        String todayStr = today.toString();

        String postStr = "{\"id\": \"3\",\"description\":\"SalesOrder 3\", \"date\":" + todayStr + "}";

        mockMvc.perform(post("/salesorders").
                content(postStr).contentType(MediaType.APPLICATION_JSON)).
                andExpect(status().isCreated()).
                andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)).
                andExpect(jsonPath("$.id").value(new Integer(3))).
                andExpect(jsonPath("$.description").value("SalesOrder 3"));
    }

    //@Test
    public void testUpdateSalesOrder() throws Exception {

        String todayStr = today.toString();

        String putStr = "{\"id\": \"0\",\"description\":\"SalesOrder 3\", \"date\":" + todayStr + "}";

        mockMvc.perform(put("/salesorders").
                content(putStr).contentType(MediaType.APPLICATION_JSON)).
                andExpect(status().isOk()).
                andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)).
                andExpect(jsonPath("$.id").value(new Integer(0))).
                andExpect(jsonPath("$.description").value("SalesOrder 3"));
    }


}
