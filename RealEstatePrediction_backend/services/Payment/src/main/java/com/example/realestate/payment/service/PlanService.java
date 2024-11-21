package com.example.realestate.payment.service;

import com.example.realestate.payment.dto.PlanDTO;
import com.example.realestate.payment.entity.Plan;
import com.example.realestate.payment.mapper.PlanMapper;
import com.example.realestate.payment.repository.PlanRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlanService {
    private final PlanRepository planRepository;
    private final PlanMapper planMapper;

    public PlanService(PlanRepository planRepository, PlanMapper planMapper) {
        this.planRepository = planRepository;
        this.planMapper = planMapper;
    }

    public PlanDTO getPlanByName(String name) {
        Plan plan = planRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Plan not found with name: " + name));
        return planMapper.toDTO(plan);
    }

    public List<PlanDTO> planList() {
        return planRepository.findAll().stream()
                .map(planMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PlanDTO findById(Long id) {
        Plan plan = planRepository.findById(String.valueOf(id))
                .orElseThrow(() -> new RuntimeException("Plan not found with id: " + id));
        return planMapper.toDTO(plan);
    }

    public PlanDTO createOrUpdate(PlanDTO planDTO) {
        Plan plan = planMapper.toEntity(planDTO);
        return planMapper.toDTO(planRepository.save(plan));
    }

    public boolean existsById(Long id) {
        return planRepository.existsById(String.valueOf(id));
    }
}
