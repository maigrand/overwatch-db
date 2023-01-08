package com.maigrand.overwatchdb.service;

import com.maigrand.overwatchdb.entity.AimType;
import com.maigrand.overwatchdb.payload.AimTypeDetails;
import com.maigrand.overwatchdb.repository.AimTypeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AimTypeService {

    private final AimTypeRepository aimTypeRepository;

    public AimTypeService(AimTypeRepository aimTypeRepository) {
        this.aimTypeRepository = aimTypeRepository;
    }

    public List<AimType> findAll() {
        return aimTypeRepository.findAll();
    }

    public AimType findById(Integer id) {
        return aimTypeRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    public AimType create(AimTypeDetails details) {
        AimType aimType = new AimType();
        aimType.setType(details.getType());

        return aimTypeRepository.save(aimType);
    }
}
