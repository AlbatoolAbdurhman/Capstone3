package com.example.capstone3.Controller;

import com.example.capstone3.Model.Owner;
import com.example.capstone3.Service.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/owner")
public class OwnerController {



    private final OwnerService ownerService;

    public ResponseEntity getAllOwner(){

    }




    @GetMapping("/get-by-id/{id}")
    public ResponseEntity getById(@PathVariable Integer id) {
        Owner owner = ownerService.getById(id);
        return owner != null ? ResponseEntity.ok(owner) : ResponseEntity.notFound().build();
    }

    @PostMapping("/add")
    public ResponseEntity create(@RequestBody Owner owner) {
        ownerService.addOwner(owner);
        return ResponseEntity.status(201).body(new ApiResponse("Owner added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity update(@PathVariable Integer id, @RequestBody Owner owner) {
        ownerService.updateOwner(id, owner);
        return ResponseEntity.status(200).body(new ApiResponse("update owner successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        ownerService.deleteOwner(id);
        return ResponseEntity.status(200).body(new ApiResponse("delete owner successfully"));
    }
}
