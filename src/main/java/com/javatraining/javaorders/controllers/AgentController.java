package com.javatraining.javaorders.controllers;

import com.javatraining.javaorders.models.Agent;
import com.javatraining.javaorders.services.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/agents")
public class AgentController
{

    @Autowired
    AgentService agServ;

// GET agent by id
    // /agents/agent/{id}
    @GetMapping(value = "agent/{agentid}", produces = "application/json")
    public ResponseEntity<?> getAgentById(@PathVariable Long agentid)
    {
        Agent a = agServ.findAgentById(agentid);

        return new ResponseEntity<>(a, HttpStatus.OK);
    }

    //Delete

    //Post

    //Update

}
