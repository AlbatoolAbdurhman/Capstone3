package com.example.capstone3.Service;

import com.example.capstone3.Api.ApiException;
import com.example.capstone3.Model.Contract;
import com.example.capstone3.Model.Offer;
import com.example.capstone3.Model.Owner;
import com.example.capstone3.Repository.ContractRepository;
import com.example.capstone3.Repository.OfferRepository;
import com.example.capstone3.Repository.OwnerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class ContractService {

    private final ContractRepository contractRepository;
    private final OwnerRepository ownerRepository;
    private final OfferRepository offerRepository;

    public void addContract(Integer ownerId,Integer offerId,Contract contract) {
        Owner owner = ownerRepository.findOwnerByOwnerId(ownerId);
        if (owner == null) {
            throw new ApiException("owner with id " + ownerId + " not found");
        }
        Offer offer=offerRepository.findOfferByOfferId(offerId);
        if (offer==null) {
            throw new ApiException("offer with id " + offerId + " not found");
        }
        contractRepository.save(contract);

    }
    public List<Contract> getContract () {
        return contractRepository.findAll();
    }

    public void updateContract(Integer contractId, Contract contract ) {
        Contract c = contractRepository.findContractByContractId(contractId);
        if (c == null) {
            throw new ApiException("contract with id " + contractId + " not found");
        }
        c.setContractDocumentationPath(contract.getContractDocumentationPath());
        c.setAgreeCost(contract.getAgreeCost());
        c.setEndDate(contract.getEndDate());
        c.setPaymentDate(contract.getPaymentDate());
        c.setStartDate(contract.getStartDate());
        c.setContractDate(contract.getContractDate());
        c.setUsingYears(contract.getUsingYears());
        contractRepository.save(c);
    }

    public void deleteContract(Integer contractId) {
        Contract contract = contractRepository.findContractByContractId(contractId);
        if(contract == null){
            throw new ApiException("Course with id " + contractId + " not found");
        }
        contractRepository.delete(contract);
    }
//تمديد مدة العقد
    public String extendContract(Integer contractId, Integer extraYears, Boolean ownerApproval, Boolean investorApproval) {//التحقق من موافقة الطرفين
        Contract contract = contractRepository.findContractByContractId(contractId);

        if (contract == null)
            return "Contract not found";

        if (!ownerApproval || !investorApproval)
            return "Extension must be approved by both Owner and Investor";

        if (contract.getEndDate().isBefore(LocalDate.now()))
            return "Cannot extend an already expired contract";

        // تمديد عدد السنوات وتحديث التاريخ
        contract.setUsingYears(contract.getUsingYears() + extraYears);
        contract.setEndDate(contract.getEndDate().plusYears(extraYears));

        contractRepository.save(contract);
        return "Contract extended successfully until: " + contract.getEndDate();
    }




}
