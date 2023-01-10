package com.maigrand.overwatchdb.service;

import com.maigrand.overwatchdb.entity.AimType;
import com.maigrand.overwatchdb.exception.EntityNotFoundException;
import com.maigrand.overwatchdb.payload.AimTypeDetails;
import com.maigrand.overwatchdb.repository.AimTypeRepository;
import com.maigrand.overwatchdb.validator.OnCreate;
import com.maigrand.overwatchdb.validator.OnUpdate;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

@Service
@Validated
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
                .orElseThrow(() -> new EntityNotFoundException("AimType not found"));
    }

    @Validated(OnCreate.class)
    public AimType create(AimTypeDetails details) {
        AimType aimType = new AimType();
        aimType.setType(details.getType());

        return aimTypeRepository.save(aimType);
    }

    @Validated(OnUpdate.class)
    public AimType update(Integer id, AimTypeDetails details) {
        AimType aimType = findById(id);
        Optional.ofNullable(details.getType()).ifPresent(aimType::setType);

        return aimTypeRepository.save(aimType);
    }
}
