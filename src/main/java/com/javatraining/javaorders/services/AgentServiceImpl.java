package com.javatraining.javaorders.services;

import com.javatraining.javaorders.models.Agent;
import com.javatraining.javaorders.repositories.AgentRepository;
import com.javatraining.javaorders.services.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "agentService")
public class AgentServiceImpl implements AgentService {

    @Autowired
    AgentRepository agentrepos;

    public Agent save(Agent agent) {
        return agentrepos.save(agent);
    }
}
