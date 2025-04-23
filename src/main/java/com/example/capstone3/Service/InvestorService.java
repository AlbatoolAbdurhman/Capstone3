package com.example.capstone3.Service;

import com.example.capstone3.Api.ApiException;
import com.example.capstone3.Model.Investor;
import com.example.capstone3.Repository.InvestorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InvestorService {

    private final InvestorRepository investorRepository ;


    public void addInvestor(Investor investor){
        investorRepository.save(investor);
    }

    public List<Investor> getAllInvestor(){
        return investorRepository.findAll();
    }

    public void updateInvestor(Investor investor, Integer investorId){
        Investor oldInvestor= investorRepository.findInvestorByInvestorId(investorId);
        if (oldInvestor==null){
            throw new ApiException("investor not found");
        }
        oldInvestor.setName(investor.getName());
        oldInvestor.setEmail(investor.getEmail());
        oldInvestor.setPassword(investor.getPassword());
        oldInvestor.setPhoneNumber(investor.getPhoneNumber());
    }


    public void deleteInvestor(Integer investorId){
        Investor oldInvestor= investorRepository.findInvestorByInvestorId(investorId);
        if (oldInvestor==null){
            throw new ApiException("investor not found");
        }
        investorRepository.delete(oldInvestor);
    }


}
