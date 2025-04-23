package com.example.capstone3.Controller;

import com.example.capstone3.Api.ApiResponse;
import com.example.capstone3.Model.Investor;
import com.example.capstone3.Service.InvestorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("investor")
@RequiredArgsConstructor
public class InvestorController {

    private final InvestorService investorService ;


    @PostMapping("/add")
    public ResponseEntity add(@Valid @RequestBody Investor investor){
    investorService.addInvestore(investor);
        return ResponseEntity.status(200).body(new ApiResponse("Added successfully"));
    }

    @GetMapping("/get/all")
    public ResponseEntity getall(){
        return ResponseEntity.status(200).body(investorService.getAllInvestor());
    }

    @PutMapping("/update/{investorId}")
    public ResponseEntity update(@RequestBody Investor investor, @PathVariable Integer investorId){
        investorService.updateInvestor(investor,investorId);
        return ResponseEntity.status(200).body(new ApiResponse("Updated successfully "));
    }

    @DeleteMapping("/delete/{investorId}")
    public ResponseEntity delete(@PathVariable Integer investorId){
        investorService.deleteInvestor(investorId);
        return ResponseEntity.status(200).body(new ApiResponse("Deleted successfully"));
    }

}
