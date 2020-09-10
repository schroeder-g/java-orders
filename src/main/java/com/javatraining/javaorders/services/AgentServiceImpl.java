package com.javatraining.javaorders.services;

import com.javatraining.javaorders.models.Agent;
import com.javatraining.javaorders.repositories.AgentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service(value = "agentService")
public class AgentServiceImpl implements AgentService {

    @Autowired
    AgentsRepository agentrepos;

    @Override
    public Agent findAgentById(Long agentId) throws
            EntityNotFoundException
    {
        return agentrepos.findById(agentId)
                .orElseThrow(() -> new EntityNotFoundException("Agent " + agentId + " not found!"));
    }

    public Agent save(Agent agent) {
        return agentrepos.save(agent);
    }
}
