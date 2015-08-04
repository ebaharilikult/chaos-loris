/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.pivotal.strepsirrhini.chaosloris.servicebroker.provisioning;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.pivotal.strepsirrhini.chaosloris.servicebroker.AbstractControllerTest;
import org.junit.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public final class ProvisioningControllerTest extends AbstractControllerTest {

    @Test
    public void create() throws Exception {
        this.mockMvc.perform(
                put("/v2/service_instances/009b82a8-81ed-4a28-9430-ce10f6442b05")
                        .content(createPayload())
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.dashboard_url")
                        .value("https://10.128.10.4/dashboard/009b82a8-81ed-4a28-9430-ce10f6442b05"));
    }

    @Test
    public void testUpdate() throws Exception {
        this.mockMvc.perform(
                patch("/v2/service_instances/009b82a8-81ed-4a28-9430-ce10f6442b05")
                        .content(updatePayload())
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());
    }

    @Test
    public void testDelete() throws Exception {
        this.mockMvc.perform(
                delete("/v2/service_instances/009b82a8-81ed-4a28-9430-ce10f6442b05")
                        .param("service_id", "f6fe01b7-1e27-4857-961f-8451b1248ad1")
                        .param("plan_id", "03e17851-de4d-435c-beb2-6eb92a8c941d"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());
    }

    private String createPayload() throws JsonProcessingException {
        Map<String, String> m = new HashMap<>();
        m.put("organization_guid", "356f58e5-0abe-4674-972e-d156d2065a9b");
        m.put("plan_id", "03e17851-de4d-435c-beb2-6eb92a8c941d");
        m.put("service_id", "f6fe01b7-1e27-4857-961f-8451b1248ad1");
        m.put("space_guid", "a65bc9e3-edd6-472b-85df-3cc68d6d8705");

        return this.objectMapper.writeValueAsString(m);
    }

    private String updatePayload() throws JsonProcessingException {
        Map<String, ?> m = Collections.singletonMap("service_id",
                UUID.fromString("f6fe01b7-1e27-4857-961f-8451b1248ad1"));

        return this.objectMapper.writeValueAsString(m);
    }

}