package com.javatraining.javaorders.services;

import com.javatraining.javaorders.models.Agent;

public interface AgentService {

    Agent findAgentById(Long agentId);

    Agent save(Agent agent);
}
