package com.example.capstone3.Service;

import com.example.capstone3.Api.ApiException;
import com.example.capstone3.Api.ApiResponse;
import com.example.capstone3.Model.Owner;
import com.example.capstone3.Repository.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OwnerService {

    private final OwnerRepository ownerRepository;

    public List<Owner> getAllOwners() {
        return ownerRepository.findAll();
    }

    public Owner getById(Integer ownerId) {
        if(ownerId == null){
            throw new ApiException("Owner not found");
        }
        return ownerRepository.findOwnerByOwnerId(ownerId);
    }

    public void addOwner(Owner owner) {
        ownerRepository.save(owner);
    }

    public void updateOwner(Integer ownerId, Owner owner) {
        Owner oldOwner = ownerRepository.findOwnerByOwnerId(ownerId);
        if(oldOwner == null){
            throw new ApiException("Owner not found");
        }

        ownerRepository.save(owner);
    }

    public void deleteOwner(Integer ownerId) {
        Owner owner = ownerRepository.findOwnerByOwnerId(ownerId);
        if (owner == null) {
            throw new ApiException("owner not found");
        }

        ownerRepository.delete(owner);
    }
}
